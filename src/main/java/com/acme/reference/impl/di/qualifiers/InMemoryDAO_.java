package com.acme.reference.impl.di.qualifiers;

/* I have to do this to bind qualifiers */

import org.glassfish.hk2.api.AnnotationLiteral;

@InMemoryDAO
public class InMemoryDAO_ extends AnnotationLiteral<InMemoryDAO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
