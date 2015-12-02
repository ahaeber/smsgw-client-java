package com.intele.chimera.client.request;

import javax.xml.datatype.XMLGregorianCalendar;

import com.intele.chimera.client.GatewayClient;

/**
 * <p>Convenience class using the builder pattern to create a new {@link com.intele.chimera.gw.xsd.smsgateway.request._2013._02.SendWindow SendWindow} object.
 * <p>Example:
 * <pre>
 * {@code
 * DatatypeFactory df = DatatypeFactory.newInstance(); // Expensive operation; consider using a ThreadLocal variable of DatatypeFactory 
 * GregorianCalendar stopDate = new GregorianCalendar(2015, 8, 9);
 *	
 * SendWindow sendWindow = new SendWindow.
 * 	Builder(df.newXMLGregorianCalendar("2015-08-06T12:00:00+02:00")).
 * 	withStopDate(df.newXMLGregorianCalendar(stopDate)).
 *	build();
 * }
 * </pre>
 *
 * @author  gre
 * @version 1.0		Aug 6, 2015
 * @see GatewayClient#send(GatewayRequest)
 * @see GatewayRequest
 * @see GasSettings
 * @see SendWindow
 */
public class SendWindow {
	
	private com.intele.chimera.gw.xsd.smsgateway.request._2013._02.SendWindow sendWindow;
	
	private SendWindow(Builder builder) {
		this.sendWindow = new com.intele.chimera.gw.xsd.smsgateway.request._2013._02.SendWindow();
		this.sendWindow.setStartDate(builder.startDate);
		this.sendWindow.setStartTime(builder.startTime);
		this.sendWindow.setStopDate(builder.stopDate);
		this.sendWindow.setStopTime(builder.stopTime);
	}
	
	public static class Builder {
		private XMLGregorianCalendar startDate;
		private XMLGregorianCalendar startTime;
		private XMLGregorianCalendar stopDate;
		private XMLGregorianCalendar stopTime;
	
		/**
		 * Set when the message should be sent.
		 * @param startDate the date to send the message
		 * @param startTime the time to send the message
		 */
		public Builder(XMLGregorianCalendar startDate, XMLGregorianCalendar startTime) {
			this.startDate = startDate;
			this.startTime = startTime;
		}
		/**
		 * Set when the message should be sent.
		 * @param startDateTime the datetime to send the message
		 */
		public Builder(XMLGregorianCalendar startDateTime) {
			this.startDate = startDateTime;
			this.startTime = startDateTime;
		}
		
		/**
		 * @param stopDate the date
		 * @return the updated builder
		 */
		public Builder withStopDate(XMLGregorianCalendar stopDate) {
			this.stopDate = stopDate;
			return this;
		}
		/**
		 * @param stopTime
		 * @return the updated builder
		 */
		public Builder withStopTime(XMLGregorianCalendar stopTime) {
			this.stopTime = stopTime;
			return this;
		}
		/**
		 * @param stopDateTime
		 * @return the updated builder
		 */
		public Builder withStopDateTime(XMLGregorianCalendar stopDateTime) {
			this.stopDate = stopDateTime;
			this.stopTime = stopDateTime;
			return this;
		}
		/**
		 * @return the newly created sendWindow
		 */
		public SendWindow build() {
			return new SendWindow(this);
		}
	}
	/**
	 * @return the wrapped sendWindow
	 */
	public com.intele.chimera.gw.xsd.smsgateway.request._2013._02.SendWindow getSendWindow() {
		return this.sendWindow;
	}
}
