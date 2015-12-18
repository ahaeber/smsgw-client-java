package com.intele.chimera.client.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.intele.chimera.client.request.GatewayRequest;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.Request;
import com.intele.chimera.gw.xsd.smsgateway.response._2013._02.Response;

/**
 * @author gre
 * @version 1.0		Dec 18, 2015
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class GatewayClientImplTest {

	private GatewayClientImpl gatewayClient;
	@Mock
	private Client client;
	@Captor
	private ArgumentCaptor<Entity<Request>> requestCaptor;
	private String target = "target";
	private MediaType mediaType = MediaType.APPLICATION_XML_TYPE;

	@Test
	public void testSendRequest() {
		WebTarget webTarget = mock(WebTarget.class);
		Builder builder = mock(Builder.class);
		Invocation invocation = mock(Invocation.class);

		Request request = new Request();
		Response response = new Response();

		when(client.target(anyString())).thenReturn(webTarget);
		when(webTarget.request()).thenReturn(builder);
		when(builder.accept(any(MediaType.class))).thenReturn(builder);
		when(builder.buildPost(any(Entity.class))).thenReturn(invocation);
		when(invocation.invoke(Response.class)).thenReturn(response);

		// method to test
		Response actualResponse = this.gatewayClient.send(request);

		assertThat(actualResponse).isEqualTo(response);

		verify(client).target(target);
		verify(builder).accept(mediaType);
		verify(builder).buildPost(requestCaptor.capture());
		Entity<Request> entity = requestCaptor.getValue();
		assertThat(entity.getEntity()).isEqualTo(request);
	}

	@Test
	public void testSendGatewayRequest() {
		WebTarget webTarget = mock(WebTarget.class);
		Builder builder = mock(Builder.class);
		Invocation invocation = mock(Invocation.class);

		GatewayRequest gatewayRequest = new GatewayRequest.Builder(1, "username", "password").build();
		Request request = gatewayRequest.getRequest();
		Response response = new Response();

		when(client.target(anyString())).thenReturn(webTarget);
		when(webTarget.request()).thenReturn(builder);
		when(builder.accept(any(MediaType.class))).thenReturn(builder);
		when(builder.buildPost(any(Entity.class))).thenReturn(invocation);
		when(invocation.invoke(Response.class)).thenReturn(response);

		// method to test
		Response actualResponse = this.gatewayClient.send(gatewayRequest);

		assertThat(actualResponse).isEqualTo(response);

		verify(client).target(target);
		verify(builder).accept(mediaType);
		verify(builder).buildPost(requestCaptor.capture());
		Entity<Request> entity = requestCaptor.getValue();
		assertThat(entity.getEntity()).isEqualTo(request);
	}
	
	@Test
	public void testSupportedFormats() {
		WebTarget webTarget = mock(WebTarget.class);
		Builder builder = mock(Builder.class);
		Invocation invocation = mock(Invocation.class);

		when(client.target(anyString())).thenReturn(webTarget);
		when(webTarget.request()).thenReturn(builder);
		when(builder.accept(any(MediaType.class))).thenReturn(builder);
		when(builder.buildPost(any(Entity.class))).thenReturn(invocation);
		when(invocation.invoke(Response.class)).thenReturn(new Response());

		MediaType mediaType = MediaType.APPLICATION_XML_TYPE;
		GatewayClientImpl gc = new GatewayClientImpl(client, target, mediaType);
		
		gc.send(new Request());
		verify(builder).accept(mediaType);
		gc.close();
		
		mediaType = MediaType.APPLICATION_JSON_TYPE;
		gc = new GatewayClientImpl(client, target, mediaType);
		gc.send(new Request());
		verify(builder).accept(mediaType);
		gc.close();
		
		mediaType = MediaType.APPLICATION_ATOM_XML_TYPE;
		gc = new GatewayClientImpl(client, target, mediaType);
		try {
			gc.send(new Request());
			failBecauseExceptionWasNotThrown(IllegalStateException.class);
		} catch(IllegalStateException expected) { }
		
		gc.close();
	}
	
	@Before
	public void initialize() {
		this.gatewayClient = new GatewayClientImpl(client, target, mediaType);
	}
}
