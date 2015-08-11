package com.intele.chimera.client;

import javax.ws.rs.client.Client;
import com.intele.chimera.client.request.GatewayRequest;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.Request;
import com.intele.chimera.gw.xsd.smsgateway.response._2013._02.Response;

/**
 * <p>Methods to send SMS messages to the Intelecom SMS Gateway.
 * <p>Note that {@link GatewayClient#close() close} method should be
 * called before disposing of the object to avoid leaking resources.
 *
 * <p>Extends the {@link AutoCloseable} interface to let GatewayClient implementations
 * implement {@link AutoCloseable#close()} to close the {@link Client}.
 * 
 * @author  gre
 * @version 1.0		Aug 6, 2015
 */
public interface GatewayClient extends AutoCloseable {

	/**
	 * <p>Sends a SMS request to the Intelecom SMS Gateway.
	 *
	 * @param gatewayRequest
	 * @return the gateway response
	 */
	public Response send(GatewayRequest gatewayRequest);

	/**
	 * <p>Sends a SMS request to the Intelecom SMS Gateway.
	 * 
	 * @param request
	 * @return the gateway response
	 */
	public Response send(Request request);
}
