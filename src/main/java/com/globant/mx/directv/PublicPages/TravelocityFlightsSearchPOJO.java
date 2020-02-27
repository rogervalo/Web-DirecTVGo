package com.globant.mx.directv.PublicPages;





import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.globant.mx.directv.contants.TravelocityFlightsSearchConstants;
import com.globant.mx.directv.utilities.PageUtils;



public class TravelocityFlightsSearchPOJO extends PageUtils {

	private static final Logger LOG = Logger.getLogger(TravelocityFlightsSearchPOJO.class);
	

	@FindBy(xpath = "//button[@data-leg0-natural-key='a7edc991769e2ec5dc234ae0a5deea42']")
	private WebElement btnResultsListBy;

	@FindBy(className = "title-city-text")
	private WebElement headerTipeSelection;

	public TravelocityFlightsSearchPOJO(WebDriver driver) {
		super(driver);
	}

	public TravelocityFlightInformation run()  {
		
		// declare your local fields
		By comboBoxSortTravel;

		setWait(new WebDriverWait(getDriver(), 10));

		getWait().until(ExpectedConditions.visibilityOf(headerTipeSelection));

		if (!headerTipeSelection.getText().toUpperCase().contains("DEPARTURE")) {
			LOG.info("no contiene la palabra salida DEPARTURE");
			sleep(3000);
		}

		// sort by sort duration
		comboBoxSortTravel = By.name("sort");
		WebElement elemento = getDriver().findElement(comboBoxSortTravel);
		getSelect(elemento).selectByVisibleText("Duration (Shortest)");

		// waiting for sort
		implicitWait(getDriver(), 15);

		// Encuentra Lista de los elementos
		comboBoxSortTravel =  By.xpath(TravelocityFlightsSearchConstants.COMBO_BOX_SORT_TRAVEL_XPATH);
		
		// click to the departure
		getWait().until(ExpectedConditions.elementToBeClickable(btnResultsListBy));
		btnResultsListBy.click();
		
		// Wait for get the message for sellect the returns
		getWait().until(ExpectedConditions.visibilityOf(headerTipeSelection));
		
		
		// select the return
		WebElement btnSelectReturn = getDriver().findElement(By.xpath(TravelocityFlightsSearchConstants.BUTTON_SEARCH_RETURNS_XPATH));
		getWait().until(ExpectedConditions.elementToBeClickable(btnSelectReturn));
		getAccion().click(btnSelectReturn).perform();
		
		sleep(3000);
		return new TravelocityFlightInformation(getDriver());
	}

	public void explicitWaitClickByText(String text) {
		(new WebDriverWait(getDriver(), 10))
				.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(text.trim())));
	}

}
