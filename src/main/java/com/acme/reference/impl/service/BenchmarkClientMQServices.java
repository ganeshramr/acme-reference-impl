package com.acme.reference.impl.service;

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

		logger.debug("BenchmarkClientMQServices:createClient  is invoked");

		ObjectMapper mapper = new ObjectMapper();
		try {

			logger.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(benchmarkclientmqDTO));

			String queue = mapper.writeValueAsString(benchmarkclientmqDTO.getQueueName());
			String host = mapper.writeValueAsString(benchmarkclientmqDTO.getHostName());
			String message = mapper.writeValueAsString(benchmarkclientmqDTO.getMessage());
			
			BenchmarkClientMQProducer bcProducer = new BenchmarkClientMQProducer(queue, host);
			bcProducer.send(message);			

			return mapper.readValue("Success", String.class);

		} catch (Exception e) {

			e.printStackTrace();
			throw new BechmarkClientServiceException("CREATION Failed");
		}

	}

	public String readClient(BenchmarkClientMQDTO benchmarkclientmqDTO) throws BechmarkClientServiceException {

		logger.debug("BechmarkClientMngmtService:readClient is invoked");
		
		ObjectMapper mapper = new ObjectMapper();
		try {

			String queue = mapper.writeValueAsString(benchmarkclientmqDTO.getQueueName());
			String host = mapper.writeValueAsString(benchmarkclientmqDTO.getHostName());
			
			BenchmarkClientMQConsumer benchmarkclientmqConsumer = new BenchmarkClientMQConsumer(queue, host);
			
			return mapper.readValue(benchmarkclientmqConsumer.receive(), String.class);

		} catch (Exception e) {

			e.printStackTrace();
			throw new BechmarkClientServiceException("READ Failed");
		}

	}

}
