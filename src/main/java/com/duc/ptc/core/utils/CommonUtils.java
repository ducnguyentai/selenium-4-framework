package com.duc.ptc.core.utils;

import com.google.common.util.concurrent.Uninterruptibles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    public static void delay(int second) {
        Uninterruptibles.sleepUninterruptibly(second, TimeUnit.SECONDS);
    }

    public static Properties readPropertiesFileFromTestResourceFolder(String fileName) {
        Properties config = null;
        LOGGER.info("The '" + fileName + "' is loading...");
        try {
            InputStream conf = new FileInputStream(System.getProperty("user.dir").concat("/src/test/resources/").concat(fileName));
            config = new Properties();
            try {
                config.load(conf);
            } catch (IOException e) {
                throw new IllegalArgumentException("Unable to read the file '" + fileName + "' in 'resources' folder.", e);
            } finally {
                try {
                    conf.close();
                } catch (IOException e) {
                    throw new IllegalArgumentException("Unable to close the file '" + fileName + "' in 'resources' folder", e);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return config;
    }
}
