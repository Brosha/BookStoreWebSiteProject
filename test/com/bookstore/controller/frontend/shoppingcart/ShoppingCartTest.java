package com.bookstore.controller.frontend.shoppingcart;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;

public class ShoppingCartTest {
	private static ShoppingCart cart;
	
	@BeforeClass
	public static void setUpBeforeClass()  throws Exception{
		cart = new ShoppingCart();
		for (int i = 1; i<11; i++) {
			Book book = new Book();
			book.setBookId(i);
			book.setPrice(i*2.0f);
			cart.addItem(book);			
			
		}
	}
	
	
	@Test
	public void testAddItem() {
		
		Book book = new Book();
		book.setBookId(4);
		cart.addItem(book);
		assertTrue(2==cart.getItems().get(book));
	}

	@Test
	public void testGetItems() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalQuantity() {
		int test = cart.getTotalQuantity();
		assertTrue(test == cart.getItems().size());
	}
	@Test
	public void testGetTotalAmount() {
		double test = 0.0;
		for (int i = 1; i<11; i++) {
			test+=i*2.0f;
			
		}
		assertTrue(test == cart.getTotalAmount());
		
	}
	
	
	@Test
	public void testClear() {
	
		cart.clear();
		assertTrue(cart.getItems().size()==0);
	}
}
