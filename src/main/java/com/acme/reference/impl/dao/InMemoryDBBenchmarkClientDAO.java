package com.acme.reference.impl.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

import com.acme.reference.impl.di.qualifiers.InMemoryDAO;
import com.acme.reference.impl.model.BenchmarkClient;

@Service 
@PerLookup
public class InMemoryDBBenchmarkClientDAO implements BenchmarkAggregatorDAOI<BenchmarkClient> {
	
	@Inject
	@InMemoryDAO
	EntityManager em;

	@Override
	public String create(BenchmarkClient entity) {
		
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		return entity.getId().toString();
	}
	

	@Override
	public BenchmarkClient read(Long id) {
		
		return  em.find(BenchmarkClient.class, id);	
	}

	@Override
	public BenchmarkClient update(BenchmarkClient entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
