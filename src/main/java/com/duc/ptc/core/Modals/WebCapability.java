package com.duc.ptc.core.Modals;

import com.duc.ptc.core.enums.Browser;

public class WebCapability {
    private Browser browser;
    private String baseURL;

    public WebCapability() {

    }

    public WebCapability setBrowser(Browser browser) {
        this.browser = browser;
        return this;
    }

    public WebCapability setBaseURL(String baseURL) {
        this.baseURL = baseURL;
        return this;
    }

    public Browser getBrowser() {
        return browser;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String toString() {
        return "[Web Capability]: {\n"
                + "\tBrowser: " + browser.toString() + "\n"
                + "\tBase URL: " + baseURL + "\n"
                + "}";
    }
}
