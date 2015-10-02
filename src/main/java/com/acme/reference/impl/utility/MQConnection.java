package com.acme.reference.impl.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acme.reference.impl.exception.BechmarkClientServiceException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MQConnection {
	
	private static MQConnection singleConnection;
	private static final Logger logger = LogManager.getLogger(BenchmarkClientMQChannelFactory.class);
	private static Connection connection;
	
	private MQConnection(){	
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost"); // Need to get it from properties
		try{
			connection = factory.newConnection();		
			
		}catch(Exception e){
			e.printStackTrace();			
		}
	}
	
	public static final Connection connectionInstance() throws BechmarkClientServiceException{		
				
		if (singleConnection == null){		
			singleConnection = new MQConnection();
			logger.info("Returned new Connection object");
			return connection;
		}else{
			logger.info("Returned Old Connection object");
			return connection;
		}		
	}
}
