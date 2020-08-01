package com.bookstore.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Review;


@WebServlet("/admin/")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AdminHomeServlet() {
        super();
       
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAO userDAO = new UserDAO();
		OrderDAO orderDAO = new OrderDAO();
		ReviewDAO reviewDAO = new ReviewDAO();
		BookDAO bookDAO= new BookDAO();
		CustomerDAO  customerDAO = new CustomerDAO();
		
		List<BookOrder> listRecentOrders = orderDAO.listRecentOrders();
		List<Review> listReviews = reviewDAO.listRecentReviews();
		
		long totalUsers = userDAO.count();
		long totalBooks = bookDAO.count();
		long totalCustomers = customerDAO.count();
		long totalReviews = reviewDAO.count();
		long totalOrders = orderDAO.count();
		
		request.setAttribute("listRecentOrders", listRecentOrders);
		request.setAttribute("listRecentReviews", listReviews);
		request.setAttribute("totalUsers", totalUsers);
		request.setAttribute("totalBooks", totalBooks);
		request.setAttribute("totalCustomers", totalCustomers);
		request.setAttribute("totalReviews", totalReviews);
		request.setAttribute("totalOrders", totalOrders);
		
		String homePage= "index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homePage);		
		dispatcher.forward(request, response);
	}

	
	

}
