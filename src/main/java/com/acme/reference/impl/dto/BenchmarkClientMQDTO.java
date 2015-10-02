package com.acme.reference.impl.dto;

public class BenchmarkClientMQDTO {

	private String queueName;	
	private String message;

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message.toString();
	}
}
