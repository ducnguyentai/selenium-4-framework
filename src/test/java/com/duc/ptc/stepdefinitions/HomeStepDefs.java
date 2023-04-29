package com.duc.ptc.stepdefinitions;

import com.duc.ptc.core.Utils.ScenarioContext;
import com.duc.ptc.pages.HomePage;
import com.duc.ptc.pages.PageFactory;
import io.cucumber.java.en.When;

public class HomeStepDefs extends BaseStepDefs{
    public HomeStepDefs(ScenarioContext context) {
        super(context);
    }

    @When("the user clicks on {string} on Home Page")
    public void the_user_clicks_on_CTA_on_home_page(String cta) {
        switch(cta.toLowerCase()) {
            case "login":
                ((HomePage)PageFactory.getPage("home")).clickOnLoginButton();
                break;
            default:
                throw new IllegalArgumentException("Click on `" + cta + "` is not supported");
        }
    }
}
