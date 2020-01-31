package com.itecor.qa.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class StepCollection {

    @Steps
    MainPageSteps mainPageSteps;

    @Given("^As anonymous user I go to the Itecor Homepage$")
    public void asAnonymousUserIGoToTheItecorHomepage() {
        mainPageSteps.openItecorHomePage();
    }

    @When("^I click on the hamburger button$")
    public void iClickOnTheHamburgerButton() {
        mainPageSteps.clickOnTheHamburgerButton();
    }

    @Then("^I am able to see the \"([^\"]*)\" section$")
    public void iAmAbleToSeeTheSection(String arg0) {
       mainPageSteps.checkIfHamburgerSectionIsPresent(arg0);
    }
}
