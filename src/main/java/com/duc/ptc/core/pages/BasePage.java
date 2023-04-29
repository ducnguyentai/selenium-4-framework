package com.duc.ptc.core.pages;

import com.duc.ptc.core.Utils.CommonUtils;
import com.duc.ptc.core.driver.DriverFactory;
import com.duc.ptc.core.webconst.Const;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage() {
        driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(Const.WAIT_DURATION));
    }

    protected WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElementPresent(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected Boolean isElementPresent(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected Boolean isElementAttributeContainValue(WebElement element, String attribute, String value) {
        return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    protected Boolean isElementCSSContainsValue(WebElement element, String css, String value) {
        return element.getCssValue(css).contains(value);
    }

    protected WebElement scrollDownToElement(WebElement element, int retry) {
        for(int i = 1; i < retry; i++) {
            try {
                if (element.isDisplayed()) {
                    logger.info("Retry number: " + i + " >>> Given element is visible.");
                    //to avoid overlap with page footer
                    smallScroll("Down");
                    return element;
                } else {
                    CommonUtils.delay(1);
                    scroll("Down");
                }
            } catch (NoSuchElementException e) {
                logger.debug("Retry number: " + i + " >>> Unable to find given element.");
            }
        }
        throw new NoSuchElementException("Cannot find given element: " + element + "\nRetry: " + retry + "\n");
    }
    protected WebElement scrollDownToElement(WebElement element) {
        for(int i = 1; i < Const.RETRY; i++) {
            try {
                if (element.isDisplayed()) {
                    logger.info("Retry number: " + i + " >>> Given element is visible.");
                    //to avoid overlap with page footer
                    smallScroll("Down");
                    return element;
                } else {
                    CommonUtils.delay(1);
                    scroll("Down");
                }
            } catch (NoSuchElementException e) {
                logger.debug("Retry number: " + i + " >>> Unable to find given element.");
            }
        }
        throw new NoSuchElementException("Cannot find given element: " + element + "\nRetry: " + Const.RETRY + "\n");
    }

    protected WebElement scrollUpToElement(WebElement element, int retry) {
        for(int i = 1; i < retry; i++) {
            try {
                if (element.isDisplayed()) {
                    logger.info("Retry number: " + i + " >>> Given element is visible.");
                    //to avoid overlap with page header
                    smallScroll("Up");
                    return element;
                } else {
                    CommonUtils.delay(1);
                    scroll("Up");
                }
            } catch (NoSuchElementException e) {
                logger.debug("Retry number: " + i + " >>> Unable to find given element.");
            }
        }
        throw new NoSuchElementException("Cannot find given element: " + element + "\nRetry: " + retry + "\n");
    }
    protected WebElement scrollUpToElement(WebElement element) {
        for(int i = 1; i < Const.RETRY; i++) {
            try {
                if (element.isDisplayed()) {
                    logger.info("Retry number: " + i + " >>> Given element is visible.");
                    //to avoid overlap with page header
                    smallScroll("Up");
                    return element;
                } else {
                    CommonUtils.delay(1);
                    scroll("Up");
                }
            } catch (NoSuchElementException e) {
                logger.debug("Retry number: " + i + " >>> Unable to find given element.");
            }
        }
        throw new NoSuchElementException("Cannot find given element: " + element + "\nRetry: " + Const.RETRY + "\n");
    }
    protected void scroll(String direction) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        switch (direction.toLowerCase()) {
            case "up":
                js.executeScript("javascript:window.scrollBy(250,0)");
                break;
            case "down":
                js.executeScript("javascript:window.scrollBy(0,250)");
                break;
            default:
                throw new IllegalArgumentException("direction: '" + direction + "' is not supported. Try 'Down', 'Up'.");
        }
    }

    protected void smallScroll(String direction) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        switch (direction.toLowerCase()) {
            case "up":
                js.executeScript("javascript:window.scrollBy(150,0)");
                break;
            case "down":
                js.executeScript("javascript:window.scrollBy(0,150)");
                break;
            default:
                throw new IllegalArgumentException("direction: '" + direction + "' is not supported. Try 'Down', 'Up'.");
        }
    }

    protected void scrollElementIntoView(WebElement element) {
        try {
            if (element.isDisplayed()) {
                logger.info("Given element is already in view port");
            } else {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", element);
            }
        } catch(NoSuchElementException e) {

        }
    }
}
