package com.duc.ptc.core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chrome extends DriverBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);

    @Override
    public WebDriver initDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        if (isHeadless != null) {
            double screenWidth = screenSize.getWidth();
            double screenHeight = screenSize.getHeight();
            if (isHeadless.equalsIgnoreCase("true") || isHeadless.equalsIgnoreCase("new")) {
                LOGGER.info(String.format("Headless mode detected. Add `--window-size=%.0fx%.0f` argument to Chrome Options", screenWidth, screenHeight ));
//                chromeOptions.addArguments("--start-maximized");
//                start-maximized argument seems not to maximize window in headless mode, need to hard-core the resolution
                chromeOptions.addArguments(String.format("--window-size=%.0fx%.0f", screenWidth, screenHeight));
            }
            chromeOptions.addArguments("--headless=" + isHeadless);
        }
        return new ChromeDriver(chromeOptions);
    }
}
