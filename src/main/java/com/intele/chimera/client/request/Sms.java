package com.intele.chimera.client.request;

import java.util.List;

import com.intele.chimera.client.GatewayClient;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.Message;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.OriginatorSettings;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.OriginatorTypeEnum;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.Parameter;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.Settings;

/**
 * <p>Convenience class to create a new {@link Message} objects using the builder pattern.
 * <p>Example:
 * <pre>
 * {@code
 * Sms sms = new Sms.
 * 	Builder("+4741000000", "Test message").
 * 	withPrice(0).
 * 	withOriginatorSettings(OriginatorTypeEnum.ALPHANUMERIC, "originator").
 * 	withSendWindow(new SendWindow.Builder(DatatypeFactory.newInstance().newXMLGregorianCalendar("2015-08-06T12:00:00+02:00")).build()).
 * 	build();
 * }
 * </pre>
 *
 * @author  gre
 * @version 1.1		Jan 6, 2016
 * @see GatewayClient#send(GatewayRequest)
 * @see GatewayRequest
 * @see GasSettings
 * @see SendWindow
 */
public class Sms {

	private final Message message;

	public static class Builder {
		private String recipient;
		private String content;
		private Integer price;
		private String clientReference;
		private Integer priority;
		private Integer validity;
		private String differentiator;
		private Integer age;
		private Boolean newSession;
		private String sessionId;
		private String invoiceNode;
		private Boolean safeRemoveNonGsmCharacters;
		private List<Parameter> parameters;
		private OriginatorSettings originatorSettings;
		private GasSettings gasSettings;
		private SendWindow sendWindow;

		/**
		 * Set the recipient and message content.
		 * @param recipient The MSISDN of the recipient. The format should follow the ITU-T E.164 standard with a + prefix, e.g. +4792000001.
		 * @param content The message payload to send, typically the message text.
		 * See chapter 3.4 in the ITC SMS Gateway API specification for more details
		 */
		public Builder(String recipient, String content) {
			this.recipient = recipient;
			this.content = content;
		}
		/**
		 * Set the price of the message.
		 * @param price The cost for the recipient to receive the message. In lowest
		 * monetary unit. See chapter 3.1  in the ITC SMS Gateway API specification for details.
		 * Example: 200 (2,- NOK)
		 * @return the updated builder
		 */
		public Builder withPrice(int price){
			this.price = price;
			return this;
		}
		/**
		 * Arbitrary client reference ID that will be returned in the message response.
		 * @param clientReference the client message ID
		 * @return the updated builder
		 */
		public Builder withClientReference(String clientReference){
			this.clientReference = clientReference;
			return this;
		}
		/**
		 * Set the priority of the message.
		 * @param priority Uses service value unless specified.
		 * Used to prioritize between messages sent from the same
		 * service.
		 * 1: low (slower)
		 * 2: medium
		 * 3: high (faster)
		 * @return the updated builder
		 */
		public Builder withPriority(int priority){
			this.priority = priority;
			return this;
		}
		/**
		 * Set a validity for the message.
		 * @param validity Uses service value unless specified.
		 * Specifies the TTL (time to live) for the message, i.e. how long
		 * before the message times out in cases where it cannot be
		 * delivered to a handset.
		 * See chapter 3.3 in the ITC SMS Gateway API specification for valid values.
		 * Example: 173
		 * @return the updated builder
		 */
		public Builder withValidity(int validity ){
			this.validity = validity;
			return this;
		}
		/**
		 * Sets the name of the message group.
		 * @param differentiator Arbitrary string set by the client to enable grouping messages in
		 * certain statistic reports. Example: pincode_messages
		 * @return the updated builder
		 */
		public Builder withDifferentiator(String differentiator){
			this.differentiator = differentiator;
			return this;
		}
		/**
		 * Sets the minimum age of the recipient.
		 * @param age Only relevant for CPA/GAS messages.
		 * Defines an age limit for message content. The mobile network operators enforces this.
		 * IMPORTANT: If the service is a subscription service all CPA/GAS
		 * messages must have age set to 18.
		 * @return the updated builder
		 */
		public Builder withAge(int age){
			this.age = age;
			return this;
		}
		/**
		 * Start a new session.
		 * @param newSession Used to start a new session. See chapter 3.5 in the
		 * ITC SMS Gateway API specification for details.
		 * @return the updated builder
		 */
		public Builder withNewSession(boolean newSession){
			this.newSession = newSession;
			return this;
		}
		/**
		 * Use an existing session.
		 * @param sessionId Used to continue an existing session. See chapter 3.5 in the
		 * ITC SMS Gateway API for details.
		 * @return the updated builder
		 */
		public Builder withSessionId(String sessionId){
			this.sessionId = sessionId;
			return this;
		}
		/**
		 * Sets a custom group name for the invoice. 
		 * @param invoiceNode Arbitrary string set by the client to enable grouping messages
		 * on the service invoice.
		 * @return the updated builder
		 */
		public Builder withInvoiceNode(String invoiceNode){
			this.invoiceNode = invoiceNode;
			return this;
		}
		/**
		 * Decides if non-GSM characters should be removed from the content text.
		 * @param safeRemoveNonGsmCharacters Default value is false.
		 * If set to true the SMSGW will remove or safely substitute invalid
		 * characters in the message content instead of rejecting the
		 * message. See chapter 3.4 in the ITC SMS Gateway API for details.
		 * @return the updated builder
		 */
		public Builder withSafeRemoveNonGsmCharacters(boolean safeRemoveNonGsmCharacters){
			this.safeRemoveNonGsmCharacters = safeRemoveNonGsmCharacters;
			return this;
		}
		/**
		 * <p>Used add special parameters to the message. See 3.7.3.4 in the ITC SMS Gateway API for
		 * valid keys and values.
		 * @param key
		 * @param value
		 * @return the updated builder
		 */
		public Builder withParameters(List<Parameter> parameters){
			this.parameters = parameters;
			return this;
		}
		/**
		 * Sets the originator.
		 * @param originatorType Specifies the type of originator. See chapter 3.2 in the ITC SMS Gateway API for details.
		 * @param originator Depends on the originatorType. Examples: "+4799999999", "Intelecom", "1960"
		 * @return the updated builder
		 */
		public Builder withOriginatorSettings(OriginatorTypeEnum originatorType, String originator){
			this.originatorSettings = new OriginatorSettings();
			originatorSettings.setOriginator(originator);
			originatorSettings.setOriginatorType(originatorType);
			return this;
		}
		/**
		 * <p>Specify GAS (Goods and Services) values. See chapter 3.1 in the ITC SMS Gateway API for details.
		 * @param gasSettings
		 * @return the updated builder
		 */
		public Builder withGasSettings(GasSettings gasSettings) {
			this.gasSettings = gasSettings;
			return this;
		}
		/**
		 * <p>Specify the send window for the message. See chapter 3.6 in the ITC SMS Gateway API.
		 * <p>Used if the message should be queued and sent in the future instead of immediately.
		 * @param sendWindow
		 * @return the updated builder
		 */
		public Builder withSendWindow(SendWindow sendWindow) {
			this.sendWindow = sendWindow;
			return this;
		}
		/**
		 * @return the newly created Sms
		 */
		public Sms build() {
			return new Sms(this);
		}
		
