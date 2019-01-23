package com.prestashop.utilities;

import java.util.concurrent.TimeUnit;

public class BrowserUtilities {


    public static void open() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

    }


}
