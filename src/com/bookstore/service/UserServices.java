package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.entity.Users;
import com.bookstore.dao.UserDAO;

public class UserServices {
	private UserDAO userDAO;
	
	private EntityManager entityManager;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public UserServices(EntityManager entityManager ,HttpServletRequest request, HttpServletResponse response) {
		
		this.entityManager = entityManager;
		userDAO= new UserDAO(entityManager);
		this.response = response;
		this.request = request;
		
	}
	public void listUser() throws ServletException, IOException {
		List<Users> listUsers =  userDAO.listAll();
		
		request.setAttribute("listUsers",listUsers);
		
		
		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
	}
	
	public void createUser() throws ServletException, IOException {
		Users newUser = new Users();
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		newUser.setEmail(email);
		newUser.setFullName(fullname);
		newUser.setPassword(password);
		Users existUser = userDAO.findByEmail(email);
		if(existUser==null) {
			userDAO.create(newUser);
			request.setAttribute("message", "New user created successfully");
			listUser();
		}
		else {
			String message = "Could not create user. A user with email "+email+" already exists";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("user_form.jsp");
			
			requestDispatcher.forward(request, response);
			
		}
		
		
		
	}
	public void editUser() throws ServletException, IOException {
		
		
		int userId = Integer.parseInt(request.getParameter("id"));
				
		Users user = userDAO.get(userId );
		String page;
		if (user!=null) {
		page ="user_form.jsp";
		request.setAttribute("user", user);
		}
		else {
			String message = "Could not find user with" + userId;
			page = "message.jsp";
			request.setAttribute("message", message);
			
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
		requestDispatcher.forward(request, response);
	}
	public void updateUser() throws ServletException, IOException {
		Users editedUser = new Users();
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		int userId = Integer.parseInt(request.getParameter("userId"));
		editedUser.setEmail(email);
		editedUser.setFullName(fullname);
		editedUser.setPassword(password);
		editedUser.setUserId(userId);
		Users userByEmail = userDAO.findByEmail(email);
		Users user = userDAO.get(userId );		
		
		
		
		if (userByEmail!= null && userByEmail.getUserId()!=user.getUserId()) {
			request.setAttribute("message", "Could not create user. A user with email"
					+ " "+email+" already exists");
			request.setAttribute("user", user);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("user_form.jsp");
			requestDispatcher.forward(request, response);
			
			
		}
			
		else {	userDAO.update(editedUser);
			//request.setAttribute("message", "User has been updated successfully");
			
			request.getSession().setAttribute("messageUpdate", "User has been updated successfully");
			response.sendRedirect("/BookStoreWebSite/admin/list_users");
			
		}
		
	}
	public void deleteUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		userDAO.delete(userId);
		request.setAttribute("message", "User "+userId+ " has been deleted successfully");
		request.removeAttribute("id");
		listUser();
		}
	
	
	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password =  request.getParameter("password");
		boolean loginResult = userDAO.checkLogin(email, password);
		if(loginResult) {
			request.getSession().setAttribute("useremail", email);
			RequestDispatcher dispatcher =request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		}
		else {
			String message = "Login failed!";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(request, response);
			
					
		}
		
		
		
	}
	
}
