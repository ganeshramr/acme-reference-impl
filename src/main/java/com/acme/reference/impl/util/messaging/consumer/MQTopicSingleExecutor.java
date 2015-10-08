package com.acme.reference.impl.util.messaging.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acme.reference.impl.dto.BenchmarkClientProduceMQDTO;

public class MQTopicSingleExecutor implements Runnable {
	
	Message message;
	MessageConsumer consumer;
	private static final Logger logger = LogManager.getLogger(MQTopicSingleExecutor.class);
	
	public MQTopicSingleExecutor(Message message, MessageConsumer consumer){
		this.message = message;
		this.consumer = consumer;
	}
	
	public void run(){
		boolean done = false;
		try{	
			while (!done) {
				TextMessage message = (TextMessage) consumer.receive();
				if ("done".equals(message.getText().toLowerCase())) {
					logger.info("Done receiving messages!");
					done = true;
				} else {
					logger.info("Received: " + message.getText());
					new BenchmarkClientProduceMQDTO().setMessage(message.getText().toString());
				}
			}
		}catch(JMSException e){
			logger.info("Exception receiving message!");
			e.printStackTrace();
		}
				
	}
}