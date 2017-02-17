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

import com.acme.reference.impl.exception.DMXClientServiceException;
import com.acme.reference.impl.service.DMXClientMngmtService;

@Api(tags = { "DMX Read Management Service" }) // swagger resource
												// annotation
@Path("/dmxread/{id}")
public class DMXReadResource {

	private static final Logger logger = LogManager.getLogger(DMXReadResource.class);

	@Inject
	DMXClientMngmtService dmxClientMngmtService;

	@GET
	@Produces("application/json")
	@ApiOperation(value = "Read a DMX client", notes = "Read a DMX client")
	public Response readDMXClient(@PathParam("id") Long id) {

		logger.debug("DMXReadResource is invoked for id: ", id);
		String reponse = null;
		try {
			reponse = dmxClientMngmtService.readClient(id);
		} catch (DMXClientServiceException e) {
			return Response.serverError().build();
		}

		return Response.ok(reponse, MediaType.APPLICATION_JSON).build();
	}

}
