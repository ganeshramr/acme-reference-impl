package com.acme.reference.impl.framework;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
    	//USE THIS ONLY IF THE JerseyHk2AnnotationScanner when cannot be used
    	bindFactory(EMFFactory.class).to(EntityManagerFactory.class).in(Singleton.class);
    	bindFactory(EMOracleFactory.class).to(EntityManagerFactory.class).in(Singleton.class);
    	bindFactory(AppEntityManagerFactory.class).to(EntityManager.class).in(RequestScoped.class);
    	
    
    }
}
