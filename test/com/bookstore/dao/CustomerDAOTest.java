package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDAOTest extends BaseDAOTest {
	private static CustomerDAO customerDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDao = new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}

	@Test	
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("tom@gmail.com");
		customer.setFullname("Tom Eagar");
		customer.setAddress("100 North Avenue");
		customer.setCity("New York");
		customer.setCountry("United States");
		customer.setZipcode("100000");
		customer.setPassword("password");		
		Customer savedCustomer=customerDao.create(customer);
		assertTrue(savedCustomer.getCustomerId()>0);
	}
	
	@Test	
	public void testCreateCustomer2() {
		Customer customer = new Customer();
		customer.setEmail("jack@gmail.com");
		customer.setFullname("Jack Starck");
		customer.setAddress("5 South Avenue");
		customer.setCity("New York");
		customer.setCountry("United States");
		customer.setZipcode("101010");
		customer.setPassword("pass");		
		Customer savedCustomer=customerDao.create(customer);
		assertTrue(savedCustomer.getCustomerId()>0);
	}
	

	@Test
	public void testGetCustomer() {
		Integer id = 1;
		Customer customer = customerDao.get(id);
		System.out.println(customer.getEmail());
		assertNotNull(customer);
	}
	@Test
	
	public void testUpdateCustomer() {
		Customer customer = customerDao.get(1);
		customer.setFullname("Tom Tom");
		Customer updatedCustomer = customerDao.update(customer);
		assertTrue(updatedCustomer.getFullname().equals("Tom Tom"));
		
		
	}
	
	@Test
	public void testFindByemail() {
		Customer customer = customerDao.findByEmail("jack@gmail.com");
		assertTrue(customer.getEmail().equals("jack@gmail.com"));
		
		
	}

	@Test
	public void testDeleteCustomer() {
		
		customerDao.delete(1);
		assertNull(customerDao.get(1));
	}

	@Test
	public void testListAll() {
		List<Customer> customers = customerDao.listAll();
		for (Customer customer : customers) {
			System.out.println(customer.getRegisterDate());
		}
		assertTrue(customers.size()==2);
	}

	@Test
	public void testCount() {
		long count = customerDao.count();
		assertTrue(count==2);
	}
	@Test
	public void testLogin() {
		boolean login = customerDao.checkLogin("Test@gmail.com", "1234");
		System.out.println(login);
		assertTrue(login);
		
	}

}
