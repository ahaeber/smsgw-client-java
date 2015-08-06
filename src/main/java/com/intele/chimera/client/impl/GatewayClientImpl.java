package com.intele.chimera.client.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.intele.chimera.client.GatewayClient;
import com.intele.chimera.client.request.GatewayRequest;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.Request;
import com.intele.chimera.gw.xsd.smsgateway.response._2013._02.Response;

/**
 * @author  gre
 * @version 1.0		Aug 6, 2015
 *
 */
public class GatewayClientImpl implements GatewayClient {

	private final Client client;
	private final String target;
	private final MediaType mediaType;

	public GatewayClientImpl(Client client, String target, MediaType mediaType) {
		super();
		this.client = client;
		this.target = target;
		this.mediaType = mediaType;
	}
	
	@Override
	public Response send(GatewayRequest gatewayRequest) {
		Request request = gatewayRequest.getRequest();
		return this.send(request);
	}
	
	@Override
	public Response send(Request request) {
		WebTarget webTarget = client.target(target);
		Entity<Request> entity;
		if(mediaType.equals(MediaType.APPLICATION_XML_TYPE)) {
			entity = Entity.xml(request);
		} else if(mediaType.equals(MediaType.APPLICATION_JSON_TYPE)) {
			entity = Entity.json(request);
		} else {
			throw new IllegalStateException("Mediatype "+mediaType+" is not supported.");
		}
		
		Invocation buildPost = webTarget.request().accept(mediaType).buildPost(entity);
		return buildPost.invoke(Response.class);
	}

	@Override
	public void close() {
		this.client.close();
	}
}
