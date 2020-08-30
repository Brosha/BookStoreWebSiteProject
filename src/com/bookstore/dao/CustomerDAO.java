package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Customer;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {

	public CustomerDAO() {
		
	}
	
	@Override
	public Customer create(Customer customer) {
		customer.setRegisterDate(new Date());
		System.out.println("*********************************" +customer.getEmail());
		return super.create(customer);
	}
	
	@Override
	public Customer get(Object id) {
		
		return super.find(Customer.class, id);
	}
	
	
	@Override
	public Customer update(Customer customer) {
		
		return super.update(customer);
	}

	@Override
	public void delete(Object id) {
		super.delete(Customer.class, id);
		
	}
	
	public Customer findByEmail(String email) {
		List<Customer> result = super.findWithNamedQuery("Customer.findByEmail","email" ,email);
		if(!result.isEmpty()) {
			return result.get(0);
		}
		else {
			return null;
		}
		
	}

	@Override
	public List<Customer> listAll() {
		return super.findWithNamedQuery("Customer.findAll");
		
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Customer.countAll");
		
	}

	public boolean checkLogin(String email, String password) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", email);
		parameters.put("password", password);
		List<Customer> listCustomers = super.findWithNamedQuery("Customer.checkLogin", parameters);
		if(listCustomers.size() ==1) {
			return true;
		}
		
		return false;
		
	}

}
