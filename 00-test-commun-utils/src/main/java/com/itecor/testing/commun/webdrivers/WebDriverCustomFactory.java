package com.itecor.testing.commun.webdrivers;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by fredericmagnenat on 23.11.17.
 */
class WebDriverCustomFactory {

    private static final Logger logger = LoggerFactory.getLogger(WebDriverCustomFactory.class);

    protected static EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    private static String getPathSystem(Object propertiesFile) throws IOException {
       return new  File(propertiesFile.toString().trim()).getCanonicalPath();
    }


    public static String getDriverByOSByBrowser(EnumBrowser browser) {
        logger.info("Factory Webdriver : " + browser);
        String path = null;
        Properties properties = new Properties();
        try {
            String filename = "config.properties";
            URL url = ClassLoader.getSystemResource(filename);
            properties.load(url.openStream());
            switch (browser) {
                case CHROME:
                    if (environmentVariables.getProperty("os.name").toLowerCase().contains("windows")) {
                        logger.info("get driver google : " +  getPathSystem(properties.get("WINDOWS_CHROME_PATH")));
                        path = getPathSystem(properties.get("WINDOWS_CHROME_PATH"));
                    } else if(environmentVariables.getProperty("os.name").toLowerCase().contains("linux")) {
                        path = getPathSystem(properties.get("LINUX_CHROME_PATH"));
                    } else if(environmentVariables.getProperty("os.name").toLowerCase().contains("mac")){
                        path = getPathSystem(properties.get("MAC_CHROME_PATH"));
                    }
                    break;
                case FIREFOX:
                    if (environmentVariables.getProperty("os.name").toLowerCase().contains("windows")) {
                        path = getPathSystem(properties.get("WINDOWS_FIREFOX_16_PATH"));
                    } else if(environmentVariables.getProperty("os.name").toLowerCase().contains("linux")) {
                        path = getPathSystem(properties.get("LINUX_FIREFOX_PATH"));
                    } else if(environmentVariables.getProperty("os.name").toLowerCase().contains("mac")){
                        path = getPathSystem(properties.getProperty("MAC_FIREFOX_PATH"));
                    }
                    break;
                case IExplorer:
                    if (environmentVariables.getProperty("os.name").toLowerCase().contains("windows")){
                        path = getPathSystem(properties.getProperty("WINDOWS_IE_PATH"));
                    }
                    break;
                default:
                    break;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return path;
    }


}
