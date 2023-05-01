package com.duc.ptc.core.driver;

import com.duc.ptc.core.utils.CommonUtils;
import com.duc.ptc.core.enums.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DriverFactory {
    private static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private static final String isHeadless = System.getProperty("isHeadLess");

    private DriverFactory() {

    }

    public static WebDriver start(Browser browser) {
        if (browser == null) {
            return null;
        }
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless != null) {
                    if (isHeadless.equalsIgnoreCase("true") || isHeadless.equalsIgnoreCase("new")) {
                        logger.info("Headless mode detected. Add `--window-size=1900x1080` argument to Chrome Options");
//                    chromeOptions.addArguments("--start-maximized");
                        //start-maximized argument seems not to maximize window in headless mode, need to hard-core the resolution
                        chromeOptions.addArguments("--window-size=1900x1080");
                    }
                    chromeOptions.addArguments("--headless=" + isHeadless);
                }
                DriverFactory.driver = new ChromeDriver(chromeOptions);
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions egdeOptions = new EdgeOptions();
                if (isHeadless != null) {
                    if (isHeadless.equalsIgnoreCase("true") || isHeadless.equalsIgnoreCase("new")) {
                        logger.info("Headless mode detected. Add `--window-size=1900x1080` argument to Edge Options");
//                    chromeOptions.addArguments("--start-maximized");
                        //start-maximized argument seems not to maximize window in headless mode, need to hard-core the resolution
                        egdeOptions.addArguments("--window-size=1900x1080");
                    }
                    egdeOptions.addArguments("--headless=" + isHeadless);
                }
                DriverFactory.driver = new EdgeDriver(egdeOptions);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless != null) {
                    if (isHeadless.equalsIgnoreCase("true") || isHeadless.equalsIgnoreCase("new")) {
                        logger.info("Headless mode detected. Add `--window-size=1900x1080` argument to Firefox Options");
//                    chromeOptions.addArguments("--start-maximized");
                        //start-maximized argument seems not to maximize window in headless mode, need to hard-core the resolution
                        firefoxOptions.addArguments("--window-size=1900x1080");
                    }
                    firefoxOptions.addArguments("--headless=" + isHeadless);
                }
                DriverFactory.driver = new FirefoxDriver(firefoxOptions);
                break;
            case BROWSERSTACK:
                String BROWSERSTACK_URL;
                String BROWSERSTACK_USERNAME;
                String BROWSERSTACK_ACCESS_KEY;
                DesiredCapabilities capabilities = new DesiredCapabilities();
                Properties config = CommonUtils.readPropertiesFileFromTestResourceFolder("browserstack.properties");
                capabilities.setCapability("os", config.get("os"));
                capabilities.setCapability("browser", config.get("browser"));
                capabilities.setCapability("version", config.get("version"));
                capabilities.setCapability("name", config.get("session_name"));
                BROWSERSTACK_USERNAME = config.getProperty("BROWSERSTACK_USERNAME");
                BROWSERSTACK_ACCESS_KEY = config.getProperty("BROWSERSTACK_ACCESS_KEY");
                BROWSERSTACK_URL = "https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
                try {
                    driver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), capabilities);
                } catch(MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                throw new IllegalArgumentException("browser: '" + browser + "' is not supported.");
        }
        return DriverFactory.driver;
    }

    public static void stop() {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
