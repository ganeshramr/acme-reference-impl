package com.acme.reference.impl.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

import com.acme.reference.impl.di.qualifiers.Oracle;
import com.acme.reference.impl.di.qualifiers.OracleDAO;
import com.acme.reference.impl.constants.Constants;
import com.acme.reference.impl.model.BenchmarkClientOracle;

@Oracle
@Service
@PerLookup
public class OracleDBBenchmarkClientDAO implements BenchmarkAggregatorDAOI<BenchmarkClientOracle> {

	@Inject
	@OracleDAO
	EntityManager em;

	@Override
	public String create(BenchmarkClientOracle entity) {
		em.getTransaction().begin();
		// em.persist(entity);
		Query query = em.createNativeQuery(Constants.INSERT_STOCK, BenchmarkClientOracle.class);
		query.setParameter(1, entity.getId());
		query.setParameter(2, entity.getCode());
		query.setParameter(3, entity.getName());
		query.executeUpdate();
		em.getTransaction().commit();
		return entity.getId().toString();
	}

	@Override
	public BenchmarkClientOracle read(Long id) {
		Query query = em.createNativeQuery(Constants.READ_STOCK, BenchmarkClientOracle.class);
		query.setParameter(1, id);
		BenchmarkClientOracle benchmarkClientOracle = (BenchmarkClientOracle) query.getSingleResult();
		return benchmarkClientOracle;
		// return em.find(BenchmarkClientOracle.class, id);
	}

	@Override
	public BenchmarkClientOracle update(BenchmarkClientOracle entity) {
		return null;
	}

	@Override
	public void delete(String id) {

	}

}
