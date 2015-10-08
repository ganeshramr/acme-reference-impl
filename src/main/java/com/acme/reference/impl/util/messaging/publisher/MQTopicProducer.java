package com.acme.reference.impl.util.messaging.publisher;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.acme.reference.impl.util.messaging.Context.ContextSearch;
import com.acme.reference.impl.util.messaging.connection.MQConnectionFactory;
import com.acme.reference.impl.util.messaging.connection.MQConnectionSession;
import com.acme.reference.impl.util.messaging.consumer.MQTopicConsumer;

public class MQTopicProducer {
	
	public String topic_name;
	Connection connection;	
	private static final Logger LOGGER = Logger.getLogger(MQTopicConsumer.class);
	
	public MQTopicProducer(String topicName){
		this.topic_name = "dynamicTopics/".concat(topicName);		
	}
	
	public String topicProducerConnectionSetUp(String message){
		
		try {
			Context context = ContextSearch.searchName();
			connection = MQConnectionFactory.getConnection();		
			LOGGER.warn("Connection Object: " + connection);
			
			Session session = MQConnectionSession.getSession(connection);
			Destination destination = (Destination) context.lookup(topic_name);
			
			MessageProducer producer = session.createProducer(destination);		

			TextMessage msg = session.createTextMessage();
			connection.start();
			msg.setText(message);
						
			producer.send(msg);

			producer.close();
			session.close();
			//connection.close();			
			
		} catch (NamingException | JMSException e) {			
			MQConnectionFactory.closeConnection(connection);
			LOGGER.warn("Error in topicProducerConnectionSetUp");
			e.printStackTrace();
		}
		return "Success";
	}
	

}
