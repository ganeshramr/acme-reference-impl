package com.acme.reference.impl.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.acme.reference.impl.di.qualifiers.Benchmark;

public  class AbstractBenchmarkAggDAO<T> implements BenchmarkAggDAOI<T> {

	@Inject
	@Benchmark
    EntityManager em;

	@Override
	public T create(T entity) {

		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		return entity;
	}

	@Override
	public T read(Long id, Class<T> T) {

		return em.find(T, id);
	}

	@Override
	public T update(T entity) {

		em.getTransaction().begin();
		T updatedEntity = em.merge(entity);
		em.getTransaction().commit();
		return updatedEntity;
	}

	@Override
	public void delete(T entity) {

		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();

	}

}
