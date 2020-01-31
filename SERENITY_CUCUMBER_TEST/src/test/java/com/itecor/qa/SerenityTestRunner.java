package com.itecor.qa;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features", plugin={"pretty", "json:target/cucumber.json"})
public class SerenityTestRunner {

}
