package com.duc.ptc.stepdefinitions;

import com.duc.ptc.constant.Const;
import com.duc.ptc.core.utils.CommonUtils;
import com.duc.ptc.core.utils.ScenarioContext;
import com.duc.ptc.modals.User;
import com.duc.ptc.pages.PageFactory;
import com.duc.ptc.utils.Helper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class CommonStepDefs extends BaseStepDefs {
    public CommonStepDefs(ScenarioContext context) {
        super(context);

    }

    @Given("the user is already created in system")
    public void the_user_is_already_created(List<Map<String, String>> rows) {
        rows.forEach(row -> {
            User user = Helper.getUser(row.get("User Type"));
            context.setContext(Const.USER, user);
        });
    }

    @Then("the user is at {string} page")
    public void the_user_is_at_page(String page) {
        Assert.assertTrue(String.format("The user is Not at `%s` page", page), PageFactory.getPage(page).isAtCurrentPage());
    }

    @Then("delay {int}")
    public void delay(int duration) {
        CommonUtils.delay(duration);
    }
}
