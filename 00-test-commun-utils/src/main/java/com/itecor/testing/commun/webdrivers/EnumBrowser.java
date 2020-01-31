package com.itecor.testing.commun.webdrivers;

/**
 * Created by fredericmagnenat on 23.11.17.
 */
public enum EnumBrowser {

    CHROME("chrome"),
    FIREFOX("firefox"),
    IExplorer("iexplorer");

    private final String browser;

    private EnumBrowser(final String browser) {
        this.browser = browser;
    }

    public String getBrowser(){
        return browser;
    }
}
