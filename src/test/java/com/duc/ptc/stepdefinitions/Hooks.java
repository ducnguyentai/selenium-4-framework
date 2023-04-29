package com.duc.ptc.stepdefinitions;

import com.duc.ptc.core.Modals.WebCapability;
import com.duc.ptc.core.Utils.Profile;
import com.duc.ptc.core.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    private WebDriver driver;
    @Before
    public void setup() throws MalformedURLException {
        WebCapability cap;
        final String environmentProperties = System.getProperty("env");
        if(environmentProperties == null) {
            cap = Profile.createInstance().setEnvironmentVariables("prod_env.properties");
        } else {
            cap = Profile.createInstance().setEnvironmentVariables(environmentProperties);
        }
        driver = DriverFactory.start(cap.getBrowser());
        driver.manage().window().maximize();
        if(environmentProperties != null) {
            //test environment need basic authentication
            if (!environmentProperties.contains("prod")) {
                //cannot use HasAuthentication with Firefox driver and RemoteWebDriver
                if(!(driver instanceof FirefoxDriver) && !(driver instanceof RemoteWebDriver)) {
                    logger.info("[ENVIRONMENT] > Loading Test Environment... ");
                    logger.info("[ENVIRONMENT] > Adding basic authentication to Test Environment... ");
                    ((HasAuthentication) driver).register(() -> new UsernameAndPassword("FRONTENDUSER", "aUCmNKHTCyLrW3eJ"));
                }
            }
        }
        driver.get(cap.getBaseURL());
    }

    @After(order = 1)
    public void tearDown() {
        DriverFactory.stop();
    }

    @After(order = 2)
    public void screenShootOnFailedTest(Scenario scenario) {
        if(scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
    }
}
