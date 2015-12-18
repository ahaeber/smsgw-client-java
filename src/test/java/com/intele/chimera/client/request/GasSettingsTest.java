package com.intele.chimera.client.request;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.intele.chimera.client.request.GasSettings.Builder;

/**
 * Tests for the builder.
 * 
 * @author gre
 * @version 1.0		Dec 17, 2015
 */
public class GasSettingsTest {

	@Test
	public void testBuilderWithRequiredParameters() {
		String serviceCode = "serviceCode";

		Builder builder = new GasSettings.Builder(serviceCode);
		
		com.intele.chimera.gw.xsd.smsgateway.request._2013._02.GasSettings gasSettings =
				builder.build().getGasSettings();
		
		assertThat(gasSettings.getServiceCode()).isEqualTo(serviceCode);
		assertThat(gasSettings.getDescription()).isNull();
	}
	
	@Test
	public void testBuilderWithAllOptionalParameters() {
		String description = "description";
		String serviceCode = "serviceCode";

		Builder builder = new GasSettings.
				Builder(serviceCode).
				withDescription(description);
		
		com.intele.chimera.gw.xsd.smsgateway.request._2013._02.GasSettings gasSettings =
				builder.build().getGasSettings();
		
		assertThat(gasSettings.getServiceCode()).isEqualTo(serviceCode);
		assertThat(gasSettings.getDescription()).isEqualTo(description);
	}
}
