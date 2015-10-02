package com.acme.reference.impl.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acme.reference.impl.dto.BenchmarkClientMQDTO;
import com.acme.reference.impl.exception.BechmarkClientServiceException;
import com.acme.reference.impl.service.BenchmarkClientMQServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "Benchmark Client MQ service" }) // swagger resource annotation
@Path("/MQ/produce")
public class BenchmarkClientMQProducer {

	private static final Logger logger = LogManager.getLogger(BenchmarkClientMQProducer.class);

	@Inject
	BenchmarkClientMQServices benchmarkclientmqservices;

	@POST	
	@Consumes("application/json")
	@Produces("text/plain")
	@ApiOperation(value = "RabbitMQ ApiOperation Value", notes = "RabbitMQ ApiOperation Notes")
	public Response createProduce(BenchmarkClientMQDTO benchmarkclientmqDTO) {

		logger.debug("createProduce is invoked");
		String reponse = null;

		try {
			reponse = benchmarkclientmqservices.createClient(benchmarkclientmqDTO);
		} catch (BechmarkClientServiceException e) {
			return Response.serverError().build();
		}

		return Response.ok(reponse, MediaType.TEXT_PLAIN).build();
	}

}
