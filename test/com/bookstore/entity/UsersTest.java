package com.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class UsersTest {

	public static void main(String[] args) {
		
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebSite");	
	
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Users user1= new Users();
		user1.setEmail("Hm@yandex.com");
		user1.setFullName("Greg");
		user1.setPassword("pas");
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(user1);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("Transaction has been completed");
	}

}
