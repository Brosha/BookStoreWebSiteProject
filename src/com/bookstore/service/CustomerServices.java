package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;

public class CustomerServices {
	private HttpServletRequest request;
	private CustomerDAO customerDAO;
	private HttpServletResponse response;
	public CustomerServices(HttpServletRequest request,HttpServletResponse response) {
		super();
		this.request = request;
		this.customerDAO = new CustomerDAO();
		this.response = response;
	}
	
	public void listCustomers() throws ServletException, IOException {
		List<Customer> listCustomers = customerDAO.listAll();
		request.setAttribute("listCustomers", listCustomers);
		String listPage = "customer_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);		
		
	}
	
	private void fillCustomer(Customer customer){
		if(request.getParameter("email")!=null && !request.getParameter("email").equals(""))
			customer.setEmail(request.getParameter("email"));			
		customer.setAddress(request.getParameter("address"));
		customer.setCity(request.getParameter("city"));
		customer.setCountry(request.getParameter("country"));
		customer.setFullname(request.getParameter("fullName"));
		if(request.getParameter("password")!=null && !request.getParameter("password").equals(""))
			customer.setPassword(request.getParameter("password"));
		customer.setPhone(request.getParameter("phone"));
		customer.setZipcode(request.getParameter("zipcode"));
	}

	public void createCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		if(existCustomer !=null) {
			String message="Could not create Customer. This email is already exists.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer_form.jsp");			
			requestDispatcher.forward(request, response);
		}
		else {
			Customer customer = new Customer();
			fillCustomer(customer);
			customerDAO.create(customer);
			request.setAttribute("message", "New customer has been created successfully.");
			listCustomers();
		}
		
		
	}

	public void editCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(customerId);
		request.setAttribute("customer", customer);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer_form.jsp");
		dispatcher.forward(request, response);		
		
		
	}

	public void updateCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		Customer existCustomer = customerDAO.findByEmail(email);
		if(existCustomer != null && existCustomer.getCustomerId()!= customerId) {
			String message="Could not update Customer. This email is already exists.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer_form.jsp");			
			requestDispatcher.forward(request, response);		
			
		}
		else {
			Customer customer = new Customer();
			fillCustomer(customer);
			customerDAO.update(customer);
			request.setAttribute("message", "A customer has been updated successfully.");
			listCustomers();		
			
			
		}
		
		
		
	}

	public void deleteCustomer() throws IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		customerDAO.delete(customerId);
		String message = "The Customer " + customerId + " has been removed successfully.";
		request.getSession().setAttribute("messageUpdate", message);
		response.sendRedirect("/BookStoreWebSite/admin/list_customer");
		
	}

	public void registerCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		if(existCustomer !=null) {
			String message="Could not register. This email already exists.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer_form.jsp");			
			requestDispatcher.forward(request, response);
		}
		else {
			Customer customer = new Customer();
			fillCustomer(customer);
			customerDAO.create(customer);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");			
			requestDispatcher.forward(request, response);
		}
		
	}

	public void showLoginPage() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginPage);
		requestDispatcher.forward(request, response);
		
	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password =  request.getParameter("password");
		boolean loginResult = customerDAO.checkLogin(email, password);
		
		if(loginResult) {
			request.getSession().setAttribute("loggedCustomer", customerDAO.findByEmail(email));
			String redirectURL = (String) request.getSession().getAttribute("redirectURL");
			if(redirectURL!=null) {
				request.getSession().removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
				
			} else {
			response.sendRedirect(request.getContextPath());
			}
		}
		else {
			String message = "Login failed!";
			request.getSession().setAttribute("message", message);
			response.sendRedirect(request.getContextPath()+"/login");
			
			
			
		
	}
	
	}
	public void showCustomerProfile() throws ServletException, IOException {
		String profilePage = "frontend/customer_profile.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(profilePage);
		requestDispatcher.forward(request, response);
		
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		String profilePage = "frontend/edit_profile.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(profilePage);
		requestDispatcher.forward(request, response);
		
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		fillCustomer(customer);
		customerDAO.update(customer);
		response.sendRedirect(request.getContextPath()+"/view_profile");
		
	}

}
