package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaDAO<T> {
	private static EntityManagerFactory entityManagerFactory;
	
	
	protected EntityManager entityManager;
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebSite");
		
		
	}
	public JpaDAO() {
	
	}
	
	public T create(T t) {
		EntityManager entityManager =entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(t);
		entityManager.flush();
		entityManager.refresh(t);		
		entityManager.getTransaction().commit();
		entityManager.close();
		return t;
	}
	public T update (T t) {
		EntityManager entityManager =entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		t = entityManager.merge(t);
		entityManager.getTransaction().commit();
		entityManager.close();
		return t;
	}
	
	public T find(Class<T> type, Object id) {
		EntityManager entityManager =entityManagerFactory.createEntityManager();
		T entity =entityManager.find(type, id);
		if(entity!=null)
			entityManager.refresh(entity);
		entityManager.close();
		return entity;
		
	}
	
	public void delete(Class<T> type, Object id) {
		EntityManager entityManager =entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Object reference = entityManager.getReference(type, id);
		if(reference!=null)
			entityManager.remove(reference);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public List<T> findWithNamedQuery(String queryName){
		EntityManager entityManager =entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryName);		
		List<T> result=query.getResultList();
		entityManager.close();
		return result;
		
	}
	
	public List<T> findWithNamedQuery(String queryName, String paramName, Object paramValue){
		EntityManager entityManager =entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		List<T> result=query.getResultList();
		entityManager.close();
		return result;
		
	}
	
	public List<T> findWithNamedQuery(String queryName, Map<String, Object> parameters){
		EntityManager entityManager =entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryName);		
			for (Entry<String, Object> entry : parameters.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		
			List<T> result=query.getResultList();
			entityManager.close();
			return result;
		
	}
	public List<T> findWithNamedQuery(String queryName, int firstResult, int maxResult){
		EntityManager entityManager =entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryName);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List<T> result=query.getResultList();
		entityManager.close();
		return result;	
		
		
	}
	
	
	public long countWithNamedQuery(String queryName) {
		
		EntityManager entityManager =entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryName);
		long result = (long) query.getSingleResult();
		entityManager.close();
		return result;
		
	}
public long countWithNamedQuery(String queryName, String paramName, Object paramValue) {		
		EntityManager entityManager =entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		long result = (long) query.getSingleResult();
		entityManager.close();
		return result;
		
	}

	public static void close() {
		entityManagerFactory.close();
	}
	
	
}
