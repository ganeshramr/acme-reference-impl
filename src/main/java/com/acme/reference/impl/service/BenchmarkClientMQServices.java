package com.acme.reference.impl.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

import com.acme.reference.impl.dto.BenchmarkClientMQDTO;
import com.acme.reference.impl.exception.BechmarkClientServiceException;
import com.acme.reference.impl.utility.BenchmarkClientMQConsumer;
import com.acme.reference.impl.utility.BenchmarkClientMQProducer;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@PerLookup
public class BenchmarkClientMQServices {

	private static final Logger logger = LogManager.getLogger(BenchmarkClientMQServices.class);

	/*
	 * @Inject public BechmarkClientMngmtService(@InMemoryDAO final
	 * BenchmarkAggregatorDAOI<BenchmarkClient> benchmarkClientMgmtDAO){
	 * 
	 * this.benchmarkClientMgmtDAO = benchmarkClientMgmtDAO; }
	 */
	public String createClient(BenchmarkClientMQDTO benchmarkclientmqDTO) throws BechmarkClientServiceException {

		logger.debug("BenchmarkClientMQServices:createClient is invoked");
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			BenchmarkClientMQProducer benchmarkclientProducer = new BenchmarkClientMQProducer(mapper.writeValueAsString(benchmarkclientmqDTO.getQueueName()));
			String message = benchmarkclientmqDTO.getMessage();
			logger.debug("BenchmarkClientMQServices:createClient is invoked" + message);
			benchmarkclientProducer.send(mapper.writeValueAsString(message));			
			return "Success";

		} catch (Exception e) {

			e.printStackTrace();
			throw new BechmarkClientServiceException("CREATION Failed");
		}

	}

	public String readClient(String queueName) throws BechmarkClientServiceException {

		logger.debug("BechmarkClientMngmtService:readClient is invoked");
		
		ObjectMapper mapper = new ObjectMapper();
		try {				
			
			logger.info("Queue ***************: " + queueName);			
			return new BenchmarkClientMQConsumer(queueName).receive().toString();

		} catch (Exception e) {

			e.printStackTrace();
			throw new BechmarkClientServiceException("READ Failed");
		}

	}

}
