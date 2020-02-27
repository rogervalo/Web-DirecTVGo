package com.globant.mx.directv.test.impl;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import com.globant.mx.directv.PublicPages.PublicHomePage;



public class TestImpl extends com.globant.mx.directv.test.Test {

	public TestImpl() {
	}

	@Test
	public void test1() {
		PublicHomePage.getInstance(getDriver());
		
		
		//assertEquals(homePage.getFlyingFrom(), "");
		//assertEquals(homePage.getFlyingTo(), "");
	}
}
