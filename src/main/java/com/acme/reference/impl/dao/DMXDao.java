package com.acme.reference.impl.dao;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

import com.acme.reference.impl.constants.Constants;
import com.acme.reference.impl.di.qualifiers.DMX;
import com.acme.reference.impl.model.DMXClient;

@Named
@Service
@PerLookup
public class DMXDao implements BenchmarkAggDAOI<DMXClient> {

	@Inject
	@DMX
	EntityManager em;

	@Override
	public DMXClient create(DMXClient dmxEntity) {
		em.getTransaction().begin();
		// em.persist(dmxEntity);
		Query query = em.createNativeQuery(Constants.INSERT_STOCK, DMXClient.class);
		query.setParameter(1, dmxEntity.getId());
		query.setParameter(2, dmxEntity.getCode());
		query.setParameter(3, dmxEntity.getName());
		query.executeUpdate();
		em.getTransaction().commit();
		return dmxEntity;
	}

	@Override
	public DMXClient read(Long id, Class<DMXClient> dmxEntity) {
		Query query = em.createNativeQuery(Constants.READ_STOCK, DMXClient.class);
		query.setParameter(1, id);
		DMXClient benchmarkClientOracle = (DMXClient) query.getSingleResult();
		return benchmarkClientOracle;
		// return em.find(BenchmarkClientOracle.class, id);
	}

	@Override
	public DMXClient update(DMXClient dmxEntity) {
		em.getTransaction().begin();
		// em.merge(dmxEntity);
		Query query = em.createNativeQuery(Constants.UPDATE_STOCK, DMXClient.class);
		query.setParameter(1, dmxEntity.getCode());
		query.setParameter(2, dmxEntity.getName());
		query.setParameter(3, dmxEntity.getId());
		query.executeUpdate();
		em.getTransaction().commit();
		return dmxEntity;
	}

	@Override
	public void delete(DMXClient dmxEntity) {
		em.getTransaction().begin();
		// em.remove(dmxEntity);
		Query query = em.createNativeQuery(Constants.DELETE_STOCK, DMXClient.class);
		query.setParameter(1, dmxEntity.getId());
		query.executeUpdate();
		em.getTransaction().commit();
	}

}
