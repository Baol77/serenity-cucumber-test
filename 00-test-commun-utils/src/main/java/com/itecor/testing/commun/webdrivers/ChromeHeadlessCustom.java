package com.itecor.testing.commun.webdrivers;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fredericmagnenat on 09.12.17.
 */
public class ChromeHeadlessCustom implements DriverSource {

    private static final Logger logger = LoggerFactory.getLogger(ChromeHeadlessCustom.class);
    private static String environment = System.getProperty("environment");
    private static WebDriver driver;

    private Map<String, Object> preferences() {
        Map<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("pdfjs.disabled", true);
        return chromePrefs;
    }

    //to disable browser extension popup
    @Override
    public WebDriver newDriver() {
        logger.info("test with the driver Chrome Headless");
        try {
            System.setProperty("webdriver.chrome.driver", WebDriverCustomFactory.getDriverByOSByBrowser(EnumBrowser.CHROME));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--test-type");
            options.setHeadless(true);
            options.setAcceptInsecureCerts(true);
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--start-fullscreen");
            options.addArguments("--incognito");

            options.setExperimentalOption("prefs", preferences());

            switch (environment) {
                case "grid":

                    DesiredCapabilities desiredCap = DesiredCapabilities.chrome();
                    desiredCap.setCapability(ChromeOptions.CAPABILITY, options);
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCap);
                default:
                case "local":
                    driver = new ChromeDriver(options);
                    break;
            }

            return driver;

        } catch (Exception e) {
            logger.error("Driver Chrome Headless stack trace");
            throw new Error(e);
        }
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }

}
