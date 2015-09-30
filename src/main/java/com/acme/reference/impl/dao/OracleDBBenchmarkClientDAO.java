package com.acme.reference.impl.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

import com.acme.reference.impl.di.qualifiers.OracleDAO;
import com.acme.reference.impl.model.BenchmarkClientOracle;

@OracleDAO
@Service
@PerLookup
public class OracleDBBenchmarkClientDAO implements BenchmarkAggregatorDAOI<BenchmarkClientOracle> {

	@Inject
	EntityManager em;

	@Override
	public String create(BenchmarkClientOracle entity) {
		em.getTransaction().begin();
		// em.persist(entity);
		em.createNamedQuery("InsertStock", BenchmarkClientOracle.class).setParameter(1, entity.getId())
				.setParameter(2, entity.getCode()).setParameter(3, entity.getName()).executeUpdate();
		em.getTransaction().commit();
		return entity.getId().toString();
	}

	@Override
	public BenchmarkClientOracle read(Long id) {
		return (BenchmarkClientOracle) em.createNamedQuery("ReadStock", BenchmarkClientOracle.class).setParameter(1, id)
				.getResultList();
	}

	@Override
	public BenchmarkClientOracle update(BenchmarkClientOracle entity) {
		return null;
	}

	@Override
	public void delete(String id) {

	}

}
