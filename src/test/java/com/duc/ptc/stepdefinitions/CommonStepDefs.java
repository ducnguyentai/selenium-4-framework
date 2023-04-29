package com.duc.ptc.stepdefinitions;

import com.duc.ptc.core.Utils.ScenarioContext;
import com.duc.ptc.pages.HomePage;
import com.duc.ptc.pages.LoginPage;
import com.duc.ptc.pages.PageFactory;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class CommonStepDefs extends BaseStepDefs{
    public CommonStepDefs(ScenarioContext context) {
        super(context);

    }

    @Then("the user is at {string} page")
    public void the_user_is_at_page(String page) {
        switch (page.toLowerCase()) {
            case "home":
                Assert.assertTrue(((HomePage) PageFactory.getPage("Home")).isAtCurrentPage());
                break;
            case"login":
                Assert.assertTrue(((LoginPage) PageFactory.getPage("Login")).isAtCurrentPage());
                break;
            default:
                throw new IllegalArgumentException("Page: " + page + " is not supported.");
        }
    }
}
