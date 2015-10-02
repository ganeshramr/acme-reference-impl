package com.acme.reference.impl.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acme.reference.impl.exception.BechmarkClientServiceException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


public class BenchmarkClientMQChannelFactory {

	private static final Logger logger = LogManager.getLogger(BenchmarkClientMQChannelFactory.class);

	public static Channel open(String queue) throws BechmarkClientServiceException {
		try{

		Connection connection = MQConnection.connectionInstance();
		Channel channel = connection.createChannel();

		channel.queueDeclare(queue, false, false, false, null);
		logger.info("open Executed ***************: " + queue);

		return channel;
		}catch(Exception e){
			e.printStackTrace();
			throw new BechmarkClientServiceException("Channel CREATION Failed");
		}
	}
}
