package com.intele.chimera.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;

import com.intele.chimera.client.impl.GatewayClientImpl;

/**
 * <p>Builder to create a {@link GatewayClient} that holds a reference to a {@link Client} object that it uses to send messages to the Intelecom SMS Gateway.
 * <p>All {@link Client} objects should be properly closed to avoid leaking resources. Calling {@link GatewayClient#close()} will also close {@link Client#close() the client connection}.
 * <p>The builder will use the default JAX-RS builder implementation - {@link ClientBuilder#newClient()}. It is also possible to either add a {@link Configuration} or set the {@link Client}
 * that should be used.
 * <p>The creation of a new {@link Client} is a relatively expensive operation and so the {@link GatewayClient} should be reused if possible. 
 *
 * @author gre
 * @version 1.0		Aug 5, 2015
 *
 */
public class GatewayClientBuilder {
	
	public static final String DEFAULT_TARGET_SERVER = "https://smsgw.intele.com";
	public static final String DEFAULT_TARGET_SERVER_PATH = "/gw/rs/sendMessages";
	public static final MediaType DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_XML_TYPE;

	private Client client;
	private String target;
	private MediaType mediaType;
	private Configuration configuration;

	/**
	 * <p>Specify the Client to be used.
	 * 
	 * @param client
	 * @return the updated builder
	 */
	public GatewayClientBuilder withClient(Client client) {
		if(configuration != null) {
			throw new IllegalArgumentException("Client cannot be set when a configuration has been provided. Instead set the configuration directly on the provided client.");
		}
		this.client = client;
		return this;
	}
	
	/**
	 * <p>Override the default target server (https://smsgw.intele.com).
	 * 
	 * @param targetServer
	 * @return the updated builder
	 */
	public GatewayClientBuilder withTargetServer(String targetServer) {
		if(targetServer == null) {
			throw new IllegalArgumentException("Target server cannot be null.");
		}
		StringBuilder b = new StringBuilder();
		if(targetServer.endsWith("/")) {
			b.append(targetServer.substring(0, targetServer.length() - 1));
		} else {
			b.append(targetServer);
		}
		b.append(DEFAULT_TARGET_SERVER_PATH);
		this.target = b.toString();
		
		return this;
	}
	/**
	 *<p>Override the default media type.
	 *
	 * @param mediaType
	 * @return the updated builder
	 */
	public GatewayClientBuilder withMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
		return this;
	}
	
	/**
	 * <p>Provide a custom configuration for the {@link Client}.
	 * 
	 * @param configuration
	 * @return the updated builder
	 */
	public GatewayClientBuilder withConfiguration(Configuration configuration) {
		if(client != null) {
			throw new IllegalArgumentException("Configuration cannot be set when a client has been provided. Instead set the configuration directly on the provided client.");
		}
		this.configuration = configuration;
		return this;
	}
	
	/**
	 * Creates the GatewayClient.
	 * 
	 * @return newly created gatewayClient
	 */
	public GatewayClient build() {
		this.initializeVariables();
		
		return new GatewayClientImpl(
				client,
				target,
				mediaType
				);
	}

	private void initializeVariables() {
		if(client == null) {
			if(configuration != null) {
				client = ClientBuilder.newClient(configuration);
			} else {
				client = ClientBuilder.newClient();
			}
		}
		if(target == null) {
			target = DEFAULT_TARGET_SERVER + DEFAULT_TARGET_SERVER_PATH;
		}
		if(mediaType == null) {
			mediaType = DEFAULT_MEDIA_TYPE;
		}
	}
}
