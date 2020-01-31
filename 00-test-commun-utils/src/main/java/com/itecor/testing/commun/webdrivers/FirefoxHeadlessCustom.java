package com.itecor.testing.commun.webdrivers;


import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FirefoxHeadlessCustom implements DriverSource {

    private static final Logger logger = LoggerFactory.getLogger(FirefoxHeadlessCustom.class);

    @Override
    public WebDriver newDriver() {
        logger.info("test with the driver Firefox Headless");
        try {
            System.setProperty("webdriver.gecko.driver", WebDriverCustomFactory.getDriverByOSByBrowser(EnumBrowser.FIREFOX));
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(true);
            WebDriver driver = new FirefoxDriver(options);
            return driver;
        } catch (Exception e) {
            logger.error("Driver Firefox Headless stack trace");
            throw new Error(e);
        }

    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }

}