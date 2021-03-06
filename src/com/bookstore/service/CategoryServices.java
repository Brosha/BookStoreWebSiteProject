package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryServices {
	private CategoryDAO categoryDAO;	

	private HttpServletRequest request;
	private HttpServletResponse response;
	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		
		this.request = request;
		this.response = response;
		
		categoryDAO = new CategoryDAO();
	}
	
	public void listCategory() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		String listPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void createCategory() throws ServletException, IOException {
		Category category = new Category();
		String name = request.getParameter("name");
		category.setName(name);
		Category existCategory = categoryDAO.findByName(name);
		if(existCategory==null) {
			categoryDAO.create(category);
			request.setAttribute("message", "New category created successfully");
			listCategory();
		}
		else {
			String message = "Could not create category. A category with name "+category+" already exists";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("category_form.jsp");
			
			requestDispatcher.forward(request, response);
			
		}
		
		
		
	}

	public void editCategory() throws ServletException, IOException {
		int categoryId= Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);
		request.setAttribute("category", category);
		String listPage = "category_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException {
		int categoryId= Integer.parseInt(request.getParameter("categoryId"));
		String categoryName= request.getParameter("name");
		Category categoryById = categoryDAO.get(categoryId);
		Category categoryByName = categoryDAO.findByName(categoryName);
		if(categoryByName !=null && categoryById.getCategoryId()!= categoryByName.getCategoryId() ) {
			
			String message = "Could not update category. "
					+ "A category with name "+ categoryName+" already exists.";
			request.setAttribute("message", message);
			String listPage = "category_form.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
			requestDispatcher.forward(request, response);
		}
		else { 
			categoryById.setName(categoryName);
			categoryDAO.update(categoryById);
			String message = "Category has been updated successfully";
			
			request.getSession().setAttribute("messageUpdate", message);
			response.sendRedirect("/BookStoreWebSite/admin/list_category");
			
			
			
		}
		
		
		
		
	}

	public void deleteCAtegory() throws IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		BookDAO bookDAO = new BookDAO();
		String message;
		long bookCount = bookDAO.count(categoryId);
		if (bookCount>0) {
			message = ("Could not delete category (ID: "+categoryId+"). "
			+ "A category has some books:"+ bookCount+" .");
		
		}
		else {
			categoryDAO.delete(categoryId);
			message = "The category " + categoryId + " has been removed successfully.";
		}			
			request.getSession().setAttribute("messageUpdate", message);
			response.sendRedirect("/BookStoreWebSite/admin/list_category");
		
	}
	
	
	
}
