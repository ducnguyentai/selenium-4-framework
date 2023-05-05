package com.duc.ptc.core.driver;

import com.duc.ptc.core.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Browserstack extends DriverBase {
    @Override
    public WebDriver initDriver() {
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
            return new RemoteWebDriver(new URL(BROWSERSTACK_URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Cannot initialize Browserstack driver");
    }
}
