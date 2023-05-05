package com.duc.ptc.core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Edge extends DriverBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);

    @Override
    public WebDriver initDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions egdeOptions = new EdgeOptions();
        if (isHeadless != null) {
            double screenWidth = screenSize.getWidth();
            double screenHeight = screenSize.getHeight();
            if (isHeadless.equalsIgnoreCase("true") || isHeadless.equalsIgnoreCase("new")) {
                LOGGER.info(String.format("Headless mode detected. Add `--window-size=%.0fx%.0f` argument to Chrome Options", screenWidth, screenHeight ));
//                chromeOptions.addArguments("--start-maximized");
//                start-maximized argument seems not to maximize window in headless mode, need to hard-core the resolution
                egdeOptions.addArguments(String.format("--window-size=%.0fx%.0f", screenWidth, screenHeight));
            }
            egdeOptions.addArguments("--headless=" + isHeadless);
        }
        return new EdgeDriver(egdeOptions);
    }
}
