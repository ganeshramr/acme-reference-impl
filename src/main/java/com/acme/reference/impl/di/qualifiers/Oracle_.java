package com.acme.reference.impl.di.qualifiers;

/* I have to do this to bind qualifiers */

import org.glassfish.hk2.api.AnnotationLiteral;

public class Oracle_ extends AnnotationLiteral<OracleDAO>implements OracleDAO {

	private static final long serialVersionUID = 1L;

}
