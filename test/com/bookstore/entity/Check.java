package com.bookstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.dao.UserDAO;

public class Check {

	public static void main(String[] args) {
		
		Users user1= new Users();
		user1.setEmail("MoSh@gmail.com");
		user1.setFullName("Mo Salah");
		user1.setPassword("mane");
		
		UserDAO userDAO = new UserDAO();
		user1 = userDAO.create(user1);
		
		System.out.println(user1.getUserId());

	}

}
