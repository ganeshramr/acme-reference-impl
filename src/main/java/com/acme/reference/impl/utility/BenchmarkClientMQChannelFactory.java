package com.acme.reference.impl.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class BenchmarkClientMQChannelFactory {
	
	private static final Logger logger = LogManager.getLogger(BenchmarkClientMQChannelFactory.class);
	
	public static Channel open(String queue) {

		try {

			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost"); // Need to get it from properties

			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.queueDeclare(queue.replaceAll("\"", ""), false, false, false, null);
			logger.info("open Executed ***************: " + queue);

			return channel;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
