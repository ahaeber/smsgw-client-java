package com.intele.chimera.client.request;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.intele.chimera.client.request.GatewayRequest.Builder;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.Request;

/**
 * Tests for the builder.
 * 
 * @author gre
 * @version 1.0		Dec 17, 2015
 */
public class GatewayRequestTest {

	@Test
	public void testBuilderWithRequiredParameters() {
		int serviceId = 123;
		String username = "username";
		String password = "password";
		
		Builder builder = new GatewayRequest.Builder(serviceId, username, password);

		Request request = builder.build().getRequest();
		
		assertThat(request.getUsername()).isEqualTo(username);
		assertThat(request.getPassword()).isEqualTo(password);
		assertThat(request.getBatchReference()).isNull();
		assertThat(request.getMessage()).isNullOrEmpty();
	}
	
	@Test
	public void testBuilderWithAllOptionalParameters() {
		int serviceId = 123;
		String username = "username";
		String password = "password";
		String batchReference = "batchReference";
		Sms sms = new Sms.Builder("recipient", "content").build();
		
		Builder builder = new GatewayRequest.Builder(serviceId, username, password);
		builder.withBatchReference(batchReference);
		
		GatewayRequest gatewayRequest = builder.build();
		
		gatewayRequest.addMessage(sms);
		
		Request request = gatewayRequest.getRequest();
		
		assertThat(request.getUsername()).isEqualTo(username);
		assertThat(request.getPassword()).isEqualTo(password);
		assertThat(request.getBatchReference()).isEqualTo(batchReference);
		assertThat(request.getMessage()).containsExactly(sms.getMessage());
	}
}
