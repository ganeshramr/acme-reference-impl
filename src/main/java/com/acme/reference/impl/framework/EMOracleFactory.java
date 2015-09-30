package com.acme.reference.impl.framework;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.hk2.api.Factory;

import com.acme.reference.impl.rest.BechmarkClientResource;

public class EMOracleFactory implements Factory<EntityManagerFactory> {
	
	private static final Logger logger = LogManager.getLogger(EMOracleFactory.class);
	private final EntityManagerFactory emf;
    
    public EMOracleFactory (){
        emf = Persistence.createEntityManagerFactory("oracle-db-ds");
    }
    
    public EntityManagerFactory provide() {
    	logger.debug("@@@@@@@@@@@@@@@@@@@@OPENING FACTORY FOR EM-Oracle-FACTORY@@@@@@@@@@@@@@@@@");
        return emf;
    }
    
	@Override
	public void dispose(EntityManagerFactory instance) {
		logger.debug("********************CLOSING FACTORY FOR EM-Oracle-FACTORY******************");
		emf.close();
		
	}

}
