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

import com.acme.reference.impl.dto.DMXDTO;
import com.acme.reference.impl.exception.DMXClientServiceException;
import com.acme.reference.impl.service.DMXClientMngmtService;

@Api(tags = { "DMX Update Management Service" }) // swagger
													// resource
													// annotation
@Path("/dmxupdate")
public class DMXUpdateResource {

	private static final Logger logger = LogManager.getLogger(DMXUpdateResource.class);

	@Inject
	DMXClientMngmtService dmxClientMngmtService;

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	@ApiOperation(value = "Update a DMX client", notes = "Update a DMX client")
	public Response updateDMXClient(DMXDTO dmxDTO) {

		logger.debug("DMXUpdateResource is invoked");
		String reponse = null;
		try {
			reponse = dmxClientMngmtService.updateClient(dmxDTO);
		} catch (DMXClientServiceException e) {
			return Response.serverError().build();
		}

		return Response.ok(reponse, MediaType.TEXT_PLAIN).build();
	}

}
