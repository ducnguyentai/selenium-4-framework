package com.duc.ptc.stepdefinitions;

import com.duc.ptc.core.Modals.WebCapability;
import com.duc.ptc.core.utils.Profile;
import com.duc.ptc.core.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);
    private WebDriver driver;
    private WebCapability cap;

    @Before
    public void setup() {
        final String environmentProperties = System.getProperty("env");
        if (environmentProperties == null) {
            cap = Profile.createInstance().setEnvironmentVariables("prod_env.properties");
        } else {
            cap = Profile.createInstance().setEnvironmentVariables(environmentProperties);
        }
        driver = DriverFactory.initDriver(cap.getBrowser());
        driver.manage().window().maximize();
        if (environmentProperties != null) {
            //test environment need basic authentication
            if (!environmentProperties.contains("prod")) {
                //cannot use HasAuthentication with Firefox driver and RemoteWebDriver
                if (!(driver instanceof FirefoxDriver) && !(driver instanceof RemoteWebDriver)) {
                    LOGGER.info("[ENVIRONMENT] > Loading Test Environment... ");
                    LOGGER.info("[ENVIRONMENT] > Adding basic authentication to Test Environment... ");
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
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
    }
}
