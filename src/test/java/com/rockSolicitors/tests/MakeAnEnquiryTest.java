package com.rockSolicitors.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MakeAnEnquiry;

import java.util.List;

public class MakeAnEnquiryTest extends BaseTest {

    private MakeAnEnquiry makeAnEnquiry;

    // Initialising log4j object to create logs
    private static Logger log = LogManager.getLogger(MakeAnEnquiryTest.class.getName());


    @Test()
    public void validateMakeEnquiryForm() throws InterruptedException {

        log.info("Starting Make An Enquiry Test");

        makeAnEnquiry =new MakeAnEnquiry(driver);

        log.info("Validating Make An Enquiry form");
        // 01. Get Make an enquiry screen
        makeAnEnquiry.getEnquiryScreen();
        Thread.sleep(2000);

        // 02. Complete make an enquiry form
        makeAnEnquiry.enterPersonDetails("James","Roghan","James.Roghan@tflrail.com", "07825638164");
        String message = "Hi\n" + "This is just a test to see if this feature is working as expected.\n" + "Thanks";
        makeAnEnquiry.enterMessage(message);
        makeAnEnquiry.setHowDidYouHearAboutUs("Google");
        makeAnEnquiry.jsExecutor("document.getElementById('choice_7_6_1').click()"); // Clicking checkbox consent []

    }

    @Test(priority = 1)
    public void validateMakeEnquiryFormFields() throws InterruptedException {

        log.info("Validating Make An Enquiry form fields");

        // A. Validate First and Last name field
        driver.navigate().refresh();
        makeAnEnquiry.getEnquiryScreen();
        Thread.sleep(2000);
        makeAnEnquiry.enterPersonDetails("James","Roghan","James.Roghan@tflrail.com",
                "07825638164");
        makeAnEnquiry.clearField("fName");
        //mae.clearField("lName");
        jsExecutor("document.getElementById('gform_submit_button_7').click()"); // Clicking submit button
        Assert.assertEquals(makeAnEnquiry.getFameAndLameWarn(),
                "This field is required. Please enter the first and last name.",
                "First and Last name field actual error is different from expected");

        // B. Validate wrong email warning
        driver.navigate().refresh();
        makeAnEnquiry.getEnquiryScreen();
        Thread.sleep(2000);
        makeAnEnquiry.enterPersonDetails("James","Roghan","James.Roghan", "07825638164");
        jsExecutor("document.getElementById('gform_submit_button_7').click()"); // Clicking submit button
        Assert.assertEquals(makeAnEnquiry.getEmailWarn(),"Please enter a valid email address.",
                "Email address field actual error is different from expected");

        // 03. Validate Address Details
        List<WebElement> addDetails = makeAnEnquiry.getAddressDetails();
        Assert.assertEquals(addDetails.get(0).getText(), "Address Details");
        Assert.assertEquals(addDetails.get(1).getText(), "151 Balham Hill");
        Assert.assertEquals(addDetails.get(2).getText(), "London");
        Assert.assertEquals(addDetails.get(3).getText(), "England SW12 9DJ");

        // 04. Validate Contact Details
        List<WebElement> contactDetails = makeAnEnquiry.getContactDetails();
        Assert.assertEquals(contactDetails.get(0).getText(), "Contact Details");
        Assert.assertEquals(contactDetails.get(1).getText(), "Tel: 020 8673 5819");
        Assert.assertEquals(contactDetails.get(2).getText(), "enquiries@rocksolicitors.com");
        Assert.assertEquals(contactDetails.get(3).getText(), "https://www.rocksolicitors.com");


        log.info("Make An Enquiry Test Completed successfully");
    }


}
