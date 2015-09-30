package com.acme.reference.impl.utility;

import java.util.List;

public class BenchmarkClientMQProducer {
	
	private BenchmarkClientMQTunnel tunnel;

	public BenchmarkClientMQProducer(String queue, String host) {
		this.tunnel = BenchmarkClientMQTunnel.newInstance(queue, host);
	}

	public void send(String message) {
		tunnel.publish(message);
	}

	public void send(List<String> messages) {
		for (String message : messages)
			send(message);
	}

	public boolean isConnected() {
		if (tunnel == null)
			return false;
		return tunnel.isOpen();
	}

	public void disconnect() {
		if (tunnel != null)
			tunnel.close();
	}

	public void purgeQueue() {
		tunnel.purgeQueue();
	}

}
