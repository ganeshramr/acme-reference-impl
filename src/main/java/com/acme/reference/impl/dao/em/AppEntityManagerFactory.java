package com.acme.reference.impl.dao.em;

import java.io.Closeable;
import java.io.IOException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.server.CloseableService;

import com.acme.reference.impl.di.qualifiers.Benchmark;
import com.acme.reference.impl.rest.BechmarkClientResource;


public class AppEntityManagerFactory implements Factory<EntityManager> {
  
	private EntityManager em;
	private EntityManagerFactory emf;
	private CloseableService closeableService;
	private static final Logger logger = LogManager.getLogger(BechmarkClientResource.class);

    @Inject
    public AppEntityManagerFactory (@Benchmark EntityManagerFactory emf,CloseableService closeableService){
        this.emf =emf;
        this.closeableService = closeableService;
    }
    
    
    public EntityManager provide() {
    		
    	em = emf.createEntityManager();
    	logger.debug("-------------OPENING EM-------------HASH:"+em.hashCode());
        closeableService.add(new Closeable() {

            @Override
            public void close() throws IOException {
                if(em.isOpen()) {
                	logger.debug("xxxxxxxxxxxxxxxxxCLOSING EMxxxxxxxxxxxxxxxxxHASH:"+em.hashCode());
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
