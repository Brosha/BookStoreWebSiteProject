package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.BookOrder;
;

public class OrderDAO extends JpaDAO<BookOrder> implements GenericDAO<BookOrder> {

	@Override
	public BookOrder create(BookOrder order) {
			order.setOrderDate(new Date());			
			order.setStatus("Processing");
			order.setRecipientMethod("Test");
		return super.create(order);
	}
	
	

	@Override
	public BookOrder get(Object orderId) {
		
		return super.find(BookOrder.class, orderId);
	}
	
	public BookOrder get(Integer orderId, Integer customerId) {
		Map<String, Object> parameters= new HashMap<String, Object>();
		parameters.put("orderId", orderId);
		parameters.put("customerId", customerId);
		List<BookOrder> result = super.findWithNamedQuery("BookOrder.findByIdAndCustomer", parameters);
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public BookOrder update(BookOrder t) {
		
		return super.update(t);
	}

	@Override
	public BookOrder find(Class<BookOrder> type, Object id) {
		// TODO Auto-generated method stub
		return super.find(type, id);
	}

	
	@Override
	public List<BookOrder> listAll() {
		
		return super.findWithNamedQuery("BookOrder.findAll");
	}

	@Override
	public long count() {
		
		return super.countWithNamedQuery("BookOrder.countAll");
	}
	
	public List<BookOrder> listByCustomer(Integer customerId){
		
		return super.findWithNamedQuery("BookOrder.findByCustomer", "customerId", customerId);
	}


	@Override
	public void delete(Object id) {
		super.delete(BookOrder.class, id);
		
	}

}
