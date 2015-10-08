package com.acme.reference.impl.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

import com.acme.reference.impl.dto.BenchmarkClientConsumeMQDTO;
import com.acme.reference.impl.dto.BenchmarkClientProduceMQDTO;
import com.acme.reference.impl.exception.BechmarkClientServiceException;
import com.acme.reference.impl.util.messaging.consumer.MQTopicConsumer;
import com.acme.reference.impl.util.messaging.publisher.MQTopicProducer;
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
	public String createClient(BenchmarkClientConsumeMQDTO benchmarkclientconsumemqDTO) throws BechmarkClientServiceException {

		logger.debug("BenchmarkClientMQServices:createClient is invoked");		
		ObjectMapper mapper = new ObjectMapper();
		
		try {			
			MQTopicProducer benchmarkclientProducer = new MQTopicProducer(mapper.writeValueAsString(benchmarkclientconsumemqDTO.getTopicName()));
			String message = mapper.writeValueAsString(benchmarkclientconsumemqDTO.getMessage());
			logger.debug("BenchmarkClientMQServices:topicProducerConnectionSetUp is invoked" + message);
			String msg = benchmarkclientProducer.topicProducerConnectionSetUp(message);			
			return msg;

		} catch (Exception e) {
			e.printStackTrace();
			throw new BechmarkClientServiceException("CREATION Failed");
		}

	}

	public String readClient(String topicName) throws BechmarkClientServiceException {
		
		logger.debug("BechmarkClientMngmtService:readClient is invoked");		
		ObjectMapper mapper = new ObjectMapper();
		try {			
			logger.info("Queue ***************: ");			
			new MQTopicConsumer(topicName).topicConsumerConnectionSetUp();
			logger.info("Topic Message:***********: " + new BenchmarkClientProduceMQDTO().getMessage());	
			return new BenchmarkClientProduceMQDTO().getMessage();

		} catch (Exception e) {

			e.printStackTrace();
			throw new BechmarkClientServiceException("READ Failed");
		}

	}

}
