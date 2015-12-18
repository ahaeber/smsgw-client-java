package com.intele.chimera.client.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;

/**
 * Tests for the builder.
 * 
 * @author gre
 * @version 1.0		Dec 17, 2015
 */
public class SendWindowTest {

	@Test
	public void testBuilderWithRequiredParameters() throws DatatypeConfigurationException {
		XMLGregorianCalendar startDateTime =
				DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());

		com.intele.chimera.gw.xsd.smsgateway.request._2013._02.SendWindow sendWindow1 =
				new SendWindow.Builder(startDateTime).build().getSendWindow();
		com.intele.chimera.gw.xsd.smsgateway.request._2013._02.SendWindow sendWindow2 = 
				new SendWindow.Builder(startDateTime, startDateTime).build().getSendWindow();
		
		assertThat(sendWindow1.getStartDate()).isEqualTo(startDateTime);
		assertThat(sendWindow1.getStartTime()).isEqualTo(startDateTime);
		assertThat(sendWindow1.getStopDate()).isNull();
		assertThat(sendWindow1.getStopTime()).isNull();
		
		assertThat(sendWindow2.getStartDate()).isEqualTo(startDateTime);
		assertThat(sendWindow2.getStartTime()).isEqualTo(startDateTime);
		assertThat(sendWindow2.getStopDate()).isNull();
		assertThat(sendWindow2.getStopTime()).isNull();
	}
	
	@Test
	public void testBuilderWithAllOptionalParameters() throws DatatypeConfigurationException {
		XMLGregorianCalendar startDateTime =
				DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
		XMLGregorianCalendar stopDateTime =
				DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());

		com.intele.chimera.gw.xsd.smsgateway.request._2013._02.SendWindow sendWindow1 =
				new SendWindow.Builder(startDateTime).
				withStopDateTime(stopDateTime).
				build().getSendWindow();
		com.intele.chimera.gw.xsd.smsgateway.request._2013._02.SendWindow sendWindow2 = 
				new SendWindow.Builder(startDateTime, startDateTime).
				withStopDate(stopDateTime).
				withStopTime(stopDateTime).
				build().getSendWindow();
		
		assertThat(sendWindow1.getStartDate()).isEqualTo(startDateTime);
		assertThat(sendWindow1.getStartTime()).isEqualTo(startDateTime);
		assertThat(sendWindow1.getStopDate()).isEqualTo(stopDateTime);
		assertThat(sendWindow1.getStopTime()).isEqualTo(stopDateTime);
		
		assertThat(sendWindow2.getStartDate()).isEqualTo(startDateTime);
		assertThat(sendWindow2.getStartTime()).isEqualTo(startDateTime);
		assertThat(sendWindow2.getStopDate()).isEqualTo(stopDateTime);
		assertThat(sendWindow2.getStopTime()).isEqualTo(stopDateTime);
	}
}
