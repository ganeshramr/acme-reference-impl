package com.acme.reference.impl.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.acme.reference.impl.di.qualifiers.DMX;

public class DMXDao {
	
	@Inject
	@DMX
	EntityManager em;

	//DO DMX stuff here
	
}
