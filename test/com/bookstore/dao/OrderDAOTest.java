package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderDetailId;

class OrderDAOTest {
private static OrderDAO orderDao;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		orderDao = new OrderDAO();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		orderDao.close();
	}

	@Test
	void testCreateBookOrder() {
		BookOrder bookOrder = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(3);
		
		bookOrder.setCustomer(customer);
		bookOrder.setRecipientName("Nam Ha Min");
		bookOrder.setRecipientPhone("123456789");
		bookOrder.setRecipientMethod("Test");
		bookOrder.setShippingAddress("Moscow");
		
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
		OrderDetail orderDetail = new OrderDetail();
		
		Book book = new Book();
		book.setBookId(20);		
		orderDetail.setBook(book);
		orderDetail.setBookOrder(bookOrder);
		
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(33.99f*2);
		orderDetails.add(orderDetail);
		bookOrder.setOrderDetails(orderDetails);
		bookOrder.setTotal(33.99f*2);
		BookOrder createdOrder = orderDao.create(bookOrder);
		assertTrue(createdOrder!=null && createdOrder.getOrderDetails().size()>0);
		
	}
	
	@Test
	void testCreateBookOrder2() {
		BookOrder bookOrder = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(6);
		
		bookOrder.setCustomer(customer);
		bookOrder.setRecipientName("Bulka");
		bookOrder.setRecipientPhone("678x");
		bookOrder.setRecipientMethod("Test2");
		bookOrder.setShippingAddress("Moscow2");
		
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
		OrderDetail orderDetail1 = new OrderDetail();
		
		Book book = new Book();
		book.setBookId(20);
			
		orderDetail1.setBook(book);
		orderDetail1.setBookOrder(bookOrder);
		
		orderDetail1.setQuantity(2);
		orderDetail1.setSubtotal(33.99f*2);
		orderDetails.add(orderDetail1);
		
		Book book2 = new Book();
		OrderDetail orderDetail2 = new OrderDetail();
		book2.setBookId(19);
		orderDetail2.setBook(book2);
		orderDetail2.setQuantity(4);
		orderDetail2.setSubtotal(36.72f*4);
		orderDetail2.setBookOrder(bookOrder);
		orderDetails.add(orderDetail2);
		
		
		bookOrder.setOrderDetails(orderDetails);
		bookOrder.setTotal(33.99f*2 +36.72f*4);
		BookOrder createdOrder = orderDao.create(bookOrder);
		assertTrue(createdOrder!=null && createdOrder.getOrderDetails().size()==2);
		
		
	}
	
	@Test
	void testGetBookOrder() {
		BookOrder bookOrder = orderDao.get(9);
		System.out.println(bookOrder.getOrderId()+ " "+bookOrder.getTotal());
		assertEquals(2, bookOrder.getOrderDetails().size());
	}
	
	@Test
	void testGetByIdAndCustomerNull() {
		Integer orderId =100;
		Integer customerId = 100;
		BookOrder order = orderDao.get(orderId, customerId);
		assertNull(order);
	}
	
	

	@Test
	void testUpdateBookOrder() {
		fail("Not yet implemented");
	}
	
	@Test
	void testUpdateBookOrderShippingAddress() {
		BookOrder bookOrder = orderDao.get(9);
		bookOrder.setShippingAddress("New Shipping Address");
		orderDao.update(bookOrder);
		BookOrder updatedOrder = orderDao.get(9);
		assertTrue(updatedOrder.getShippingAddress().equals("New Shipping Address"));
	}
	
	@Test
	void testUpdateBookOrderOrderDetail() {
		BookOrder bookOrder = orderDao.get(9);
		Iterator<OrderDetail> iterator = bookOrder.getOrderDetails().iterator();
		while(iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if (orderDetail.getBook().getBookId()==20) {
				orderDetail.setQuantity(1);
				orderDetail.setSubtotal(orderDetail.getSubtotal()/2);
			}
			
		}
		orderDao.update(bookOrder);
		BookOrder updatedOrder = orderDao.get(9);
		Iterator<OrderDetail> iterator1 = updatedOrder.getOrderDetails().iterator();
		int updatedQuantity=0;
		float updatedSubtotal=0.0f;
		while(iterator1.hasNext()) {
			OrderDetail orderDetail = iterator1.next();
			if (orderDetail.getBook().getBookId()==20) {
				updatedQuantity=orderDetail.getQuantity();
				updatedSubtotal=orderDetail.getSubtotal();
			}
			
		}		
		
		
		assertTrue(updatedQuantity==1 && updatedSubtotal==33.99f);
	}

	@Test
	void testListByCustomer() {
		
		List<BookOrder> bookOrders = orderDao.listByCustomer(6);
		for (BookOrder bookOrder : bookOrders) {
			for (OrderDetail orderDetail : bookOrder.getOrderDetails()) {
				System.out.println(orderDetail.getBook().getPrice()+" - "+ orderDetail.getBook().getTitle() +" - "+ orderDetail.getSubtotal());
			}
		}
	}
	@Test
	void testListByCustomerNoOrder() {
		Integer customerId =99;
		List<BookOrder> listOrders = orderDao.listByCustomer(customerId);
		assertTrue(listOrders.isEmpty());
		
	}
	@Test
	void testListByCustomerHaveOrders() {
		Integer customerId =1;
		List<BookOrder> listOrders = orderDao.listByCustomer(customerId);
		assertTrue(!listOrders.isEmpty());
		
	}
	
	
	
	
	@Test
	void testDeleteObject() {
		orderDao.delete(9);
		assertTrue(orderDao.get(9)==null);
	}

	@Test
	void testListAll() {
		List<BookOrder> list = orderDao.listAll();
		for (BookOrder bookOrder : list) {
			System.out.println(bookOrder.getOrderId() + " - "+ bookOrder.getRecipientName());
			for (OrderDetail orderDetail : bookOrder.getOrderDetails()) {
				System.out.println(orderDetail.getBook().getPrice()+" - "+ orderDetail.getBook().getTitle() +" - "+ orderDetail.getSubtotal());
			}
		}
		assertTrue(list.size()==2);
	}

	@Test
	void testCount() {
		
		assertTrue(orderDao.count()==2);
	}
	
	@Test
	public void testListRecentOrders() {
		List<BookOrder> orders= orderDao.listRecentOrders();
		for(BookOrder order : orders) {
			System.out.println(order.getCustomer().getFullname());
		}
	}

}
