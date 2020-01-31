package com.itecor.testing.commun.webdrivers;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

/**
 * Created by zfjfmt on 18.08.2017.
 */
public class InternetExplorerCustom implements DriverSource {

    @Override
    public WebDriver newDriver() {
        WebDriver driver = null;
        try {
            System.setProperty("webdriver.ie.driver", WebDriverCustomFactory.getDriverByOSByBrowser(EnumBrowser.IExplorer));
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
            options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
            options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
            driver = new InternetExplorerDriver(options);
            return driver;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}
