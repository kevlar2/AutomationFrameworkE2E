package com.rockSolicitors.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MakeAPayment;

public class MakePaymentTest extends BaseTest {

    private MakeAPayment makeAPayment;

    // Initialising log4j object to create logs
    private static Logger log = LogManager.getLogger(MakePaymentTest.class.getName());

    @Test()
    public void validatePageUrl(){

        log.info("Validating make a payment page url");

        makeAPayment =new MakeAPayment(driver);

        // 01. Get make a payment page
        makeAPayment.getMakePaymentPage();

        // 02. Validate url
        Assert.assertEquals(makeAPayment.validateUrl(), "https://rocksolicitors.leapweb.co.uk/paynow/");
    }

    @Test(priority = 1)
    public void validatePageTitle(){
        log.info("Validating make a payment page title");
        // 03. Validate page title
        // Assert.assertEquals(map.getPageTitle(), "Pay Your Bill");
    }

    @Test(priority = 2)
    public void validateReferenceNumberField(){
        log.info("Validating Reference number field");

        // 04. Enter Reference number
        //map.setYourReferenceNumber().sendKeys("Tester001");
    }

}
