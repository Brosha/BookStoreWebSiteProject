package com.bookstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CategoryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebSite");	
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Category cat = new Category("Doom");
		
		
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(cat);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println(cat.getCategoryId());
	
	}

}
