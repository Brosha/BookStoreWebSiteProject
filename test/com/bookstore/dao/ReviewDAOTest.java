package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest {

	private static ReviewDAO reviewDao;
	private static BookDAO bookDao;
	private static CustomerDAO customerDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDAO();
		bookDao = new BookDAO();
		customerDao = new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}


	@Test
	public void testCreateReview() {
		Review review = new Review();
		Customer customer = new Customer();
		customer.setCustomerId(7);		
		Book book = new Book();
		book.setBookId(20);
		review.setBook(book);
		review.setCustomer(customer);
		review.setRating(5);
		review.setHeadline("This is a very interesting book");
		review.setComment("I have read this book. Very nice!");
		Review createdReview =  reviewDao.create(review);
		assertNotNull(createdReview);
	}

	@Test
	public void testGet() {
		Review review = reviewDao.get(1);
		assertNotNull(review);
	}
	
	@Test
	public void testUpdateReview() {
		Review review = reviewDao.get(1);
		review.setHeadline("Usefull book");
		Review updatedReview = reviewDao.update(review);
		assertTrue(updatedReview.getHeadline().equals("Usefull book"));
	}
	
	
	@Test
	public void testDeleteReview() {
		reviewDao.delete(2);
		assertNull(reviewDao.get(2));
	}

	@Test
	public void testListAll() {
		List<Review> reviews = reviewDao.listAll();
		for (Review review : reviews) {
			System.out.println(review.getCustomer().getEmail() +"  "+review.getHeadline());
			
		}
		assertTrue(2==reviews.size());
	}

	@Test
	public void testCount() {
		long count = reviewDao.count();
		assertTrue(count ==2);
		
	}
	
	@Test
	public void testFindByCustomerAndBook() {
		Review review = reviewDao.findByCustomerAndBook(4, 20);
		assertTrue(review!=null);
		
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteClassOfTObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindWithNamedQueryString() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindWithNamedQueryStringStringObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindWithNamedQueryStringMapOfStringObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindWithNamedQueryStringIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountWithNamedQueryString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountWithNamedQueryStringStringObject() {
		fail("Not yet implemented");
	}
	@Test
	public void testListRecentReviews() {
		List<Review> reviews = reviewDao.listRecentReviews();
		assertTrue(reviews.size()!=0);
	}

}
