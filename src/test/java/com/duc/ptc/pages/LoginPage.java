package com.duc.ptc.pages;

import com.duc.ptc.core.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css="[name='email']")
    WebElement emailTxt;

    public boolean isAtCurrentPage(){
        return waitForElementVisible(emailTxt).isDisplayed();
    }
}
