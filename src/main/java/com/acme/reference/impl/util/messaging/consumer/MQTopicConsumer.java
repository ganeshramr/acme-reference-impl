package com.acme.reference.impl.util.messaging.consumer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.acme.reference.impl.util.messaging.Context.ContextSearch;
import com.acme.reference.impl.util.messaging.connection.MQConnectionFactory;
import com.acme.reference.impl.util.messaging.connection.MQConnectionSession;
import com.acme.reference.impl.util.messaging.listener.MQListener;

public class MQTopicConsumer {
	
	public String topic_name;
	Connection connection;
	private static final Logger LOGGER = Logger.getLogger(MQTopicConsumer.class);
	
	public MQTopicConsumer(String topicName){
		this.topic_name = "dynamicTopics/".concat(topicName);
	}
	
	public void topicConsumerConnectionSetUp(){
		
		try {
			Context context = ContextSearch.searchName();
			connection = MQConnectionFactory.getConnection();			
			
			Session session = MQConnectionSession.getSession(connection);
			Destination destination = (Destination) context.lookup(topic_name);
			
			MessageConsumer consumer = session.createConsumer(destination);
			
			consumer.setMessageListener(new MQListener(connection, consumer));
			
			connection.start();
			
		} catch (NamingException | JMSException e) {
			// TODO Auto-generated catch block			
			MQConnectionFactory.closeConnection(connection);
			LOGGER.warn("Error in topicConsumerConnectionSetUp");
			e.printStackTrace();
		}
		finally{
			//MQConnectionFactory.closeConnection(connection);
			LOGGER.info("Reaching at Topic consumer final block ");
		}
	}
}
