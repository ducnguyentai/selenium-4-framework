package com.duc.ptc.core.driver;

import org.openqa.selenium.WebDriver;

import java.awt.*;

public abstract class DriverBase {
    protected WebDriver driver;
    protected final String isHeadless = System.getProperty("isHeadless");
    protected final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public DriverBase() {
    }

    protected abstract WebDriver initDriver();
}
