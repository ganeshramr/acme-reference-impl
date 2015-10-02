package com.acme.reference.impl.utility;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

public class BenchmarkClientMQConsumer {
	
	private BenchmarkClientMQTunnel tunnel;
	private static final Logger logger = LogManager.getLogger(BenchmarkClientMQConsumer.class);

	public BenchmarkClientMQConsumer(String queue) {
		logger.info("BenchmarkClientMQConsumer************: " + queue);
		this.tunnel = BenchmarkClientMQTunnel.newInstance(queue);
	}

	public BenchmarkClientMQConsumer(BenchmarkClientMQTunnel tunnel, String queue) {
		this.tunnel = tunnel;		
	}

	public String receive() throws java.lang.InterruptedException, ShutdownSignalException, ConsumerCancelledException {
		return tunnel.receive();
	}

	public List<String> receive(int timeout) {
		return tunnel.receive(timeout);
	}

	public void disconnect() {
		tunnel.disconnect();
	}

	public boolean isConnected() {
		return tunnel.isOpen();
	}

	public void purgeQueue() {
		tunnel.purgeQueue();
	}


}
