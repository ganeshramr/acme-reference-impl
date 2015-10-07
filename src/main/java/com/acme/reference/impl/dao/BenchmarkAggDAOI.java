package com.acme.reference.impl.dao;

import org.jvnet.hk2.annotations.Contract;


@Contract
public interface BenchmarkAggDAOI<T> {
	
	T create(T entity);
	T update(T entity);
	T read(Long id, Class<T> T);
	void delete(T entity);

}
