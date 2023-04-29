package com.duc.ptc.core.Utils;

import com.duc.ptc.core.Modals.WebCapability;
import com.duc.ptc.core.enums.Browser;

import java.io.*;
import java.util.Properties;

public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    private WebCapability cap;

    private Profile() {
        cap = new WebCapability();
    }
    private static class LazyInit {
        private static final Profile profile = new Profile();
    }

    public static Profile createInstance() {
        return LazyInit.profile;
    }

    public WebCapability setEnvironmentVariables(String fileName) {
        Properties config = CommonUtils.readPropertiesFileFromTestResourceFolder(fileName);
        cap.setBaseURL(config.getProperty("base_url"));
        //use to override browser key in properties file
        if(System.getProperty("browser") != null) {
            cap.setBrowser(Browser.valueOf(System.getProperty("browser").toUpperCase()));
        } else {
            cap.setBrowser(Browser.valueOf(config.getProperty("browser").toUpperCase()));
        }

        return cap;
    }
}
