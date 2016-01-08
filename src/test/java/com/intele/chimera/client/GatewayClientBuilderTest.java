package com.intele.chimera.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import java.lang.reflect.Field;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.Test;

import com.intele.chimera.client.impl.GatewayClientImpl;

public class GatewayClientBuilderTest {

	@Test
	public void shouldNotBeAbleToSetConfigurationWithClient() {
		GatewayClientBuilder gatewayClientBuilder = new GatewayClientBuilder().withClient(ClientBuilder.newClient());
		
		try {
			gatewayClientBuilder.withConfiguration(new ClientConfig());
			failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
		} catch(IllegalArgumentException expected) { }
	}
	
	@Test
	public void shouldNotBeAbleToSetClientWithConfiguration() {
		GatewayClientBuilder gatewayClientBuilder = new GatewayClientBuilder().withConfiguration(new ClientConfig());
		
		try {
			gatewayClientBuilder.withClient(ClientBuilder.newClient());
			failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
		} catch(IllegalArgumentException expected) { }
	}
	
	@Test
	public void targetServerCannotBeNull() {
		try {
			new GatewayClientBuilder().withTargetServer(null);
			failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
		} catch(IllegalArgumentException expected) { }
	}
	
	@Test
	public void shouldAllowTargetServerWithAndWithoutTrailingSpace() throws Exception {
		String targetServer = "targetServer";
		
		GatewayClientBuilder gatewayClientBuilder = new GatewayClientBuilder().withTargetServer(targetServer);
		
		Field f = gatewayClientBuilder.getClass().getDeclaredField("target");
		f.setAccessible(true);
		String target = (String) f.get(gatewayClientBuilder);
		assertThat(target).isEqualTo(targetServer+GatewayClientBuilder.DEFAULT_TARGET_SERVER_PATH);
		
		gatewayClientBuilder.withTargetServer("targetServer/");
		target = (String) f.get(gatewayClientBuilder);
		assertThat(target).isEqualTo(targetServer+GatewayClientBuilder.DEFAULT_TARGET_SERVER_PATH);
	}
	
	@Test
	public void testBuilderWithRequiredParameters() throws Exception {
		GatewayClient gatewayClient = new GatewayClientBuilder().build();
		
		assertThat(gatewayClient).isInstanceOf(GatewayClientImpl.class);
		
		GatewayClientImpl gwclient = (GatewayClientImpl) gatewayClient;

		Field f = gwclient.getClass().getDeclaredField("client");
		f.setAccessible(true);
		Client client = (Client) f.get(gwclient);
		f = gwclient.getClass().getDeclaredField("target");
		f.setAccessible(true);
		String target = (String) f.get(gwclient);
		f = gwclient.getClass().getDeclaredField("mediaType");
		f.setAccessible(true);
		MediaType mediaType = (MediaType) f.get(gwclient);
		
		assertThat(client).isNotNull();
		assertThat(target).isEqualTo(GatewayClientBuilder.DEFAULT_TARGET_SERVER+GatewayClientBuilder.DEFAULT_TARGET_SERVER_PATH);
		assertThat(mediaType).isEqualTo(GatewayClientBuilder.DEFAULT_MEDIA_TYPE);
	}
	
	@Test
	public void testBuilderWithAllParameters() throws Exception {
		ClientConfig configuration = new ClientConfig();
		String proxyPassword = "proxyPassword";
		configuration.property(ClientProperties.PROXY_PASSWORD, proxyPassword);
		MediaType chosenMediaType = MediaType.APPLICATION_JSON_TYPE;
		String targetServer = "targetServer";
		
		GatewayClient gatewayClient = new GatewayClientBuilder().
				withConfiguration(configuration).
				withMediaType(chosenMediaType).
				withTargetServer(targetServer).
				build();
		
		assertThat(gatewayClient).isInstanceOf(GatewayClientImpl.class);
		
		GatewayClientImpl gwclient = (GatewayClientImpl) gatewayClient;

		Field f = gwclient.getClass().getDeclaredField("client");
		f.setAccessible(true);
		Client client = (Client) f.get(gwclient);
		f = gwclient.getClass().getDeclaredField("target");
		f.setAccessible(true);
		String target = (String) f.get(gwclient);
		f = gwclient.getClass().getDeclaredField("mediaType");
		f.setAccessible(true);
		MediaType mediaType = (MediaType) f.get(gwclient);
		
		assertThat(client).isNotNull();
		assertThat(client.getConfiguration().getProperty(ClientProperties.PROXY_PASSWORD)).isEqualTo(proxyPassword);
		assertThat(target).isEqualTo(targetServer+GatewayClientBuilder.DEFAULT_TARGET_SERVER_PATH);
		assertThat(mediaType).isEqualTo(chosenMediaType);
	}
}
