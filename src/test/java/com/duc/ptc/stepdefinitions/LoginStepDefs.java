package com.duc.ptc.stepdefinitions;

import com.duc.ptc.constant.Const;
import com.duc.ptc.core.utils.ScenarioContext;
import com.duc.ptc.modals.User;
import com.duc.ptc.pages.LoginPage;
import com.duc.ptc.pages.PageFactory;
import io.cucumber.java.en.When;

public class LoginStepDefs extends BaseStepDefs{
    private LoginPage loginPage = (LoginPage) PageFactory.getPage("login");
    public LoginStepDefs(ScenarioContext context) {
        super(context);
    }

    @When("the user inputs email")
    public void the_user_input_email() {
        String email = ((User) context.getContext(Const.USER)).getEmail();
        loginPage.inputEmail(email);
    }
}
