package com.acme.reference.impl.util.messaging.Context;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextSearch {

	public static Context searchName() throws NamingException{			
		
		Properties props=new Properties();			
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.put(Context.PROVIDER_URL,"tcp://hostname:61616");			
		
		Context context = new InitialContext(props);	
		return context;
		}
	}