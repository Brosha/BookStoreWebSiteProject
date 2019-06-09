package com.bookstore.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaDAO<T> {
	
	protected EntityManager entityManager;

	public JpaDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	public T create(T t) {
		entityManager.getTransaction().begin();
		
		entityManager.persist(t);
		entityManager.flush();
		entityManager.refresh(t);		
		entityManager.getTransaction().commit();
		
		return t;
	}
	public T update (T t) {
		entityManager.getTransaction().begin();
		t = entityManager.merge(t);
		entityManager.getTransaction().commit();
		return t;
	}
	
	public T find(Class<T> type, Object id) {
		T entity =entityManager.find(type, id);
		if(entity!=null)
			entityManager.refresh(entity);
		return entity;
		
	}
	
	public void delete(Class<T> type, Object id) {
		entityManager.getTransaction().begin();
		Object reference = entityManager.getReference(type, id);
		if(reference!=null)
			entityManager.remove(reference);
		entityManager.getTransaction().commit();
	}
	
	public List<T> findWithNamedQuery(String queryName){
		
		Query query = entityManager.createNamedQuery(queryName);
		return query.getResultList();
		
	}
	
	public List<T> findWithNamedQuery(String queryName, String paramName, Object paramValue){
		
		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		return query.getResultList();
		
	}
	
	public long countWithNamedQuery(String queryName) {
		Query query = entityManager.createNamedQuery(queryName);
		return (long) query.getSingleResult();
		
	}
}
