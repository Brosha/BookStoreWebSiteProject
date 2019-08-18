package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest {
	
	private static CategoryDAO categoryDAO;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		categoryDAO = new CategoryDAO(entityManager);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}
	
	@Test
	public void testCreateCategory() {
		Category newCategory = new Category("Core Java");
		Category createdCategory = categoryDAO.create(newCategory);
		System.out.println(createdCategory.getCategoryId());
		assertTrue(createdCategory!=null && createdCategory.getCategoryId()>0);
	}
	
	@Test
	public void testGet() {
		Category category = categoryDAO.get(25);
		assertNotNull(category);
	}

	

	@Test
	public void testUpdateCategory() {
		Category category = new Category("Java Core");
		category.setCategoryId(4);
		Category newCat = categoryDAO.update(category);
		assertEquals(category.getName(), newCat.getName());
		
	}

	@Test
	public void testDeleteCategory() {
		categoryDAO.delete(3);
		Category cat = categoryDAO.get(3);
		assertNull(cat);
	}

	@Test
	public void testListAll() {
		assertTrue(categoryDAO.listAll().size() >0);
	}

	@Test
	public void testCount() {
		long count = categoryDAO.count();
		assertEquals(3, count);
	}
	@Test
	public void testFindByName() {
		String name = "Doom";
		Category category =categoryDAO.findByName(name);
		assertNotNull(category);
		
	}

}
