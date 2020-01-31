package com.itecor.qa.pages;

import Utilities.ApplicationConfigReader;
import Utilities.DOMUtilities;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@DefaultUrl("http://www.itecor.com")
public class MainPage extends PageObject {

    @FindBy(id="menu")
    WebElement divMenu;

    public void clickOnTheHamburgerButton() {
        divMenu.click();
    }

    public boolean isAboutUsLinkPresent() {
        String xpathExp = "//ul[@id='menu-new-home-page-menu']//a[text()='about us']";
        return DOMUtilities.checkElementVisibilityBy(getDriver(), 3, By.xpath(xpathExp));
    }
}
