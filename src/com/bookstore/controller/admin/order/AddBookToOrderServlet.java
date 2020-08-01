package com.bookstore.controller.admin.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;
import com.bookstore.service.OrderServices;
@WebServlet("/admin/add_book_to_order")
public class AddBookToOrderServlet extends HttpServlet {

	public AddBookToOrderServlet() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int quantity =Integer.parseInt(request.getParameter("quantity"));
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.get(bookId);
		HttpSession httpSession = request.getSession();
		BookOrder bookOrder = (BookOrder) httpSession.getAttribute("order");
		
		float subtotal = quantity *book.getPrice();
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setBook(book);
		orderDetail.setQuantity(quantity);
		orderDetail.setSubtotal(subtotal);
		bookOrder.setTotal(subtotal+bookOrder.getTotal());
		bookOrder.getOrderDetails().add(orderDetail);
		
		request.setAttribute("book", book);
		
		
		httpSession.setAttribute("NewBookAdded", true);
		
		
		String resultPage = "add_book_result.jsp";
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(resultPage);
		requestDispatcher.forward(request, response);
				
		
	}

}
