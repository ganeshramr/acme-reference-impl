package com.acme.reference.impl.service.test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.acme.reference.impl.dto.BenchmarkClientDTO;
import com.acme.reference.impl.dto.DeliveryChannelDTO;
import com.acme.reference.impl.rest.BechmarkClientDetailResource;
import com.acme.reference.impl.rest.BechmarkClientResource;

@Test

public class TestBenchmarkClientMngmtServiceInt extends JerseyTestNg.ContainerPerClassTest {

	

	@Test
	public void createClient() {

		BenchmarkClientDTO benchmarkClientDTO = new BenchmarkClientDTO();

		benchmarkClientDTO.setName("acme");
		benchmarkClientDTO.setDescription("Demo Org");

		List<String> authorizedBenchMarks = new ArrayList<>();
		authorizedBenchMarks.add("msci");
		authorizedBenchMarks.add("s&p");

		benchmarkClientDTO.setAuthorizedBenchMarks(authorizedBenchMarks);

		List<DeliveryChannelDTO> deliveryChannels = new ArrayList<>();

		DeliveryChannelDTO ftp = new DeliveryChannelDTO();

		ftp.setName("acme-ftp");
		ftp.setType("ftp");
		ftp.setURL("ftp://acme-ftp.com:21");
		ftp.setUsename("foo");
		ftp.setPassword("*****");

		deliveryChannels.add(ftp);

		benchmarkClientDTO.setDeliveryChannels(deliveryChannels);

		final String reponse = target("benchmarkclient").request().post(
				Entity.json(benchmarkClientDTO), String.class);
		//Assert.assertEquals(reponse, "1");
		
		final BenchmarkClientDTO readResp = target("/benchmarkclient/"+reponse).request().get(BenchmarkClientDTO.class);
		Assert.assertNotNull(readResp);
		Assert.assertEquals(readResp.getName(),"acme");
	}
	
	
	
	/**
	 * Register the Resource and TestBinder in the Application
	 */
	@Override
	protected Application configure() {
		return new ResourceConfig() {
			{   
				
				register(new ApplicationBinderForTests());
				register(BechmarkClientResource.class);
				register(BechmarkClientDetailResource.class);

			}
		};
	}

}
