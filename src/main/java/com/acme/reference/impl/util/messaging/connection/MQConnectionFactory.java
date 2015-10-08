package com.acme.reference.impl.util.messaging.connection;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MQConnectionFactory {	
	private static final Logger logger = LogManager.getLogger(MQConnectionFactory.class);

	private static ConnectionFactory connectionFactory;
	private static Connection connection;
	private static MQConnectionFactory singlefactory;

	
	@SuppressWarnings({"static-access" })
	private MQConnectionFactory() throws JMSException {	        
	        this.connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);	        
			this.connection = connectionFactory.createConnection();
		}

	
	public static Connection getConnection() throws JMSException {		
		if (null == singlefactory){
			synchronized(MQConnectionFactory.class) {
				if (null == singlefactory){
					singlefactory = new MQConnectionFactory() ;					
				}				
			}			
		}	
		return connection;
	}

	
	public static void closeConnection(Connection connection) {
		try {
			connection.stop();
			connection.close();
		} catch (JMSException e) {
			logger.warn("Error closing JMS connection");
		}
	}
}
