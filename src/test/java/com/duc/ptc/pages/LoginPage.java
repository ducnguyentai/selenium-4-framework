package com.duc.ptc.pages;

import com.duc.ptc.core.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage implements IPage {
    @FindBy(css="[name='email']")
    WebElement emailTxt;

    @FindBy(css="[type='submit']")
    WebElement continueBtn;

    @FindBy(css="")
    WebElement passwordTxt;

    @FindBy(css="")
    WebElement loginBtn;

    @Override
    public boolean isAtCurrentPage(){
        return waitForElementVisible(emailTxt).isDisplayed();
    }

    public LoginPage inputEmail(String email) {
        emailTxt.sendKeys(email);
        return this;
    }

    public LoginPage clickContinueButton() {
        continueBtn.click();
        return this;
    }
}
