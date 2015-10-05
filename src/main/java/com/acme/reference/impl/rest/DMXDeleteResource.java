package com.acme.reference.impl.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acme.reference.impl.exception.DMXClientServiceException;
import com.acme.reference.impl.service.DMXClientMngmtService;

@Api(tags = { "DMX Delete Management Service" }) // swagger resource
												// annotation
@Path("/dmxdelete/{id}")
public class DMXDeleteResource {

	private static final Logger logger = LogManager.getLogger(DMXDeleteResource.class);

	@Inject
	DMXClientMngmtService dmxClientMngmtService;

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	@ApiOperation(value = "Delete a DMX client", notes = "Delete a DMX client")
	public Response deleteDMXClient(@PathParam("id") Long id) {

		logger.debug("DMXDeleteResource is invoked for id: ", id);
		String reponse = null;
		try {
			reponse = dmxClientMngmtService.deleteClient(id);
		} catch (DMXClientServiceException e) {
			return Response.serverError().build();
		}

		return Response.ok(reponse, MediaType.APPLICATION_JSON).build();
	}

}
