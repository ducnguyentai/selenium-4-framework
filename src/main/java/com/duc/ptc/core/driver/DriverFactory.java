package com.duc.ptc.core.driver;

import com.duc.ptc.core.enums.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
    private static WebDriver driver;
    private static DriverBase driverBase;

    private DriverFactory() {
    }

    public static WebDriver initDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                driverBase = new Chrome();
                driver = driverBase.initDriver();
                break;
            case EDGE:
                driverBase = new Edge();
                driver = driverBase.initDriver();
                break;
            case FIREFOX:
                driverBase = new FireFox();
                driver = driverBase.initDriver();
                break;
            case BROWSERSTACK:
                driverBase = new Browserstack();
                driver = driverBase.initDriver();
                break;
            default:
                throw new IllegalArgumentException("browser: '" + browser + "' is not supported.");
        }
        return driver;
    }

    public static void stop() {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }

    public static String getBrowserName() {
        if(driver instanceof ChromeDriver) {
            return Browser.CHROME.toString();
        }
        if(driver instanceof FirefoxDriver) {
            return Browser.FIREFOX.toString();
        }
        if(driver instanceof EdgeDriver) {
            return Browser.EDGE.toString();
        }
        if(driver instanceof RemoteWebDriver) {
            return Browser.BROWSERSTACK.toString();
        }
        return null;
    }

    public static String getDriverPath(Browser browser) {
        switch (browser) {
            case CHROME:
                return WebDriverManager.chromedriver().getDownloadedDriverPath();
            case EDGE:
                return WebDriverManager.firefoxdriver().getDownloadedDriverPath();
            case FIREFOX:
                return WebDriverManager.edgedriver().getDownloadedDriverPath();
            default:
                throw new IllegalArgumentException("Browser: `" + browser + "` is not supported.");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
