package com.bookstore.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.bookstore.service.UserServices;

@WebServlet("/admin/login")

public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserServices services = new UserServices(request, response);
		services.login();
		

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
		requestDispatcher.forward(request, response);

}}

