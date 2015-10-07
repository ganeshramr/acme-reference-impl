package com.acme.reference.impl.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

import com.acme.reference.impl.dao.BenchmarkAggDAOI;
import com.acme.reference.impl.dto.BenchmarkClientDTO;
import com.acme.reference.impl.exception.BechmarkClientServiceException;
import com.acme.reference.impl.model.BenchmarkClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@PerLookup
public class BechmarkClientMngmtService {
	
	private static final Logger logger = LogManager.getLogger(BechmarkClientMngmtService.class);	
	
	
	private final BenchmarkAggDAOI<BenchmarkClient> benchmarkClientMgmtDAO;
	
	@Inject 
	public BechmarkClientMngmtService(@Named ("BenchmarkClientDAO") final BenchmarkAggDAOI<BenchmarkClient> benchmarkClientDAO){
		
		this.benchmarkClientMgmtDAO = benchmarkClientDAO; 
	}
	
	
	public String createClient(BenchmarkClientDTO benchmarkClientDTO) throws BechmarkClientServiceException {
		
		logger.debug("BechmarkClientMngmtService:createClient  is invoked");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			logger.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(benchmarkClientDTO));
			
			BenchmarkClient benchmarkClient = new BenchmarkClient();
			benchmarkClient.setDetails(mapper.writeValueAsString(benchmarkClientDTO));
			
			return String.valueOf(benchmarkClientMgmtDAO.create(benchmarkClient).getId());
			
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
			throw new BechmarkClientServiceException("CREATION Failed");
		}
		
		
	}
	
	
public BenchmarkClientDTO readClient(Long clientId) throws BechmarkClientServiceException {
		
		logger.debug("BechmarkClientMngmtService:readClient is invoked for id {}",clientId);
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(benchmarkClientMgmtDAO.read(clientId,BenchmarkClient.class).getDetails().getBytes(), BenchmarkClientDTO.class);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new BechmarkClientServiceException("READ Failed");
		}
		
		
	}

}
