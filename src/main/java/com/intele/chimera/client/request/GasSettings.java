package com.intele.chimera.client.request;

/**
 * <p>Builder for specifying GAS parameters.
 * 
 * @author  gre
 * @version 1.0		Aug 5, 2015
 *
 */
public class GasSettings {

	private com.intele.chimera.gw.xsd.smsgateway.request._2013._02.GasSettings gasSettings;
	
	private GasSettings(Builder builder) {
		gasSettings = new com.intele.chimera.gw.xsd.smsgateway.request._2013._02.GasSettings();
		gasSettings.setServiceCode(builder.serviceCode);
		gasSettings.setDescription(builder.description);
	}

	public static class Builder {
		private String serviceCode;
		private String description;
		
		public Builder(String serviceCode) {
			this.serviceCode = serviceCode;
		}
		
		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}
		
		public GasSettings build() {
			return new GasSettings(this);
		}
	}
	
	public com.intele.chimera.gw.xsd.smsgateway.request._2013._02.GasSettings getGasSettings() {
		return gasSettings;
	}
}
