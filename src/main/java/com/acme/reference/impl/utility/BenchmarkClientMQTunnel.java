package com.acme.reference.impl.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acme.reference.impl.exception.BechmarkClientServiceException;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class BenchmarkClientMQTunnel {

	private Channel rabbitMQChannel;
	private String queue;	
	private static final Logger logger = LogManager.getLogger(BenchmarkClientMQTunnel.class);

	
	private BenchmarkClientMQTunnel(String queue) {
		try {
			rabbitMQChannel = BenchmarkClientMQChannelFactory.open(queue);
		} catch (BechmarkClientServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		this.queue = queue;
	}
	
	
	public Channel getRabbitMQChannel() {
		return rabbitMQChannel;
	}


	public String getQueue() {
		return queue;
	}	


	public static BenchmarkClientMQTunnel newInstance(String queue) {	
		return new BenchmarkClientMQTunnel(queue);
	}

	
	
	public void publish(String message) {
		try {
			logger.info("Publish Executed for Message ***************: " + message);
			if (message == null) 
				throw new IllegalArgumentException();
			rabbitMQChannel.basicPublish("", this.queue, null, message.getBytes());
			logger.info("Message sent *for : "+ this.queue + message);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isOpen() {
		return rabbitMQChannel.isOpen();
	}

	public void purgeQueue() {
		try {
			rabbitMQChannel.queuePurge(queue);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void close() {
		try {
			rabbitMQChannel.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String receive(){
		//MQConsumer consumer = new MQConsumer(queue, rabbitMQChannel );
		try{
			  
			 logger.info("Receive Started ************: " + queue);
			 
			  Consumer consumer = new QueueingConsumer(rabbitMQChannel);			  
			  
			  rabbitMQChannel.basicConsume(this.queue, false, consumer); 
			  //consumer remains in suspend until message arrives 
			  logger.info("QueuingConsumer.Delivery************: ");		  
			  QueueingConsumer.Delivery delivery;		  
			  logger.info("QueuingConsumer.Delivery created************: ");
			  
			 // while(true){ 
				  delivery = ((QueueingConsumer) consumer).nextDelivery();
			  //}
			  logger.info("Receiver returned ***************: " + new  String(delivery.getBody()));
			  
			  rabbitMQChannel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); 
			  return delivery.toString(); 
						 
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	
	}

	public List<String> receive(int timeout) {
		List<String> messages = new ArrayList<String>();
		try {
			QueueingConsumer queueConsumer = new QueueingConsumer(rabbitMQChannel);
			rabbitMQChannel.basicConsume(queue, true, queueConsumer);
			QueueingConsumer.Delivery delivery = null;
			while (true) {
				delivery = queueConsumer.nextDelivery(timeout);
				if (delivery == null)
					break;
				messages.add(new String(delivery.getBody()));
			}
			return messages;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void disconnect() {
		try {
			rabbitMQChannel.queuePurge(queue);
			rabbitMQChannel.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}