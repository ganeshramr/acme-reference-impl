package com.acme.reference.impl.util.messaging.listener;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acme.reference.impl.util.messaging.consumer.MQTopicSingleExecutor;

public class MQListener implements MessageListener {

	Connection connection;
	MessageConsumer consumer;
	private Executor threadPool = Executors.newCachedThreadPool();
	private static final Logger logger = LogManager.getLogger(MQListener.class);

	public MQListener(Connection connection, MessageConsumer consumer) {
		this.connection = connection;
		this.consumer = consumer;
	}	

	@Override
	public void onMessage(Message message) {
		logger.info("You are in onMessage listener.");
		execute(message);
		TextMessage textMessage = (TextMessage) message;
		
		try {
			System.out.println(
					"Consumer " + Thread.currentThread().getName() + " received message: " + textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	};

	public void execute(Message msgs) {
		threadPool.execute(new MQTopicSingleExecutor(msgs, consumer));
		logger.info("You are in executor method for listener. And your message is " + msgs);		
//		try {
//			connection.stop();
//		} catch (JMSException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}


