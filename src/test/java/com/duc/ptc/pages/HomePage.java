package com.duc.ptc.pages;

import com.duc.ptc.core.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage implements IPage{
    @FindBy(css = "[href='/en/login']")
    WebElement loginBtn;
    @FindBy(xpath = "//h2[contains(@class, 'carousel_app-carousel-title') and contains(text(), 'Live TV')]")
    WebElement liveTVCarousel;

    public LoginPage clickOnLoginButton() {
        loginBtn.click();
        return new LoginPage();
    }

    public HomePage scrollToLiveTVCarousel() {
        scrollDownToElement(liveTVCarousel);
        return this;
    }
    @Override
    public boolean isAtCurrentPage() {
        return waitForElementVisible(loginBtn).isDisplayed();
    }
}
