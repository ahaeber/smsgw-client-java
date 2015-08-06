package com.intele.chimera.client.request;

import com.intele.chimera.client.GatewayClient;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.Message;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.OriginatorSettings;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.OriginatorTypeEnum;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.Parameter;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.Settings;

/**
 * <p>Convenience class using the builder pattern to create a new {@link Message} object.
 * <p>Example:
 * <pre>
 * {@code
 * Sms sms = new Sms.
 * 	Builder("+4741000000", "test message").
 * 	withPrice(0).
 * 	withOriginatorSettings(OriginatorTypeEnum.ALPHANUMERIC, "test").
 * 	withSendWindow(new SendWindow.Builder(DatatypeFactory.newInstance().newXMLGregorianCalendar("2015-08-06T12:00:00+02:00")).build()).
 * 	build();
 * </pre>
 *
 * @author  gre
 * @version 1.0		Aug 6, 2015
 * @see GatewayClient#send(GatewayRequest)
 * @see GatewayRequest
 * @see GasSettings
 * @see SendWindow
 */
public class Sms {

	private Message message;

	public static class Builder {
		private Message message;
		private SettingsWrapper sw;

		/**
		 * @param recipient The MSISDN of the recipient. The format should follow the ITU-T E.164 standard with a + prefix.
		 * Example: +4792000001.
		 * Note: This must be a valid MSISDN, that is Mobile phone
		 * number. E.g. for Norway these numbers start with 4, 9, 58 or
		 * 59.
		 * @param content The message payload to send, typically the message text.
		 * See chapter 3.4 in the ITC SMS Gateway API specification for more details
		 */
		public Builder(String recipient, String content) {
			this.message = new Message();
			this.sw = new SettingsWrapper();
			this.message.setRecipient(recipient);
			this.message.setContent(content);
		}
		/**
		 * @param price The cost for the recipient to receive the message. In lowest
		 * monetary unit. See chapter 3.1  in the ITC SMS Gateway API specification for details.
		 * Example: 200 (2,- NOK)
		 * @return
		 */
		public Builder withPrice(int price){
			this.message.setPrice(price);
			return this;
		}
		/**
		 * Arbitrary client reference ID that will be returned in the
		 * message response.
		 * @param clientReference
		 * @return
		 */
		public Builder withClientReference(String clientReference){
			this.message.setClientReference(clientReference);
			return this;
		}
		/**
		 * @param priority Uses service value unless specified.
		 * Used to prioritize between messages sent from the same
		 * service.
		 * 1: low (slower)
		 * 2: medium
		 * 3: high (faster)
		 * @return
		 */
		public Builder withPriority(int priority){
			this.sw.getSettings().setPriority(priority);
			return this;
		}
		/**
		 * @param validity Uses service value unless specified.
		 * Specifies the TTL (time to live) for the message, i.e. how long
		 * before the message times out in cases where it cannot be
		 * delivered to a handset.
		 * See chapter 3.3 in the ITC SMS Gateway API specification for valid values.
		 * Example: 173
		 * @return
		 */
		public Builder withValidity(int validity ){
			this.sw.getSettings().setValidity(validity);
			return this;
		}
		/**
		 * @param differentiator Arbitrary string set by the client to enable grouping messages in
		 * certain statistic reports. Example: pincode_messages
		 * @return
		 */
		public Builder withDifferentiator(String differentiator){
			this.sw.getSettings().setDifferentiator(differentiator);
			return this;
		}
		/**
		 * @param age Only relevant for CPA/GAS messages.
		 * Defines an age limit for message content. The mobile network operators enforces this.
		 * IMPORTANT: If the service is a subscription service all CPA/GAS
		 * messages must have age set to 18.
		 * @return
		 */
		public Builder withAge(int age){
			this.sw.getSettings().setAge(age);
			return this;
		}
		/**
		 * @param newSession Used to start a new session. See chapter 3.5 in the
		 * ITC SMS Gateway API specification for details.
		 * @return
		 */
		public Builder withNewSession(boolean newSession){
			this.sw.getSettings().setNewSession(newSession);
			return this;
		}
		/**
		 * @param sessionId Used to continue an existing session. See chapter 3.5 in the
		 * ITC SMS Gateway API for details.
		 * @return
		 */
		public Builder withSessionId(String sessionId){
			this.sw.getSettings().setSessionId(sessionId);
			return this;
		}
		/**
		 * @param invoiceNode Arbitrary string set by the client to enable grouping messages
		 * on the service invoice.
		 * @return
		 */
		public Builder withInvoiceNode(String invoiceNode){
			this.sw.getSettings().setInvoiceNode(invoiceNode);
			return this;
		}
		/**
		 * @param autoDetectEncoding Default value is false. Currently not in use.
		 * @return
		 */
		public Builder withAutoDetectEncoding(boolean autoDetectEncoding){
			this.sw.getSettings().setAutoDetectEncoding(autoDetectEncoding);
			return this;
		}
		/**
		 * @param safeRemoveNonGsmCharacters Default value is false.
		 * If set to true the SMSGW will remove or safely substitute invalid
		 * characters in the message content instead of rejecting the
		 * message. See chapter 3.4 in the ITC SMS Gateway API for details.
		 * @return
		 */
		public Builder withSafeRemoveNonGsmCharacters(boolean safeRemoveNonGsmCharacters){
			this.sw.getSettings().setSafeRemoveNonGsmCharacters(safeRemoveNonGsmCharacters);
			return this;
		}
		/**
		 * <p>Used add special parameters to the message. See 3.7.3.4 in the ITC SMS Gateway API for
		 * valid keys and values.
		 * @param key
		 * @param value
		 * @return
		 */
		public Builder withParameter(String key, String value){
			Parameter parameter = new Parameter();
			parameter.setKey(key);
			parameter.setValue(value);
			this.sw.getSettings().getParameter().add(parameter);
			return this;
		}
		/**
		 * @param originatorType Specifies the type of originator. See chapter 3.2 in the ITC SMS Gateway API for details.
		 * @param originator Depends on the originatorType. Examples: "+4799999999", "Intelecom", "1960"
		 * @return
		 */
		public Builder withOriginatorSettings(OriginatorTypeEnum originatorType, String originator){
			OriginatorSettings originatorSettings = new OriginatorSettings();
			originatorSettings.setOriginator(originator);
			originatorSettings.setOriginatorType(originatorType);
			this.sw.getSettings().setOriginatorSettings(originatorSettings);;
			return this;
		}
		/**
		 * <p>Specify GAS (Goods and Services) values. See chapter 3.1 in the ITC SMS Gateway API for details.
		 * @param gasSettings
		 * @return
		 */
		public Builder withGasSettings(GasSettings gasSettings) {
			this.sw.getSettings().setGasSettings(gasSettings.getGasSettings());
			return this;
		}
		/**
		 * <p>Specify the send window for the message. See chapter 3.6 in the ITC SMS Gateway API.
		 * @param sendWindow
		 * @return
		 */
		public Builder withSendWindow(SendWindow sendWindow) {
			this.sw.getSettings().setSendWindow(sendWindow.getSendWindow());
			return this;
		}

		public Sms build() {
			return new Sms(this);
		}

		private class SettingsWrapper {
			private Settings settings = null;

			private Settings getSettings() {
				if(settings == null) {
					this.settings = new Settings();
				}
				return settings;
			}
		}
	}

	private Sms(Builder builder) {
		this.message = builder.message;
		System.out.println(builder.sw);
		if(builder.sw.settings != null) {
			this.message.setSettings(builder.sw.settings);
		}
	}

	protected Message getMessage() {
		return message;
	}
}
