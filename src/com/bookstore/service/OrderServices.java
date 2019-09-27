package com.bookstore.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;

public class OrderServices {
	private BookDAO bookDAO;	
	private OrderDAO orderDAO;
	private HttpServletRequest request;
	private CategoryDAO categoryDAO;
	private HttpServletResponse response;
	
	
	
	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.bookDAO = new BookDAO();
		this.categoryDAO = new CategoryDAO();
		this.orderDAO = new OrderDAO();
	}



	public void listAllOrders() throws ServletException, IOException {
		List<BookOrder> listOrder = orderDAO.listAll();
		request.setAttribute("listOrder", listOrder);
		String listOrders = "order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listOrders);
		dispatcher.forward(request, response);
		
		
		
	}



	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		Integer bookOderId = Integer.parseInt(request.getParameter("id"));
		BookOrder bookOrder = orderDAO.get(bookOderId);
		request.setAttribute("order", bookOrder);
		String detailPage = "order_detail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(detailPage);
		dispatcher.forward(request, response);
		
	}



	public void showCheckOutForm() throws ServletException, IOException {
		String checkOutPage = "frontend/checkout.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(checkOutPage);
		dispatcher.forward(request, response);
		
	}



	public void placeOrder() throws IOException {
		String recipientName = request.getParameter("recipientName");
		String recipientPhone = request.getParameter("recipientPhone");
		String address = request.getParameter("address");
		String zipcode = request.getParameter("zipcode");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String paymentMethod = request.getParameter("paymentMethod");
		String shippingAddress =address+", "+city+", "+zipcode+", "+country; 
		BookOrder bookOrder = new BookOrder();
		bookOrder.setRecipientName(recipientName);
		bookOrder.setRecipientPhone(recipientPhone);		
		bookOrder.setShippingAddress(shippingAddress);
		bookOrder.setPaymentMethod(paymentMethod);
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		bookOrder.setCustomer(customer);
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		Map<Book, Integer> items = shoppingCart.getItems();
		Iterator<Book> iterator = items.keySet().iterator();
		Set<OrderDetail> orderDetails = new HashSet<>();
		while (iterator.hasNext()) {
			Book book =iterator.next();
			Integer quantity = items.get(book);
			float subtotal = quantity * book.getPrice();
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(book);
			orderDetail.setQuantity(quantity);
			orderDetail.setBookOrder(bookOrder);
			orderDetail.setSubtotal(subtotal);
			orderDetails.add(orderDetail);
			
		}
		bookOrder.setOrderDetails(orderDetails);
		bookOrder.setTotal(shoppingCart.getTotalAmount());
		orderDAO.create(bookOrder);
		String message="Thank you. Your order has been received."
				+ "We will deliver your books within a few days.";
		request.getSession().removeAttribute("cart");
		request.getSession().setAttribute("message", message);
		response.sendRedirect(request.getContextPath()+"/view_cart");
	}



	public void lisOrderByCustomer() throws ServletException, IOException {
		Integer customerId= ((Customer) request.getSession().getAttribute("loggedCustomer")).getCustomerId();
		List<BookOrder> listOrders =orderDAO.listByCustomer(customerId);
		request.setAttribute("listOrders", listOrders);
		String historyPage = "frontend/order_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(historyPage);
		dispatcher.forward(request, response);
		
	}



	public void showOrderDetailCustomer() throws ServletException, IOException {
		Integer bookOderId = Integer.parseInt(request.getParameter("id"));
		Integer customerId= ((Customer) request.getSession().getAttribute("loggedCustomer")).getCustomerId(); 
		
		BookOrder bookOrder = orderDAO.get(bookOderId, customerId);
		request.setAttribute("order", bookOrder);
		String detailPage = "frontend/order_detail.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(detailPage);
		dispatcher.forward(request, response);
		
	}

}
