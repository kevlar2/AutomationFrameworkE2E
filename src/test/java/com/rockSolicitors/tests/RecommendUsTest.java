package com.rockSolicitors.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RecommendUs;
import resources.ExcelDataDrivenTest.ExcelDataDrivenTest;

import java.io.IOException;
import java.util.ArrayList;

public class RecommendUsTest extends BaseTest{

    private RecommendUs recommendUs;
    public ExcelDataDrivenTest d;

    // Creating variable for excel data to be used within the script
    ArrayList<String> yourFriendDetails;
    ArrayList<String> yourDetails;
    ArrayList<String> warning1;
    ArrayList<String> warning2;
    ArrayList<String> friendsFirstNameWarning;
    ArrayList<String> friendsLastNameWarning;
    ArrayList<String> urFirstNameWarning;
    ArrayList<String> urLastNameWarning;
    ArrayList<String> validateWebsite;


    // Initialising log4j object to create logs
    private static Logger log = LogManager.getLogger(RecommendUsTest.class.getName());

    @Test(priority = 0)
    public void validateButtonsOnReferToAFriendPage() throws IOException, InterruptedException {
        recommendUs = new RecommendUs(driver);
        d = new ExcelDataDrivenTest();

        // initialising array with data from excel sheet and store in arrays
        yourFriendDetails =d.getData("recommendUsTest");
        yourDetails =d.getData("recommendUsTest1");
        warning1 =d.getData("Warning 1");
        warning2 =d.getData("Warning 2");
        friendsFirstNameWarning =d.getData("FriendsFirstNameWarning");
        friendsLastNameWarning =d.getData("FriendsLastNameWarning");
        urFirstNameWarning =d.getData("YourFirstNameWarning");
        urLastNameWarning =d.getData("YourLastNameWarning");
        validateWebsite =d.getData("Validate Website");

        log.info("Validating buttons within the refer to a friend screen");

        // 01. Validate refer to a friend button
        recommendUs.getReferOurFirm();
        Thread.sleep(2000);

        // 02. Validate close [X] button
        recommendUs.closeXForm();
        driver.navigate().refresh();

        // 03. Validate entering details then closing form using the close [X] button
        recommendUs.getReferOurFirm();
        Thread.sleep(1000);
        recommendUs.enterYourFriendDetails(yourFriendDetails.get(1), yourFriendDetails.get(2), yourFriendDetails.get(3));
        recommendUs.enterYourDetails(yourDetails.get(1), yourDetails.get(2), yourDetails.get(3));
        recommendUs.clearFields("yourFriendFirstName");
        recommendUs.closeXForm();
        driver.navigate().refresh();

        //04. Validate entering details then closing form using the close cancel button
        recommendUs.getReferOurFirm();
        Thread.sleep(1000);
        recommendUs.enterYourFriendDetails(yourFriendDetails.get(1),yourFriendDetails.get(2),yourFriendDetails.get(3));
        recommendUs.enterYourDetails(yourDetails.get(1), yourDetails.get(2),yourDetails.get(3));
        recommendUs.cancelForm();
        driver.navigate().refresh();

    }

    @Test(priority = 1)
    public void validateMessagePreview() throws InterruptedException {

        log.info("Validating message preview");

        //05. Validate message preview. check it contains 1. Your Friends Name, 2. R.O.C.K Web url 2. Your Name
        recommendUs.getReferOurFirm();
        Thread.sleep(1000);
        recommendUs.enterYourFriendDetails(yourFriendDetails.get(1),yourFriendDetails.get(2),yourFriendDetails.get(3));
        recommendUs.enterYourDetails(yourDetails.get(1), yourDetails.get(2),yourDetails.get(3));
        Assert.assertTrue(recommendUs.getMessagePreviewText().contains(yourFriendDetails.get(1) + " " +yourFriendDetails.get(2)));
        Assert.assertTrue(recommendUs.getMessagePreviewText().contains(validateWebsite.get(1)));
        Assert.assertTrue(recommendUs.getMessagePreviewText().contains(yourDetails.get(1) + " " + yourDetails.get(2)));
        recommendUs.cancelFormAndRefresh();

    }

