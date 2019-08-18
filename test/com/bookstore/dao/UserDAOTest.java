package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest extends BaseDAOTest{
	
	private static UserDAO userDAO;

	@BeforeClass
	public static void setupClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		userDAO = new UserDAO(entityManager);
	}

	@Test
	public void testCreateUsers() {

		Users user1 = new Users();
		user1.setEmail("Testim.com");
		user1.setFullName("Tim");
		user1.setPassword("bgl");

		user1 = userDAO.create(user1);

		assertTrue(user1.getUserId() > 0);

	}

	@Test(expected = PersistenceException.class )
	public void testCreateUsersFieldNotSet() {

		Users user1 = new Users();

		user1 = userDAO.create(user1);

		

	}
	@Test
	public void testUpdateUsers() {
		
	}
	
	@Test
	public void testGetUsersFound() {
		Integer userId=1;
		Users users = userDAO.get(userId);
		System.out.println(users.getEmail());
		assertNotNull(users);
		
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId=100;
		Users users = userDAO.get(userId);
		
		assertNull(users);
		
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId=9;
		userDAO.delete(userId);
		Users users = userDAO.get(userId);
		assertNull(users);
		
	}
	@Test
	public void testListAll() {
		List<Users> users = userDAO.listAll();
		
		assertTrue(users.size()>0);
	}
	@Test
	public void testCount() {
		
		long totalUsers = userDAO.count();
		System.out.println(totalUsers);
		assertEquals(2, totalUsers);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email="gmail.com";
		String password = "Spudi";
		assertFalse(userDAO.checkLogin(email, password));
		
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();

	}
}
