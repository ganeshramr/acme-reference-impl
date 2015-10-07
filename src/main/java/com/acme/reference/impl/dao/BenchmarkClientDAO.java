package com.acme.reference.impl.dao;

import javax.inject.Named;

import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

import com.acme.reference.impl.model.BenchmarkClient;

@Named
@Service 
@PerLookup
public class BenchmarkClientDAO extends AbstractBenchmarkAggDAO<BenchmarkClient> {
	
	public void doSomethingElse(){
		em.clear();
	}
	
}
