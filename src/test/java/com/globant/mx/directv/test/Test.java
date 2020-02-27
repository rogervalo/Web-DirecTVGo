package com.globant.mx.directv.test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.globant.mx.directv.utilities.Mydriver;



public abstract class Test {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public Test() {
		
	}
	
	
	
	@BeforeSuite(alwaysRun=true)
	@Parameters({"navegador"})
	public void beforeClass(String navegador) {
		setDriver(Mydriver.getWebDriverInstance());
		Mydriver.maximiseBrowser();
		Mydriver.deleteAllCookies();
		Mydriver.openHomePage();
	}
	
	
	
	
	@AfterSuite(alwaysRun=true)
	public void afterClass() {
		driver = Mydriver.getWebDriverInstance();
		driver.quit();
	}



	public WebDriver getDriver() {
		return Mydriver.getWebDriverInstance();
	}



	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriverWait getWait() {
		return wait;
	}



	public void setWait(WebDriverWait wait) {
		this.wait = wait;
	}	
	
}
