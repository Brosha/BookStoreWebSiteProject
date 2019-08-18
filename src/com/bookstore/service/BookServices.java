package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {
	
	private BookDAO bookDAO;	
	private EntityManager entityManager;
	private HttpServletRequest request;
	private CategoryDAO categoryDAO;
	private HttpServletResponse response;
	
	
	
	public BookServices(EntityManager entityManager, HttpServletRequest request,
			HttpServletResponse response) {
		super();
		this.bookDAO = new BookDAO(entityManager);
		this.entityManager = entityManager;
		this.request = request;
		this.response = response;
		categoryDAO = new CategoryDAO(entityManager);
	}



	public void listBooks() throws ServletException, IOException {
		List<Book> listBooks = bookDAO.listAll();
		request.setAttribute("listBooks", listBooks);
		String listPage = "book_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
		
	}



	public void createBook() throws ServletException, IOException {
		
		String title = request.getParameter("title");
		
		Book existBook = bookDAO.findByTitle(title);
		if (existBook!=null) {
			String message = "Could not create book. A book with title "+title+" already exists";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/new_book");			
			requestDispatcher.forward(request, response);
			return;
		}
		Book newBook = new Book();
		readBookFields(newBook);
		
		
		Book createdBook = bookDAO.create(newBook);
		if (createdBook.getBookId()>0) {
			String message = "A new book has been created successfully";
			request.setAttribute("message", message);
			listBooks();
		
		}
	}



	public void newBook() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		
		request.setAttribute("listCategory", listCategory);
		String listPage = "book_form.jsp";		
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
	}



	public void editBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		List<Category> listCategory = categoryDAO.listAll();
		
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("book", book);
		String listPage = "book_form.jsp";		
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
	}



	public void updateBook() throws IOException, ServletException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Book existBook = bookDAO.get(bookId);
		Book bookByTitle= bookDAO.findByTitle(request.getParameter("title"));
		if(!existBook.equals(bookByTitle)){
			request.setAttribute("message", "Could not create Book. A book with title:"
					+ " "+request.getParameter("title")+" already exists");
			request.setAttribute("book", existBook);
			List<Category> listCategory = categoryDAO.listAll();			
			request.setAttribute("listCategory", listCategory);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("book_form.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		
		readBookFields(existBook);
		bookDAO.update(existBook);
		
		String message = "Book has been updated successfully";
		
		request.getSession().setAttribute("messageUpdate", message);
		response.sendRedirect("/BookStoreWebSite/admin/book_list");
		
		
	}
	
	
	public void readBookFields(Book book) throws IOException, ServletException {
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
				
		String bookImage = request.getParameter("bookImage");
		float price = Float.parseFloat((request.getParameter("price")));
		String description = request.getParameter("description");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate=null;
		try {
			publishDate = dateFormat.parse(request.getParameter("publishDate"));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ServletException("Error parsing publish date (format is MM/dd/yyyy)");
			
		}
		
		
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		book.setPublishDate(publishDate);
		book.setIsbn(isbn);
		Category category = categoryDAO.get(categoryId);
		book.setCategory(category);
		book.setPrice(price);
		Part part = request.getPart("bookImage");
		if (part !=null && part.getSize()>0) {
			long size = part.getSize();
			byte[] imageBytes = new byte[(int) size];
			 InputStream inputStream = part.getInputStream();
			 inputStream.read(imageBytes);
			 inputStream.close();
			book.setImage(imageBytes); 
		}
		
	}



	public void deleteBook() throws IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		bookDAO.delete(bookId);
		String message = "The Book " + bookId + " has been removed successfully.";
		request.getSession().setAttribute("messageUpdate", message);
		response.sendRedirect("/BookStoreWebSite/admin/list_books");
		
	}


	
	

}
