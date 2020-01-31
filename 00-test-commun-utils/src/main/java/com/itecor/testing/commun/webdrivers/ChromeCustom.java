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


public class ChromeCustom implements DriverSource {


    private static final Logger logger = LoggerFactory.getLogger(ChromeCustom.class);
    public static String environment = System.getProperty("environment");
    ;

    public static WebDriver driver;


    @Override
    public WebDriver newDriver() {
        logger.info("test with the driver Chrome !");
        try {
            System.setProperty("webdriver.chrome.driver", WebDriverCustomFactory.getDriverByOSByBrowser(EnumBrowser.CHROME));
            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);
            options.setHeadless(false);
            //options.addArguments("--start-maximized");
            options.addArguments("--start-fullscreen");
            options.addArguments("--incognito");

            switch (environment) {
                case "grid":
                    DesiredCapabilities desiredCap = DesiredCapabilities.chrome();
                    desiredCap.setCapability(ChromeOptions.CAPABILITY, options);
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCap);
                    driver.manage().window().maximize();
                    break;
                case "local":
                default:
                    driver = new ChromeDriver(options);
                    break;
            }

            return driver;

        } catch (Exception e) {
            logger.error("Driver Chrome stack trace");
            throw new Error(e);
        }
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}