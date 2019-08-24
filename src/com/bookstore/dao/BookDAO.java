package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {
	
	
	
	public BookDAO(EntityManager entityManager) {
		super(entityManager);
		
	}

	@Override
	public Book create(Book book) {
		book.setLastUpdateTime(new Date());		
		return super.create(book);
	}

	@Override
	public Book update(Book book) {
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}

	

	@Override
	public Book get(Object id) {
		
		return super.find(Book.class, id);
	}

	@Override
	public void delete(Object bookId) {
		super.delete(Book.class, bookId);
		
		
	}

	@Override
	public List<Book> listAll() {
		
		return super.findWithNamedQuery("Book.findAll");
	}
	
	public Book findByTitle(String title) {		
		List<Book> books = super.findWithNamedQuery("Book.findByTitle", "title", title);
		if(!books.isEmpty())
			return books.get(0);
		return null;
	}
	
	public List<Book> listByCategory(int categoryId){
		List<Book> listBooks= super.findWithNamedQuery("Book.findByCategory", "catId", categoryId);
		return listBooks;
	}
	
	public List<Book> listNewBooks(){
		
		Query query = entityManager.createNamedQuery("Book.listNew");
		query.setFirstResult(0);
		query.setMaxResults(4);
		
		return query.getResultList();		
		
		
	}
	
	public List<Book> search(String keyword){
		
		return super.findWithNamedQuery("Book.search", "keyword", keyword);
		
	}
	
	
	@Override
	public long count() {
		
		return super.countWithNamedQuery("Book.countAll");
	}

}
