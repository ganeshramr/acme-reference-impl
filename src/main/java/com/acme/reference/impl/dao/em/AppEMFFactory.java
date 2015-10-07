package com.acme.reference.impl.dao.em;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.hk2.api.Factory;

import com.acme.reference.impl.rest.BechmarkClientResource;


public class AppEMFFactory implements Factory<EntityManagerFactory> {
	
	private static final Logger logger = LogManager.getLogger(BechmarkClientResource.class);
	private final EntityManagerFactory emf;
    
    public AppEMFFactory (){
        emf = Persistence.createEntityManagerFactory("hsqldb-ds");
    }
    
    public EntityManagerFactory provide() {
    	logger.debug("@@@@@@@@@@@@@@@@@@@@OPENING FACTORY FOR EMF-FACTORY@@@@@@@@@@@@@@@@@");
        return emf;
    }
    
	@Override
	public void dispose(EntityManagerFactory instance) {
		logger.debug("********************CLOSING FACTORY FOR EMF-FACTORY******************");
		emf.close();
		
	}

}
