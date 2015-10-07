package com.acme.reference.impl.framework;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

import com.acme.reference.impl.dao.em.AppEMFFactory;
import com.acme.reference.impl.dao.em.AppEntityManagerFactory;
import com.acme.reference.impl.dao.em.DMXEMFFactory;
import com.acme.reference.impl.dao.em.DMXEntityManagerFactory;
import com.acme.reference.impl.di.qualifiers.Benchmark_;
import com.acme.reference.impl.di.qualifiers.DMX_;

public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
    	//USE THIS ONLY IF THE JerseyHk2AnnotationScanner when cannot be used
    	bindFactory(AppEMFFactory.class).to(EntityManagerFactory.class).qualifiedBy(new Benchmark_()).in(Singleton.class);
    	bindFactory(DMXEMFFactory.class).to(EntityManagerFactory.class).qualifiedBy(new DMX_()).in(Singleton.class);
    	bindFactory(AppEntityManagerFactory.class).to(EntityManager.class).qualifiedBy(new Benchmark_()).in(RequestScoped.class);
    	bindFactory(DMXEntityManagerFactory.class).to(EntityManager.class).qualifiedBy(new DMX_()).in(RequestScoped.class);
    	
    
    }
}
