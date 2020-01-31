package com.itecor.qa.steps;

import Utilities.ApplicationConfigReader;
import com.itecor.qa.pages.MainPage;
import net.thucydides.core.annotations.Step;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPageSteps {

    MainPage mainPage;

    @Step
    public void openItecorHomePage() {
        // It will use the DefaultUrl tag in the PageObject class
        mainPage.open();
    }

    @Step
    public void clickOnTheHamburgerButton() {
         mainPage.clickOnTheHamburgerButton();
    }

    @Step
    public void checkIfHamburgerSectionIsPresent(String hamburgerSection) {

        System.out.println(ApplicationConfigReader.getInstance().getPropAValue());

        switch (hamburgerSection) {
            default:
            case "about us":
                assertThat(mainPage.isAboutUsLinkPresent()).isTrue();
        }

    }
}
