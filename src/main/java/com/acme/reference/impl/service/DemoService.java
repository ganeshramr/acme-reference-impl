package com.acme.reference.impl.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jvnet.hk2.annotations.Service;

import com.acme.reference.impl.dto.response.DemoResponse;

@Service
public class DemoService {
	
	private static final Logger logger = LogManager.getLogger(DemoService.class);
	
	public DemoResponse ack(){
		logger.debug("Test service is invoked now");
		DemoResponse demoResponse = new DemoResponse();
		demoResponse.setResponseMessage("The call is successfull");
		
		return demoResponse;

	}

}
