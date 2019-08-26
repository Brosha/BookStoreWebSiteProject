package com.bookstore.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class Test {
	
	public static void main(String[] args) {
	UserDAO userDAO;
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	
	
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebSite");
		entityManager = entityManagerFactory.createEntityManager();
		userDAO= new UserDAO();
	
	
		List<Users> listUsers =  userDAO.listAll();
		for (Users users : listUsers) {
			System.out.println(users);
		}
		
	
}}
