package com.acme.reference.impl.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acme.reference.impl.dto.BenchmarkClientOracleDTO;
import com.acme.reference.impl.exception.BechmarkClientServiceException;
import com.acme.reference.impl.service.BechmarkClientMngmtOracleService;

@Api(tags = { "Benchmark Create Management Service Oracle" }) // swagger
																// resource
																// annotation
@Path("/benchmarkclientoracle")
public class BechmarkCreateOracleResource {

	private static final Logger logger = LogManager.getLogger(BechmarkCreateOracleResource.class);

	@Inject
	BechmarkClientMngmtOracleService bechmarkClientMngmtOracleService;

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	@ApiOperation(value = "Create a benchmark client for Oracle", notes = "Create a benchmark client for Oracle")
	public Response createBenchmarkClientOracle(BenchmarkClientOracleDTO benchmarkClientOracleDTO) {

		logger.debug("createBenchmarkClientOracle is invoked");
		String reponse = null;
		try {
			reponse = bechmarkClientMngmtOracleService.createClient(benchmarkClientOracleDTO);
		} catch (BechmarkClientServiceException e) {
			return Response.serverError().build();
		}

		return Response.ok(reponse, MediaType.TEXT_PLAIN).build();
	}

}
