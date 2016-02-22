package com.noushin.demo.wsdata.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Define methods required to be implemented for CRUD operations on data objects.
 * 
 * @author nbashir
 *
 * @param <T>
 *            Business Entity to persist
 * @param <ID>
 *            Entity Identifier
 */
public interface GenericDao<T, ID extends Serializable> {
	/* 
	 * Find a data element by its ID.
	 */
	T findById(ID id);
	
	/* 
	 * Find all data elements.
	 */	
	List<T> findAll();

	/* 
	 * Find data elements that have the same properties as the given example.
	 */
	List<T> findByExample(T exampleInstance, String[] excludeProperty);
	
	/*
	 * Save a data element.
	 */
	T makePersistent(T entity);

	/*
	 * Remove a data element.
	 */
	void makeTransient(T entity);
}
