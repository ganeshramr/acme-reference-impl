package com.acme.reference.impl.utility;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class BenchmarkClientMQChannelFactory {
	
	public static Channel open(String queue, String host) {

		try {

			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(host);

			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.queueDeclare(queue, false, false, false, null);

			return channel;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
