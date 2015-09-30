package com.acme.reference.impl.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acme.reference.impl.dto.BenchmarkClientOracleDTO;
import com.acme.reference.impl.exception.BechmarkClientServiceException;
import com.acme.reference.impl.service.BechmarkClientMngmtOracleService;

@Api(tags = { "Benchmark Read Management Service Oracle" }) // swagger resource
															// annotation
@Path("/benchmarkclientoracle/{id}")
public class BechmarkReadOracleResource {

	private static final Logger logger = LogManager.getLogger(BechmarkReadOracleResource.class);

	@Inject
	BechmarkClientMngmtOracleService bechmarkClientMngmtOracleService;

	@GET
	@Produces("application/json")

	@ApiOperation(value = "Read a benchmark client", notes = "Read a benchmark client")
	public Response readBenchmarkClient(@PathParam("id") Long id) {

		logger.debug("readBenchmarkClient is invoked for client id {}", id);
		BenchmarkClientOracleDTO reponse = null;
		try {
			reponse = bechmarkClientMngmtOracleService.readClient(id);
		} catch (BechmarkClientServiceException e) {
			return Response.serverError().build();
		}

		return Response.ok(reponse, MediaType.APPLICATION_JSON).build();
	}

}
