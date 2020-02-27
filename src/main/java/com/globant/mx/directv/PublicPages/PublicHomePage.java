package com.globant.mx.directv.PublicPages;


import java.util.Calendar;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.globant.mx.directv.contants.TravelocityMainConstants;
import com.globant.mx.directv.utilities.FechasUtilerias;
import com.globant.mx.directv.utilities.PageUtils;



public class PublicHomePage extends PageUtils{
	
	//
	private static PublicHomePage homePage;
	
	@FindBy(id="flight-adults-hp-flight")
	private WebElement logginButtton;
	
	
	@FindBy(id="flight-departing-hp-flight")
	private WebElement inputDateDeparting;
	
	

	private String FlyingTo; 
	

	private String flyingFrom;
	
	
	
	
	
	public String getFlyingTo() {
		return FlyingTo;
	}

	public void setFlyingTo(String flyingTo) {
		FlyingTo = flyingTo;
	}

	public String getFlyingFrom() {
		return flyingFrom;
	}

	public void setFlyingFrom(String flyingFrom) {
		this.flyingFrom = flyingFrom;
	}

	private PublicHomePage(WebDriver driver) {
		super(driver);
		driver.get("http://www.travelocity.com/");
	}
	
	public TravelocityFlightsSearchPOJO selectTravel() {
		//Declare fields
		String mainWindowHandle = getDriver().getWindowHandle();
		
		setWait(new WebDriverWait(getDriver(), 10));
		
		// botton de viajes , le da click
		findElementById(TravelocityMainConstants.BTN_TAB_FLIGHT_TAB_HP_ID).click();
		
		// IDA
		flyingFrom = selectTravel(findElementById(TravelocityMainConstants.TXT_CAMP_ORIGIN_FLY_ID) , TravelocityMainConstants.ORIGIN_FLY, 0).getText();
		
		// REGRESO
		FlyingTo = selectTravel(findElementById(TravelocityMainConstants.TXT_CAMP_DESTINATION_ID), TravelocityMainConstants.DESTINATION_FLY, 0).getText();
		
		// select num Adults
		selectAdults(logginButtton, 1);
		
		// select Dates
		waitForAElementClick(inputDateDeparting);
		getAccion().click(inputDateDeparting).perform();
		
		Calendar calendario = Calendar.getInstance();
		calendario = FechasUtilerias.sumaMesesACalendario(calendario, 2);
		
		Calendar fechaReturn = calendario;
		FechasUtilerias.sumarDiasACalendario(fechaReturn, 3);
		
		
		selectDateFromXPath(calendario);
		clickButtonWithId("flight-returning-hp-flight");
		selectDateFromXPath(fechaReturn);
		
		// buttonSearch
		clickButtonWithXpath(TravelocityMainConstants.BTN_SEARCH_XPATH);
		
		// quitar el popup
		Set<String> set = getDriver().getWindowHandles();
		for(String windowHandle : set) {
		if(!mainWindowHandle.equals(windowHandle)) {
			getDriver().switchTo().window(windowHandle);
			getDriver().close();
			getDriver().switchTo().window(mainWindowHandle);
			break;
			}
		}
		return new TravelocityFlightsSearchPOJO(getDriver());
	}
	
	public WebElement selectTravel(WebElement travel, String text, int optionToSelect) {
		if(travel != null) {
			getWait().until(ExpectedConditions.elementToBeClickable(travel));
			getAccion().click(travel).perform();
			getAccion().sendKeys(travel, text).perform();
			
			By result = By.xpath("//a[@id='aria-option-"+optionToSelect+"']/div[contains(@class, 'multiLineDisplay')]");
			getWait().until(ExpectedConditions.presenceOfElementLocated(result));
			getAccion().sendKeys(Keys.TAB).perform();	
		}
		return travel;
	}
	
	
	
	public void selectAdults(WebElement elemento, int valor) {
		 getSelect(elemento).selectByValue(String.valueOf(valor));
	}
	
	
	
	public void selectDateFromXPath(Calendar calendario) {
		// calendario NO SE USA POR AHORITA
		WebElement nextMonth = getDriver().findElement(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
		waitForAElementClick(nextMonth);
		getAccion().doubleClick(nextMonth).perform();
		WebElement elementoDay = getDriver().findElement(By.xpath(".//div[@class='datepicker-cal-month'][2]/table/tbody/tr/td/button[text()=1]"));
		elementoDay.click();
		getAccion().sendKeys(Keys.ESCAPE).perform();
	}
	
	public static PublicHomePage getInstance(WebDriver driver) {
		if(homePage == null) {
			homePage = new PublicHomePage(driver);
		}
		return homePage;
	}
	
}
