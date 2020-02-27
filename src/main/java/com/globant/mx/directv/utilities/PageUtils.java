package com.globant.mx.directv.utilities;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class PageUtils.
 */
public abstract class PageUtils {
	
	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(PageUtils.class);
	
	/** The driver. */
	private WebDriver driver;
	
	/** The wait. */
	private WebDriverWait wait;
	
	/** The accion. */
	private Actions accion;

	/**
	 * Instantiates a new page utils.
	 */
	public PageUtils() {
	}

	/**
	 * Instantiates a new page utils.
	 *
	 * @param pDriver the driver
	 */
	public PageUtils(WebDriver pDriver) {
		PageFactory.initElements(pDriver, this);
		if (wait == null) {
			wait = new WebDriverWait(pDriver, 10);
		}
		driver = pDriver;
		accion = new Actions(getDriver());
	}

	/**
	 * Wait seconds.
	 *
	 * @param seconds the seconds
	 */
	public void waitSeconds(Long seconds) {
		if (wait != null) {
			try {
				synchronized(wait) {
					while(true) {
						wait.wait(seconds*1000);
						break;
					}
				}
				
			} catch (InterruptedException e) {
				LOG.error("Error on waitSeconds() " + e.getMessage());
				Thread.currentThread().interrupt();
			}
		}
	}

	/**
	 * Wait seconds.
	 *
	 * @param seconds the seconds
	 * @param driver the driver
	 */
	public void waitSeconds(int seconds, WebDriver driver) {
		if (driver != null) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}

	/**
	 * Gets the wait.
	 *
	 * @return the wait
	 */
	protected WebDriverWait getWait() {
		return wait;
	}

	/**
	 * Sets the wait.
	 *
	 * @param wait the new wait
	 */
	protected void setWait(WebDriverWait wait) {
		this.wait = wait;
	}

	/**
	 * Sets the driver.
	 *
	 * @param driver the new driver
	 */
	protected void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	protected WebDriver getDriver() {
		return driver;
	}

	/**
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	public Actions getAccion() {
		return accion;
	}

	/**
	 * Sets the accion.
	 *
	 * @param accion the new accion
	 */
	public void setAccion(Actions accion) {
		this.accion = accion;
	}

	/**
	 * Wait for A element click.
	 *
	 * @param elemento the elemento
	 */
	public void waitForAElementClick(WebElement elemento) {
		getWait().until(ExpectedConditions.elementToBeClickable(elemento));
	}

	/**
	 * Wait for element visible.
	 *
	 * @param elemento the elemento
	 * @param wait the wait
	 */
	public void waitForElementVisible(By elemento, WebDriverWait wait) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elemento));
	}

	/**
	 * Wait present located element.
	 *
	 * @param xpath the xpath
	 */
	public void waitPresentLocatedElement(String xpath) {
		By elemento = By.xpath(xpath);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(elemento));
	}

	/**
	 * Implicit wait.
	 *
	 * @param webDriver the web driver
	 * @param seconds the seconds
	 */
	public void implicitWait(WebDriver webDriver, int seconds) {
		webDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	/**
	 * Gets the element X path.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @return the element X path
	 */
	public String getElementXPath(WebDriver driver, WebElement element) {
		return (String) ((JavascriptExecutor) driver).executeScript(
				"gPt=function(c){if(c.id!==''){return'id(\"'+c.id+'\")'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase();",
				element);
	}

	/**
	 * Switch taband close.
	 *
	 * @param myDriver the my driver
	 */
	public void switchTabandClose(WebDriver myDriver) {
		Set<String> windows = myDriver.getWindowHandles();
		String mainwindow = myDriver.getWindowHandle();

		for (String handle : windows) {
			myDriver.switchTo().window(handle);
			LOG.info("switched to " + myDriver.getTitle() + "  Window");
			String pagetitle = myDriver.getTitle();
			if (pagetitle.equalsIgnoreCase("XYZ Title")) {
				myDriver.close();
			}
		}

		myDriver.switchTo().window(mainwindow);
	}

	/**
	 * Sleep.
	 *
	 * @param seconds the seconds
	 */
	public void sleep(long seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException ex) {
			LOG.error("Error on sleep thread " + ex.getMessage());
			Thread.currentThread().interrupt();
		} catch (Exception ex) {
			LOG.error("Error on sleep thread " + ex.getMessage());
		}
	}

	/**
	 * Random number.
	 *
	 * @param num1 the num 1
	 * @param num2 the num 2
	 * @return the int
	 */
	public int randomNumber(int num1, int num2) {
		try {
			return (int) Math.floor(Math.random() * (num1 - (num2 + 1)) + (num2));
		} catch (Exception ex) {
			LOG.error("Error on randomNumber() "+ex.getMessage());
			return num2;
		}
	}

	/**
	 * Gets the select.
	 *
	 * @param elemento the elemento
	 * @return the select
	 */
	public Select getSelect(WebElement elemento) {
		return  new Select(elemento);
	}

	
	/**
	 * Click button with xpath.
	 *
	 * @param xpath the xpath
	 * @return the web element
	 */
	public WebElement clickButtonWithXpath(String xpath) {
		By element = By.xpath(xpath);
		getWait().until(ExpectedConditions.presenceOfElementLocated(element));
		WebElement buttonElement = findElementByBy(element);
		getAccion().click(buttonElement).perform();
		return buttonElement;
	}
	
	public WebElement clickButtonWithId(String id) {
		By element = By.id(id);
		getWait().until(ExpectedConditions.presenceOfElementLocated(element));
		WebElement buttonElement = findElementByBy(element);
		getAccion().click(buttonElement).perform();
		return buttonElement;
	}
	
	
	public WebElement clickButtonWithBy(By element) {
		getWait().until(ExpectedConditions.presenceOfElementLocated(element));
		WebElement buttonElement = findElementByBy(element);
		getAccion().click(buttonElement).perform();
		return buttonElement;
	}
	
	
	public WebElement findElementById(String id) {
		By element = By.id(id);
		getWait().until(ExpectedConditions.presenceOfElementLocated(element));
		return findElementByBy(element);
	}
	
	public WebElement findElementByBy(By by) {
		WebElement webElement = getDriver().findElement(by);
		getWait().until(ExpectedConditions.elementToBeClickable(webElement));
		return webElement;
	}
	

}
