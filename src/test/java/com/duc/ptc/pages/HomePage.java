package com.duc.ptc.pages;

import com.duc.ptc.core.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(css = "[href='/en/login']")
    WebElement loginBtn;

    public boolean isAtCurrentPage() {
       return waitForElementVisible(loginBtn).isDisplayed();
    }

    public HomePage clickOnLoginButton() {
        loginBtn.click();
        return this;
    }
}
