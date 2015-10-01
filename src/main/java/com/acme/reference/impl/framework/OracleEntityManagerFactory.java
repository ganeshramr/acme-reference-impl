package com.acme.reference.impl.framework;

import java.io.Closeable;
import java.io.IOException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.server.CloseableService;

import com.acme.reference.impl.di.qualifiers.OracleDAO;

public class OracleEntityManagerFactory implements Factory<EntityManager> {

	private EntityManager em;
	private EntityManagerFactory emf;
	private CloseableService closeableService;
	private static final Logger logger = LogManager.getLogger(OracleEntityManagerFactory.class);

	@Inject
	public OracleEntityManagerFactory(@OracleDAO EntityManagerFactory emf, CloseableService closeableService) {
		this.emf = emf;
		this.closeableService = closeableService;
	}

	public EntityManager provide() {

		em = emf.createEntityManager();
		logger.debug("------------- OPENING ORACLE EM ------------- HASH: " + em.hashCode());
		closeableService.add(new Closeable() {

			@Override
			public void close() throws IOException {
				if (em.isOpen()) {
					logger.debug("xxxxxxxxxxxxxxxxx CLOSING ORACLE EM xxxxxxxxxxxxxxxxx HASH: " + em.hashCode());
					em.close();
				}
			}
		});
		return em;
	}

	@Override
	public void dispose(EntityManager instance) {

	}

}
