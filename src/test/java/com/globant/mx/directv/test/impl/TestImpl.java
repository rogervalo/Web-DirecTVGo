package com.globant.mx.directv.test.impl;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.globant.mx.directv.PublicPages.TravelocityFlightInformation;
import com.globant.mx.directv.PublicPages.TravelocityFlightsSearchPOJO;
import com.globant.mx.directv.PublicPages.PublicHomePage;



public class TestImpl extends com.globant.mx.directv.test.Test {

	public TestImpl() {
	}

	@Test
	public void test1() {
		PublicHomePage homePage =  PublicHomePage.getInstance(getDriver());
		TravelocityFlightsSearchPOJO tfsPo = homePage.selectTravel();
		TravelocityFlightInformation tff = tfsPo.run();
		tff.run();
		
		assertEquals(homePage.getFlyingFrom(), "");
		assertEquals(homePage.getFlyingTo(), "");
	}
}