    @Test(priority = 2)
    public void validateYourFriendDetailsWarnings() throws InterruptedException {

        log.info("Validating your friends details warnings");

        // 06. Validate your friend details warning messages
        //---------------------------------------------//
        // A. Enter only names and click on send then validate email warning
        // Expected warning 1 -> "Please enter a valid email address for your friend."
        // Expected warning 2 -> "Please enter a valid email address for yourself."
        recommendUs.getReferOurFirm();
        Thread.sleep(1000);
        recommendUs.enterYourFriendDetails(yourFriendDetails.get(1),yourFriendDetails.get(2),yourFriendDetails.get(3));
        recommendUs.clearFields("yourFriendEmailAddress");
        recommendUs.enterYourDetails(yourDetails.get(1), yourDetails.get(2),yourDetails.get(3));
        recommendUs.sendForm();

        // Validate Expected Warning 1
        String expectedError1 = warning1.get(1);
        Assert.assertEquals(recommendUs.getErrorMessage(), expectedError1);
        recommendUs.cancelFormAndRefresh();

        recommendUs.getReferOurFirm();
        Thread.sleep(1000);
        recommendUs.enterYourFriendDetails(yourFriendDetails.get(1),yourFriendDetails.get(2),yourFriendDetails.get(3));
        recommendUs.enterYourDetails(yourDetails.get(1), yourDetails.get(2),yourDetails.get(3));
        recommendUs.clearFields("yourEmailAddress");
        recommendUs.sendForm();

        // Validate Expected Warning 2
        String expectedError2 = warning2.get(1);
        Assert.assertEquals(recommendUs.getErrorMessage(), expectedError2);
        recommendUs.cancelFormAndRefresh();

        // B. Validate Name Warnings
        // Your Friends first name warning
        // Expected friendFirstNameWarning = "Please provide your friends first name."
        recommendUs.getReferOurFirm();
        Thread.sleep(1000);
        recommendUs.enterYourFriendDetails(yourFriendDetails.get(1),yourFriendDetails.get(2),yourFriendDetails.get(3));
        recommendUs.clearFields("yourFriendFirstName");
        recommendUs.enterYourDetails(yourDetails.get(1), yourDetails.get(2),yourDetails.get(3));
        recommendUs.sendForm();

        // Validate your friends first name warning
        String friendFirstNameWarning = friendsFirstNameWarning.get(1);
        Assert.assertEquals(recommendUs.getErrorMessage(), friendFirstNameWarning);
        recommendUs.cancelFormAndRefresh();

        // Your Friends last name warning
        // Expected friendLastNameWarning = "Please provide your friends last name."
        recommendUs.getReferOurFirm();
        Thread.sleep(1000);
        recommendUs.enterYourFriendDetails(yourFriendDetails.get(1),yourFriendDetails.get(2),yourFriendDetails.get(3));
        recommendUs.clearFields("yourFriendLastName");
        recommendUs.enterYourDetails(yourDetails.get(1), yourDetails.get(2),yourDetails.get(3));
        recommendUs.sendForm();

        // Validate your friend last name warning
        String friendLastNameWarning = friendsLastNameWarning.get(1);
        Assert.assertEquals(recommendUs.getErrorMessage(), friendLastNameWarning);
        recommendUs.cancelFormAndRefresh();

    }

    @Test(priority = 3)
    public void validateYourDetailsWarnings() throws InterruptedException {

        log.info("Validating your details warning");

        // Your first name warning
        // Expected yourFirstNameWarning = "Your first name is not valid."
        recommendUs.getReferOurFirm();
        Thread.sleep(1000);
        recommendUs.enterYourFriendDetails(yourFriendDetails.get(1),yourFriendDetails.get(2),yourFriendDetails.get(3));
        recommendUs.enterYourDetails(yourDetails.get(1), yourDetails.get(2),yourDetails.get(3));
        recommendUs.clearFields("yourFirstName");
        recommendUs.sendForm();

        // Validate your first name warning
        String yourFirstNameWarning = urFirstNameWarning.get(1);
        Assert.assertEquals(recommendUs.getErrorMessage(), yourFirstNameWarning);
        recommendUs.cancelFormAndRefresh();

        // Your last name warning
        // Expected yourLastNameWarning = "Your last name is not valid."
        recommendUs.getReferOurFirm();
        Thread.sleep(1000);
        recommendUs.enterYourFriendDetails(yourFriendDetails.get(1),yourFriendDetails.get(2),yourFriendDetails.get(3));
        recommendUs.enterYourDetails(yourDetails.get(1), yourDetails.get(2),yourDetails.get(3));
        recommendUs.clearFields("yourLastName");
        recommendUs.sendForm();

        // Validate your last name warning
        String yourLastNameWarning = urLastNameWarning.get(1);
        Assert.assertEquals(recommendUs.getErrorMessage(), yourLastNameWarning);
        recommendUs.cancelFormAndRefresh();

        log.info("Recommend Us Test Completed successfully");
    }



}
