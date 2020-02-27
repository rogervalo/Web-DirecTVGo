package com.globant.mx.directv.utilities;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.log4testng.Logger;

public class Mydriver {

	private static WebDriver driver;
	private static Mydriver myDriver;
	private static final Logger LOG = Logger.getLogger(Mydriver.class);

	private Mydriver(String navegador) {
		String os = System.getProperty("os.name").toUpperCase().contains("WINDOWS") ? ".exe" : "";
		if (StringUtils.isNotEmpty(navegador)) {
			File driverArchive;
			String path;

			switch (navegador.toUpperCase()) {
			case "CHROME":
				try {
					driverArchive = new File("src/main/webapp/WEB-INF/libs/chromedriver" + os);
					path = driverArchive.getAbsolutePath();
					try {
						Runtime.getRuntime().exec("chmod 777 " + path);
					} catch (Exception ex) {
						System.out.println("Exeption :" + ex.getMessage());
					}
					System.setProperty("webdriver.chrome.driver", path);
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--start-maximized");
					driver = new ChromeDriver();
				} catch (Exception ex) {
					LOG.error("Error on Mydriver() " + ex.getMessage());
				}
				break;
			case "FIREFOX":
				driverArchive = new File("src/main/webapp/WEB-INF/libs/geckodriver" + os);
				path = driverArchive.getAbsolutePath();
				if (os.equals("")) {
					try {
						Runtime.getRuntime().exec("chmod 777 " + path);
					} catch (Exception ex) {
						LOG.error("error en driver : " + ex.getMessage());
					}
				}
				System.setProperty("webdriver.gecko.driver", path);
				driver = new FirefoxDriver();
				break;
			case "EXPLORER":
				driverArchive = new File("src/main/webapp/WEB-INF/libs/MicrosoftWebDriver.exe");
				path = driverArchive.getAbsolutePath();
				System.setProperty("webdriver.edge.driver", path);
				driver = new EdgeDriver();
				break;
			case "FIREFOXP":
				File file = new File("src/main/webapp/WEB-INF/libs/firebug-1.8.1.xpi");
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				firefoxProfile.addExtension(file);
				firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.8.1"); // Avoid startup screen
				break;
			default:
				driver.manage().deleteAllCookies();
				break;
			}
		}
	}

	public static Mydriver getInstance() {
		if (myDriver == null) {
			
			myDriver = new Mydriver(FileUtils.readPropertiesFile("browser"));
		}
		return myDriver;
	}

	public static WebDriver getWebDriverInstance() {
		if (driver == null) {
			getInstance();
		}
		return driver;
	}

	public static void maximiseBrowser() {
		getWebDriverInstance().manage().window().maximize();
	}
	
	public static void quitDriver() {
		getWebDriverInstance().close();
	}
	
	public static void deleteAllCookies() {
		getWebDriverInstance().manage().deleteAllCookies();
	}
	
	public static void openHomePage() {
			getWebDriverInstance().get("http://" +FileUtils.readPropertiesFile("environment") + ".directvgo.com/" + FileUtils.readPropertiesFile("market"));
	}
}
