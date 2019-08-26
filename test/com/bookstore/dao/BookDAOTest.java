package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest extends BaseDAOTest{
	private static BookDAO bookDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		bookDao = new BookDAO();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book book = new Book();
		Category category = new Category();
		category.setName("Advanced Java");
		category.setCategoryId(13);
		book.setCategory(category);
		book.setTitle("Effective Java (2nd Edition)");
		book.setAuthor("Joshua Bloch");
		book.setDescription("New coverage of generics, enums, annotations, autoboxing");
		book.setPrice(38.87f);
		book.setIsbn("0321356683");		
		DateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");		
		Date publishDate = dateFormat.parse("05/28/2008");		
		book.setPublishDate(publishDate);
		String imagePath = "C:\\BookStoreProject\\dummy-data-books\\books\\Effective Java.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		Book createdBook = bookDao.create(book);
		assertTrue(createdBook.getBookId()>0);
		
		
	}
	
	@Test
	public void testCreate2ndBook() throws ParseException, IOException {
		Book book = new Book();
		Category category = new Category();
		category.setName("Advanced Java");
		category.setCategoryId(13);
		book.setCategory(category);
		book.setTitle("Java 8 in Action: Lambdas, Streams, and functional-style programming");
		book.setAuthor("Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");
		book.setDescription("Java 8 in Action is a clearly written guide to the new features of Java 8. The book covers lambdas, streams, and functional-style programming. With Java 8's functional features you can now write more concise code in less time, and also automatically benefit from multicore architectures. It's time to dig in!\r\n" + 
				"\r\n" + 
				"Purchase of the print book includes a free eBook in PDF, Kindle, and ePub formats from Manning Publications.");
		book.setPrice(36.72f);
		book.setIsbn("1617291994");		
		DateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");		
		Date publishDate = dateFormat.parse("08/28/2014");		
		book.setPublishDate(publishDate);
		String imagePath = "C:\\BookStoreProject\\dummy-data-books\\books\\Java 8 in Action.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		Book createdBook = bookDao.create(book);
		assertTrue(createdBook.getBookId()>0);
		
		
	}
	
	

	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book book = new Book();
		book.setBookId(18);
		Category category = new Category();
		category.setName("Java Core");
		category.setCategoryId(4);
		book.setCategory(category);
		book.setTitle("Effective Java (3nd Edition)");
		book.setAuthor("Joshua Bloch");
		book.setDescription("New coverage of generics, enums, annotations, autoboxing");
		book.setPrice(40f);
		book.setIsbn("0321356683");		
		DateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");		
		Date publishDate = dateFormat.parse("05/28/2008");		
		book.setPublishDate(publishDate);
		String imagePath = "C:\\BookStoreProject\\dummy-data-books\\books\\Effective Java.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		
		Book updatedBook = bookDao.update(book);
		assertEquals(book.getCategory().getName(), updatedBook.getCategory().getName());
		
		
	}

	@Test
	public void testGet() {
		Book book = bookDao.get(18);
		assertTrue(book.getBookId()==18);
	}

	@Test
	public void testDeleteObject() {
		bookDao.delete(17);
		assertTrue(bookDao.get(17)==null);
	}

	@Test
	public void testListAll() {
		List<Book> books = bookDao.listAll();
		for (Book book : books) {
			System.out.println(book.getTitle());
		}
	}
	
	@Test
	public void testListByCategory() {
		List<Book> books = bookDao.listByCategory(13);
		for (Book book : books) {
			System.out.println(book.getTitle());
		}
		
	}
	
	@Test
	public void testListNewBooks() {
		List<Book> listBooks = bookDao.listNewBooks();
		for (Book book : listBooks) {
			System.out.println(book.getTitle() + "  "+ book.getPublishDate());
		}
		assertTrue(listBooks.size()==4);
		
	}
	

	@Test
	public void testCount() {
		assertTrue(bookDao.count()==2);
	}
	
	@Test
	public void testFindByTitle() {
		Book book = bookDao.findByTitle("Effective Java (3nd Edition)");
		assertTrue(book.getTitle().equals("Effective Java (3nd Edition)"));
		
	}
	
	@Test 
	public void testSearchBook() {
		String keyword = "Java";
		List<Book> books = bookDao.search(keyword);
		for (Book book : books) {
			System.out.println(book.getTitle());
		}
		assertTrue(books.size()==6);
		
	}
	
	@Test 
	public void testSearchBookInAuthor() {
		String keyword = "Joshua";
		List<Book> books = bookDao.search(keyword);
		for (Book book : books) {
			System.out.println(book.getTitle());
		}
		assertTrue(books.size()==1);
		
	}
	@Test 
	public void testSearchBookInDescription() {
		String keyword = "Enterprise";
		List<Book> books = bookDao.search(keyword);
		for (Book book : books) {
			System.out.println(book.getTitle());
		}
		assertTrue(books.size()==1);
		
	}
	

}
