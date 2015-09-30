package com.acme.reference.impl.rest;

import javax.inject.Inject;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acme.reference.impl.service.DemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "MQ service" }) // swagger resource annotation
@Path("/MQ")
public class BenchmarkClientMQResources {

	private static final Logger logger = LogManager.getLogger(BenchmarkClientMQResources.class);

	@Inject
	DemoService demoService;

	@GET
	@Produces("application/json")
	@ApiOperation(value = "Dummy Test method", notes = "Dummy method to prove deployment")
	public Response testMethod() {

		logger.debug("Resource class is invoked");
		return Response.ok(demoService.ack(), MediaType.APPLICATION_JSON).build();

	}

}
