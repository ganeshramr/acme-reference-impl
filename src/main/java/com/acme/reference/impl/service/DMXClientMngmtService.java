package com.acme.reference.impl.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

import com.acme.reference.impl.dao.BenchmarkAggDAOI;
import com.acme.reference.impl.dto.DMXDTO;
import com.acme.reference.impl.exception.DMXClientServiceException;
import com.acme.reference.impl.model.DMXClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@PerLookup
public class DMXClientMngmtService {

	private static final Logger logger = LogManager.getLogger(DMXClientMngmtService.class);

	private final BenchmarkAggDAOI<DMXClient> dmxClientMgmtDAO;

	@Inject
	@Named("DMXDAO")
	public DMXClientMngmtService(final BenchmarkAggDAOI<DMXClient> dmxClientDAO) {
		this.dmxClientMgmtDAO = dmxClientDAO;
	}

	public String createClient(DMXDTO dmxDTO) throws DMXClientServiceException {

		logger.debug("DMXClientMngmtService: createClient is invoked");

		ObjectMapper mapper = new ObjectMapper();
		try {
			logger.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dmxDTO));

			DMXClient dmxClient = new DMXClient();
			dmxClient.setId(Long.parseLong(mapper.writeValueAsString(dmxDTO.getId())));
			dmxClient.setCode(mapper.writeValueAsString(dmxDTO.getCode()));
			dmxClient.setName(mapper.writeValueAsString(dmxDTO.getName()));

			return dmxClientMgmtDAO.create(dmxClient).getId().toString();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new DMXClientServiceException("CREATION Failed");
		}

	}

	public String readClient(Long id) throws DMXClientServiceException {

		logger.debug("DMXClientMngmtService: readClient is invoked for id: ", id);

		try {
			return dmxClientMgmtDAO.read(id, DMXClient.class).getName();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DMXClientServiceException("READ Failed");
		}

	}

	public String updateClient(DMXDTO dmxDTO) throws DMXClientServiceException {

		logger.debug("DMXClientMngmtService: updateClient is invoked");

		ObjectMapper mapper = new ObjectMapper();
		try {
			logger.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dmxDTO));

			DMXClient dmxClient = new DMXClient();
			dmxClient.setId(Long.parseLong(mapper.writeValueAsString(dmxDTO.getId())));
			dmxClient.setCode(mapper.writeValueAsString(dmxDTO.getCode()));
			dmxClient.setName(mapper.writeValueAsString(dmxDTO.getName()));

			return dmxClientMgmtDAO.update(dmxClient).getId().toString();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new DMXClientServiceException("UPDATE Failed");
		}

	}

	public String deleteClient(Long id) throws DMXClientServiceException {

		logger.debug("DMXClientMngmtService: readClient is invoked for id: ", id);
		ObjectMapper mapper = new ObjectMapper();
		try {
			DMXClient dmxClient = new DMXClient();
			dmxClient.setId(Long.parseLong(mapper.writeValueAsString(id)));
			dmxClientMgmtDAO.delete(dmxClient);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			throw new DMXClientServiceException("DELETE Failed");
		}

	}

}
