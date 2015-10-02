package com.acme.reference.impl.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acme.reference.impl.exception.BechmarkClientServiceException;
import com.acme.reference.impl.service.BenchmarkClientMQServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "Benchmark Client MQ service" }) // swagger resource annotation
@Path("/MQ/consume/{queueName}")
public class BenchmarkClientMQConsumer {
	
	private static final Logger logger = LogManager.getLogger(BenchmarkClientMQConsumer.class);

	@Inject
	BenchmarkClientMQServices benchmarkclientmqservices;
	
	@GET	
	@Produces("application/json")
	@ApiOperation(value = "RabbitMQ ApiOperation Value", notes = "RabbitMQ ApiOperation Notes")
	public Response createConsume(@PathParam("queueName") String queueName) {

		logger.info("createConsume is invoked");
		String reponse = null;

		try {
			reponse = benchmarkclientmqservices.readClient(queueName);
			logger.info("Response received for createConsume");
		} catch (BechmarkClientServiceException e) {
			return Response.serverError().build();
		}

		return Response.ok(reponse, MediaType.TEXT_PLAIN).build();
	}

}
