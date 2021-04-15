package com.rockSolicitors.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import resources.base;

import java.io.IOException;

public class BaseTest extends base {

    protected WebDriver driver;

    // Initialising log4j object to create logs
    private static Logger log = LogManager.getLogger(BaseTest.class.getName());

    @BeforeClass
    public void startUp() throws IOException {
        driver = initialiseDriver();
        log.info("Driver is initialised");
        driver.get(prop.getProperty("url"));
        log.info("Navigating to " + prop.getProperty("url"));

    }

    @AfterClass
    public void tearDown(){
        log.info("Closing Browser");
        driver.quit();
        driver=null;
    }

}
