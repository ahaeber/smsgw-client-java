package com.intele.chimera.client.request;

import com.intele.chimera.client.GatewayClient;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.Request;

/**
 * <p>Convenience class using the builder pattern to create a new {@link Request} object.
 * <p>Example:
 * <pre>
 * {@code
 * GatewayRequest request = new GatewayRequest.Builder(100, "username", "password").build();
 * request.addMessage(
 * 	new Sms.Builder("+4741000000", "test").withPrice(0).build()
 * 	);
 * }
 * </pre>
 *
 * @author  gre
 * @version 1.0		Aug 6, 2015
 * @see GatewayClient#send(GatewayRequest)
 * @see Sms
 */
public class GatewayRequest {

	private final Request request = new Request();

	public static class Builder {
		private int serviceId;
		private String username;
		private String password;
		private String batchReference;

		/**
		 * <p>Service identifier and credentials.
		 * @param serviceId
		 * @param username
		 * @param password
		 */
		public Builder(int serviceId, String username, String password) {
			this.serviceId = serviceId;
			this.username = username;
			this.password = password;
		}
		/**
		 * @param batchReference Reference ID that will be returned in the response.
		 * @return the updated builder
		 */
		public Builder withBatchReference(String batchReference) {
			this.batchReference = batchReference;
			return this;
		}
		/**
		 * @return the newly created gatewayrequest 
		 */
		public GatewayRequest build() {
			return new GatewayRequest(this);
		}
	}

	private GatewayRequest(Builder builder) {
		this.request.setServiceId(builder.serviceId);
		this.request.setUsername(builder.username);
		this.request.setPassword(builder.password);
		this.request.setBatchReference(builder.batchReference);
	}
	/**
	 * Adds a sms to this request.
	 * @param sms
	 */
	public void addMessage(Sms sms) {
		this.request.getMessage().add(sms.getMessage());
	}
	/**
	 * @return the wrapped request
	 */
	public Request getRequest() {
		return request;
	}
}
