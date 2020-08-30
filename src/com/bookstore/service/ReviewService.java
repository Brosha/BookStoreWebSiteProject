package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewService {
	private HttpServletRequest request;	
	private HttpServletResponse response;
	private ReviewDAO reviewDAO;
	
	public ReviewService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.reviewDAO = new ReviewDAO();
		
		
	}
	
	public void listReview() throws ServletException, IOException {
		List<Review> reviews = reviewDAO.listAll();
		request.setAttribute("listReview", reviews);
		String listPage = "review_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
		
		
	}

	public void editReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);
		request.setAttribute("review", review);
		String formPage= "review_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(formPage);
		dispatcher.forward(request, response);
	}

	public void updateReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));
		Review review = reviewDAO.get(reviewId);
		review.setComment(request.getParameter("comment"));
		review.setHeadline(request.getParameter("headline"));
		reviewDAO.update(review);
		request.setAttribute("message", "Review "+ reviewId + " has been updated successfully");
		listReview();
	}

	public void deleteReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		reviewDAO.delete(reviewId);
		request.setAttribute("message", "Review "+ reviewId + " has been deleted successfully");
		listReview();
	}

	public void showReviewForm() throws ServletException, IOException {
		String page;
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		BookDAO bookDAO = new BookDAO();
		Book book = bookDAO.get(bookId);
		request.setAttribute("book", book);
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		Review existReview = reviewDAO.findByCustomerAndBook(customer.getCustomerId(), bookId);
		if(existReview !=null) {
			request.setAttribute("review",existReview);
			page = "frontend/review_info.jsp";
			
		}
		else {
			page = "frontend/review_form.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	public void submitReview() throws IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		int rating =Integer.parseInt(request.getParameter("rating"));
		
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review newReview = new Review();
		newReview.setComment(comment);
		newReview.setHeadline(headline);
		newReview.setRating(rating);
		Book book = new Book();
		book.setBookId(bookId);
		newReview.setBook(book);
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		newReview.setCustomer(customer);
		reviewDAO.create(newReview);		
		response.sendRedirect(request.getContextPath()+"/view_book?id="+bookId);
		
		
		
		
	}
	
}
