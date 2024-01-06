package com.duc.ptc.core.pages;

import com.duc.ptc.core.driver.DriverFactory;
import com.duc.ptc.core.utils.CommonUtils;
import com.duc.ptc.core.webconst.Const;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver;
    private final Wait<WebDriver> wait;

    public BasePage() {
        driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Const.WAIT_DURATION))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
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
        int currentTry = 1;
        while(currentTry < retry) {
            try {
                if (element.isDisplayed()) {
                    LOGGER.info("Retry number: " + currentTry + " >>> Given element is visible.");
                    //to avoid overlap with page footer
                    smallScroll("Down");
                    return element;
                } else {
                    LOGGER.info("Retry number: " + currentTry + " >>> Given element is NOT visible.");
                    scroll("Down");
                    CommonUtils.delay(2);
                    currentTry++;
                }
            } catch (NoSuchElementException e) {
                LOGGER.info("Retry number: " + currentTry + " >>> Unable to find given element.");
                currentTry++;
                scroll("Down");
                CommonUtils.delay(1);
            }
        }
        throw new NoSuchElementException("Cannot find given element: " + element + "\nRetry: " + Const.RETRY + "\n");
    }
    protected WebElement scrollDownToElement(WebElement element) {
        int currentTry = 1;
        while(currentTry < Const.RETRY) {
            try {
                if (element.isDisplayed()) {
                    LOGGER.info("Retry number: " + currentTry + " >>> Given element is visible.");
                    //to avoid overlap with page footer
                    smallScroll("Down");
                    return element;
                } else {
                    LOGGER.info("Retry number: " + currentTry + " >>> Given element is NOT visible.");
                    scroll("Down");
                    CommonUtils.delay(2);
                    currentTry++;
                }
            } catch (NoSuchElementException e) {
                LOGGER.info("Retry number: " + currentTry + " >>> Unable to find given element.");
                currentTry++;
                scroll("Down");
                CommonUtils.delay(1);
            }
        }
        throw new NoSuchElementException("Cannot find given element: " + element + "\nRetry: " + Const.RETRY + "\n");
    }

    protected WebElement scrollUpToElement(WebElement element, int retry) {
        int currentTry = 1;
        while(currentTry < retry) {
            try {
                if (element.isDisplayed()) {
                    LOGGER.info("Retry number: " + currentTry + " >>> Given element is visible.");
                    //to avoid overlap with page footer
                    smallScroll("Up");
                    return element;
                } else {
                    LOGGER.info("Retry number: " + currentTry + " >>> Given element is NOT visible.");
                    scroll("Up");
                    CommonUtils.delay(2);
                    currentTry++;
                }
            } catch (NoSuchElementException e) {
                LOGGER.info("Retry number: " + currentTry + " >>> Unable to find given element.");
                currentTry++;
                scroll("Up");
                CommonUtils.delay(1);
            }
        }
        throw new NoSuchElementException("Cannot find given element: " + element + "\nRetry: " + Const.RETRY + "\n");
    }

    protected WebElement scrollUpToElement(WebElement element) {
        int currentTry = 1;
        while(currentTry < Const.RETRY) {
            try {
                if (element.isDisplayed()) {
                    LOGGER.info("Retry number: " + currentTry + " >>> Given element is visible.");
                    //to avoid overlap with page footer
                    smallScroll("Up");
                    return element;
                } else {
                    LOGGER.info("Retry number: " + currentTry + " >>> Given element is NOT visible.");
                    scroll("Up");
                    CommonUtils.delay(2);
                    currentTry++;
                }
            } catch (NoSuchElementException e) {
                LOGGER.info("Retry number: " + currentTry + " >>> Unable to find given element.");
                currentTry++;
                scroll("Up");
                CommonUtils.delay(1);
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
                LOGGER.info("Given element is already in view port");
            } else {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", element);
            }
        } catch (NoSuchElementException ignored) {
        }
    }

    protected boolean waitForPageLoad() {
        return wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
}
