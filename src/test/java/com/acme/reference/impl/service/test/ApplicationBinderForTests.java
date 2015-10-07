package com.acme.reference.impl.service.test;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

import com.acme.reference.impl.dao.BenchmarkAggDAOI;
import com.acme.reference.impl.dao.BenchmarkClientDAO;
import com.acme.reference.impl.dao.em.AppEMFFactory;
import com.acme.reference.impl.dao.em.AppEntityManagerFactory;
import com.acme.reference.impl.dao.em.DMXEMFFactory;
import com.acme.reference.impl.dao.em.DMXEntityManagerFactory;
import com.acme.reference.impl.di.qualifiers.Benchmark_;
import com.acme.reference.impl.di.qualifiers.DMX_;
import com.acme.reference.impl.model.BenchmarkClient;
import com.acme.reference.impl.service.BechmarkClientMngmtService;
import com.acme.reference.impl.service.DemoService;

/*
 * Add ONLY what is needed by tests
 * 
 * */
public class ApplicationBinderForTests extends AbstractBinder {
    @Override
    protected void configure() {
    	//USE THIS ONLY FOR TESTS
       bind(DemoService.class).to(DemoService.class);
       bind(BechmarkClientMngmtService.class).to(BechmarkClientMngmtService.class).in(PerLookup.class);
       bind(BenchmarkClientDAO.class).named("BenchmarkClientDAO").to(new TypeLiteral<BenchmarkAggDAOI<BenchmarkClient>>() {}).in(PerLookup.class);
       bindFactory(AppEMFFactory.class).to(EntityManagerFactory.class).qualifiedBy(new Benchmark_()).in(Singleton.class);
       bindFactory(DMXEMFFactory.class).to(EntityManagerFactory.class).qualifiedBy(new DMX_()).in(Singleton.class);
       bindFactory(AppEntityManagerFactory.class).to(EntityManager.class).qualifiedBy(new Benchmark_()).in(RequestScoped.class);
       bindFactory(DMXEntityManagerFactory.class).to(EntityManager.class).qualifiedBy(new DMX_()).in(RequestScoped.class);
   	
    }
}
