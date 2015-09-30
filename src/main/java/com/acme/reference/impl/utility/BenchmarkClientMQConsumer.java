package com.acme.reference.impl.utility;

import java.util.List;

public class BenchmarkClientMQConsumer {
	
	private BenchmarkClientMQTunnel tunnel;

	public BenchmarkClientMQConsumer(String queue, String host) {
		this.tunnel = BenchmarkClientMQTunnel.newInstance(queue, host);
	}

	public BenchmarkClientMQConsumer(BenchmarkClientMQTunnel tunnel, String queue) {
		this.tunnel = tunnel;
	}

	public String receive() {
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
