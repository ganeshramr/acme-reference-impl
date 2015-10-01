package com.acme.reference.impl.service;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.hk2.api.PerLookup;
import org.jvnet.hk2.annotations.Service;

import com.acme.reference.impl.dao.BenchmarkAggregatorDAOI;
import com.acme.reference.impl.di.qualifiers.OracleDAO;
import com.acme.reference.impl.dto.BenchmarkClientOracleDTO;
import com.acme.reference.impl.exception.BechmarkClientServiceException;
import com.acme.reference.impl.model.BenchmarkClientOracle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@PerLookup
public class BechmarkClientMngmtOracleService {

	private static final Logger logger = LogManager.getLogger(BechmarkClientMngmtOracleService.class);

	private final BenchmarkAggregatorDAOI<BenchmarkClientOracle> benchmarkClientMgmtOracleDAO;

	@Inject
	public BechmarkClientMngmtOracleService(
			@OracleDAO final BenchmarkAggregatorDAOI<BenchmarkClientOracle> benchmarkClientMgmtOracleDAO) {
		this.benchmarkClientMgmtOracleDAO = benchmarkClientMgmtOracleDAO;
	}

	public String createClient(BenchmarkClientOracleDTO benchmarkClientOracleDTO)
			throws BechmarkClientServiceException {

		logger.debug("BechmarkClientMngmtOracleService: createClient is invoked");

		ObjectMapper mapper = new ObjectMapper();
		try {
			logger.debug(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(benchmarkClientOracleDTO));

			BenchmarkClientOracle benchmarkClientOracle = new BenchmarkClientOracle();
			benchmarkClientOracle.setId(Long.parseLong(mapper.writeValueAsString(benchmarkClientOracleDTO.getId())));
			benchmarkClientOracle.setCode(mapper.writeValueAsString(benchmarkClientOracleDTO.getCode()));
			benchmarkClientOracle.setName(mapper.writeValueAsString(benchmarkClientOracleDTO.getName()));

			return benchmarkClientMgmtOracleDAO.create(benchmarkClientOracle);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new BechmarkClientServiceException("CREATION Failed");
		}

	}

	public String readClient(Long id) throws BechmarkClientServiceException {

		logger.debug("BechmarkClientMngmtOracleService: readClient is invoked for id {}", id);

		try {
			return benchmarkClientMgmtOracleDAO.read(id).getName();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BechmarkClientServiceException("READ Failed");
		}

	}

}
