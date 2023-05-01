package com.duc.ptc.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"com.duc.ptc.stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-reports/reports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber-html-reports/cucumber.json"
        },
        tags = "not @ignore"

)
public class Runner {
}
