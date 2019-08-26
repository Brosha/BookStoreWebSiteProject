package com.bookstore.controller.admin.category;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.service.CategoryServices;
import com.bookstore.service.UserServices;

@WebServlet("/admin/list_category")
public class ListCategoryServlet extends HttpServlet{
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher(homePage);		
		//dispatcher.forward(request, response);
		CategoryServices categoryServices = new CategoryServices(request,response);
		categoryServices.listCategory();
	
}

}
