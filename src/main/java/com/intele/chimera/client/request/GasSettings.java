package com.intele.chimera.client.request;

/**
 * <p>Convenience class using the builder pattern to create a new {@link com.intele.chimera.gw.xsd.smsgateway.request._2013._02.GasSettings} object.
 * <p>Example:
 * <pre>
 * {@code
 * GasSettings gasSettings = new GasSettings.
 * 	Builder("05002").
 * 	withDescription("Blade Runner, Ringen, 2x adult").
 * 	build();
 * }
 * </pre>
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
		
		/**
		 * @param serviceCode Identifier for the category of Goods and services. See chapter
		 * 3.1 in the ITC SMS Gateway API specification for more information.
		 */
		public Builder(String serviceCode) {
			this.serviceCode = serviceCode;
		}
		
		/**
		 * @param description Further details of the Goods and services. The description may
		 * occur on the end-user invoice (together with category) for certain Mobile Network Operators.
		 * @return the updated builder
		 */
		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}
		/**
		 * @return newly created gasSettings
		 */
		public GasSettings build() {
			return new GasSettings(this);
		}
	}
	/**
	 * @return wrapped gasSettings
	 */
	public com.intele.chimera.gw.xsd.smsgateway.request._2013._02.GasSettings getGasSettings() {
		return gasSettings;
	}
}
