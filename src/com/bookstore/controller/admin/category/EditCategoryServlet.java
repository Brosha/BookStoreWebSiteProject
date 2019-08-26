package com.bookstore.controller.admin.category;

import javax.servlet.annotation.WebServlet;

import com.bookstore.service.CategoryServices;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/admin/edit_category")
public class EditCategoryServlet extends HttpServlet{
	public EditCategoryServlet() {}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryServices categoryService = new CategoryServices(request, response);
		categoryService.editCategory();
		
}
}