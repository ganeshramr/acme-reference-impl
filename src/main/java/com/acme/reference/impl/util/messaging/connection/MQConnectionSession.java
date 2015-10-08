package com.acme.reference.impl.util.messaging.connection;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.log4j.Logger;

public class MQConnectionSession {		
	
	private static final Logger LOGGER = Logger.getLogger(MQConnectionSession.class);
	
	public static Session getSession(Connection connection){				
		Session session = null;
		try {
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			LOGGER.error("Exception occured", e);
			e.printStackTrace();
		}				
		return session;		
	}
}
