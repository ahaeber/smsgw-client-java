package com.intele.chimera.client.request;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.Message;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.OriginatorTypeEnum;
import com.intele.chimera.gw.xsd.smsgateway.request._2013._02.Settings;

/**
 * Tests for the builder.
 * 
 * @author gre
 * @version 1.0		Dec 17, 2015
 */
public class SmsTest {

	@Test
	public void testBuilderWithRequiredParameters() {
		String recipient = "recipient";
		String content = "content";		

		Message message = new Sms.Builder(recipient, content).build().getMessage();
		
		assertThat(message.getRecipient()).isEqualTo(recipient);
		assertThat(message.getContent()).isEqualTo(content);

		assertThat(message.getClientReference()).isNull();
		assertThat(message.getPrice()).isNull();
		assertThat(message.getSettings()).isNull();
	}
	
	@Test
	public void testBuilderWithAllOptionalParameters() {
		String recipient = "recipient";
		String content = "content";		
		int age = 15;
		String clientReference = "clientReference";
		String differentiator = "differentiator";
		GasSettings gasSettings = new GasSettings.Builder("serviceCode").build();
		String invoiceNode = "invoiceNode";
		boolean newSession = true;
		OriginatorTypeEnum originatorType = OriginatorTypeEnum.ALPHANUMERIC;
		String originator = "alphanumeric";
		String key1 = "key1";
		String value1 = "value1";
		String key2 = "key2";
		String value2 = "value2";
		int price = 5000;
		int priority = 5;
		boolean safeRemoveNonGsmCharacters = true;
		SendWindow sendWindow = new SendWindow.Builder(null).build();
		String sessionId = "sessionId";
		int validity = 171;
		
		Message message = new Sms.Builder(recipient, content).
				withAge(age).
				withClientReference(clientReference).
				withDifferentiator(differentiator).
				withGasSettings(gasSettings).
				withInvoiceNode(invoiceNode).
				withNewSession(newSession).
				withOriginatorSettings(originatorType, originator).
				withParameter(key1, value1).
				withParameter(key2, value2).
				withPrice(price).
				withPriority(priority).
				withSafeRemoveNonGsmCharacters(safeRemoveNonGsmCharacters).
				withSendWindow(sendWindow).
				withSessionId(sessionId).
				withValidity(validity).
				build().getMessage();
		
		assertThat(message.getRecipient()).isEqualTo(recipient);
		assertThat(message.getContent()).isEqualTo(content);
		assertThat(message.getClientReference()).isEqualTo(clientReference);
		assertThat(message.getPrice()).isEqualTo(price);
		
		Settings settings = message.getSettings();
		
		assertThat(settings.getAge()).isEqualTo(age);
		assertThat(settings.getDifferentiator()).isEqualTo(differentiator);
		assertThat(settings.getGasSettings()).isEqualTo(gasSettings.getGasSettings());
		assertThat(settings.getInvoiceNode()).isEqualTo(invoiceNode);
		assertThat(settings.getOriginatorSettings().getOriginator()).isEqualTo(originator);
		assertThat(settings.getOriginatorSettings().getOriginatorType()).isEqualTo(originatorType);
		assertThat(settings.getParameter().size()).isEqualTo(2);
		assertThat(settings.getParameter().get(0).getKey()).isEqualTo(key1);
		assertThat(settings.getParameter().get(0).getValue()).isEqualTo(value1);
		assertThat(settings.getParameter().get(1).getKey()).isEqualTo(key2);
		assertThat(settings.getParameter().get(1).getValue()).isEqualTo(value2);
		assertThat(settings.getPriority()).isEqualTo(priority);
		assertThat(settings.getSendWindow()).isEqualTo(sendWindow.getSendWindow());
		assertThat(settings.getSessionId()).isEqualTo(sessionId);
		assertThat(settings.getValidity()).isEqualTo(validity);
		assertThat(settings.isNewSession()).isEqualTo(newSession);
		assertThat(settings.isSafeRemoveNonGsmCharacters()).isEqualTo(safeRemoveNonGsmCharacters);
	}
}