		private boolean hasSettings() {
			return (
					priority != null ||
					validity != null ||
					differentiator != null ||
					age != null ||
					newSession != null ||
					sessionId != null ||
					invoiceNode != null ||
					safeRemoveNonGsmCharacters != null ||
					parameters != null ||
					originatorSettings != null ||
					gasSettings != null ||
					sendWindow != null
					);
		}
	}

	private Sms(Builder builder) {
		this.message = new Message();
		this.message.setClientReference(builder.clientReference);
		this.message.setContent(builder.content);
		this.message.setPrice(builder.price);
		this.message.setRecipient(builder.recipient);
		
		if(builder.hasSettings()) {
			Settings settings = new Settings();
			settings.setAge(builder.age);
			settings.setDifferentiator(builder.differentiator);
			if(builder.gasSettings != null) {
				settings.setGasSettings(builder.gasSettings.getGasSettings());
			}
			settings.setInvoiceNode(builder.invoiceNode);
			settings.setNewSession(builder.newSession);
			settings.setOriginatorSettings(builder.originatorSettings);
			settings.setPriority(builder.priority);
			settings.setSafeRemoveNonGsmCharacters(builder.safeRemoveNonGsmCharacters);
			if(builder.sendWindow != null) {
				settings.setSendWindow(builder.sendWindow.getSendWindow());
			}
			settings.setSessionId(builder.sessionId);
			settings.setValidity(builder.validity);
			if(builder.parameters != null) {
				for(Parameter p : builder.parameters) {
					settings.getParameter().add(p);
				}
			}
			this.message.setSettings(settings);
		}
	}

	/**
	 * @return the wrapped message
	 */
	public Message getMessage() {
		return message;
	}
}
