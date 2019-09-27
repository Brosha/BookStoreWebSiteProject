package com.bookstore.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Map;

import com.bookstore.entity.Book;

public class ShoppingCart {
	private Map<Book, Integer> cart = new HashMap<>();
	
	
	public void addItem(Book book) {
		if (cart.containsKey(book)){
			Integer quantity = cart.get(book) +1;
			cart.put(book, quantity);			
		}
		
		else {
			cart.put(book, 1);
		}
	}	
	
	public Map<Book, Integer> getItems() {
		return this.cart;				
	}
	
	
	public void removeItem(Book book) {
		cart.remove(book);
	}
	
	public void updateCart(int[] bookIds, int[] quantities) {
		for (int i=0; i<bookIds.length; i++) {
			Book key = new Book();
			key.setBookId(bookIds[i]);
			Integer value = quantities[i];
			cart.put(key, value);
		} 
		
	}
	public int getTotalQuantity() {
		int total=0;
		
		for (int quantity : cart.values()) {
			total+=quantity;
		}
		
		return total;
		
	}
	
	public float getTotalAmount() {
		float total = 0.0f;
		 for (Map.Entry<Book, Integer> entry : cart.entrySet()) {
			total+=entry.getKey().getPrice()*entry.getValue();
		}
		return total; 
	}
	
	public int getTotalItems() {
		return cart.size();
	}
	
	public void clear() {
		cart.clear();
		
	}
}
