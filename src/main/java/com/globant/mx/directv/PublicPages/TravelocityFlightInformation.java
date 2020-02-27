package com.globant.mx.directv.PublicPages;


import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.globant.mx.directv.utilities.PageUtils;


public class TravelocityFlightInformation extends PageUtils{
	
	
	
	
	public TravelocityFlightInformation(WebDriver driver) {
		super(driver);
	}

	
	
	public void run() {
		System.out.println("la pagina va a cargar");
		getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		System.out.println("termino de cargar la pagina ...");
		WebElement buttonElement = clickButtonWithXpath("(//button[@class='btn-text toggle-trigger'])[1]");
	}
	
	
	
	
}
