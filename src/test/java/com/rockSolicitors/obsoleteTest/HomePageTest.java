package com.rockSolicitors.obsoleteTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.*;
import resources.ExcelDataDrivenTest.ExcelDataDrivenTest;
import resources.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HomePageTest extends base {

    public WebDriver driver;

    // Initialising log4j object to create logs
    private static Logger log = LogManager.getLogger(base.class.getName());

    @BeforeTest
    public void startUp() throws IOException {
        driver = initialiseDriver();
        log.info("Driver is initialised");
        driver.get(prop.getProperty("url"));
        log.info("Navigating to " + prop.getProperty("url"));

    }

    @Test()
    public void recommendUsTest() throws InterruptedException, IOException {
        log.info("Starting Recommend Us Test");

        RecommendUs recommendUs = new RecommendUs(driver);
        ExcelDataDrivenTest d = new ExcelDataDrivenTest();
        ArrayList<String> yourFriendDetails =d.getData("recommendUsTest");
        ArrayList<String> yourDetails =d.getData("recommendUsTest1");
        ArrayList<String> warning1 =d.getData("Warning 1");
        ArrayList<String> warning2 =d.getData("Warning 2");
        ArrayList<String> friendsFirstNameWarning =d.getData("FriendsFirstNameWarning");
        ArrayList<String> friendsLastNameWarning =d.getData("FriendsLastNameWarning");
        ArrayList<String> urFirstNameWarning =d.getData("YourFirstNameWarning");
        ArrayList<String> urLastNameWarning =d.getData("YourLastNameWarning");
        ArrayList<String> validateWebsite =d.getData("Validate Website");


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

        //05. Validate message preview. check it contains 1. Your Friends Name, 2. R.O.C.K Web url 2. Your Name
        recommendUs.getReferOurFirm();
        Thread.sleep(1000);
        recommendUs.enterYourFriendDetails(yourFriendDetails.get(1),yourFriendDetails.get(2),yourFriendDetails.get(3));
        recommendUs.enterYourDetails(yourDetails.get(1), yourDetails.get(2),yourDetails.get(3));
        Assert.assertTrue(recommendUs.getMessagePreviewText().contains(yourFriendDetails.get(1) + " " +yourFriendDetails.get(2)));
        Assert.assertTrue(recommendUs.getMessagePreviewText().contains(validateWebsite.get(1)));
        Assert.assertTrue(recommendUs.getMessagePreviewText().contains(yourDetails.get(1) + " " + yourDetails.get(2)));
        recommendUs.cancelFormAndRefresh();

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

    @Test(priority = 1)
    public void makeAnEnquiryTest() throws InterruptedException {
        log.info("Starting Make An Enquiry Test");

        MakeAnEnquiry mae =new MakeAnEnquiry(driver);

        // 01. Get Make an enquiry screen
        mae.getEnquiryScreen();
        Thread.sleep(2000);

        // 02. Complete make an enquiry form
        mae.enterPersonDetails("James","Roghan","James.Roghan@tflrail.com", "07825638164");
        String message = "Hi\n" + "This is just a test to see if this feature is working as expected.\n" + "Thanks";
        mae.enterMessage(message);
        mae.setHowDidYouHearAboutUs("Google");
        mae.jsExecutor("document.getElementById('choice_7_6_1').click()"); // Clicking checkbox consent []

        // 03. Validate warning message

        // A. Validate First and Last name warning
        driver.navigate().refresh();
        mae.getEnquiryScreen();
        Thread.sleep(2000);
        mae.enterPersonDetails("James","Roghan","James.Roghan@tflrail.com", "07825638164");
        mae.clearField("fName");
        //mae.clearField("lName");
        jsExecutor("document.getElementById('gform_submit_button_7').click()"); // Clicking submit button
        Assert.assertEquals(mae.getFameAndLameWarn(),"This field is required. Please enter the first and last name.");

        // B. Validate wrong email warning
        driver.navigate().refresh();
        mae.getEnquiryScreen();
        Thread.sleep(2000);
        mae.enterPersonDetails("James","Roghan","James.Roghan", "07825638164");
        jsExecutor("document.getElementById('gform_submit_button_7').click()"); // Clicking submit button
        Assert.assertEquals(mae.getEmailWarn(),"Please enter a valid email address.");

        // 03. Validate Address Details
        List<WebElement> addDetails = mae.getAddressDetails();
        Assert.assertEquals(addDetails.get(0).getText(), "Address Details");
        Assert.assertEquals(addDetails.get(1).getText(), "151 Balham Hill");
        Assert.assertEquals(addDetails.get(2).getText(), "London");
        Assert.assertEquals(addDetails.get(3).getText(), "England SW12 9DJ");

        // 04. Validate Contact Details
        List<WebElement> contactDetails = mae.getContactDetails();
        Assert.assertEquals(contactDetails.get(0).getText(), "Contact Details");
        Assert.assertEquals(contactDetails.get(1).getText(), "Tel: 020 8673 5819");
        Assert.assertEquals(contactDetails.get(2).getText(), "enquiries@rocksolicitors.com");
        Assert.assertEquals(contactDetails.get(3).getText(), "https://www.rocksolicitors.com");


        log.info("Make An Enquiry Test Completed successfully");

    }

    @Test(priority = 2)
    public void viewPriceListTest() throws InterruptedException {
        log.info("Starting View Price List Test");

        // Create a view price list object
        ViewPriceList vpl = new ViewPriceList(driver);

        // 01. Navigate to price list page
        vpl.getViewPriceListPage();
        Thread.sleep(2500);
        Assert.assertEquals(vpl.validateWebpageLink(), "https://rocksolicitors.leapweb.co.uk/view-price-list/");

        // 02. Navigate to wills power of attorney wills page
        vpl.getWillsPowersOfAttorneyWillsPage();
        Assert.assertEquals(vpl.validateWebpageLink(), "https://rocksolicitors.leapweb.co.uk/services/wills-powers-of-attorney-wills/");

        // A. Validate wills services list table
        List<WebElement> willsServicesData = vpl.validateServicesTableData();
        Assert.assertEquals(willsServicesData.get(0).getText(), "Will Drafting");
        Assert.assertEquals(willsServicesData.get(1).getText(), "£250.00");

        // B. Validate wills key stages table
        vpl.getKeystagesMenu();
        List<WebElement> willsKeyStagesData = vpl.validateKeyStagesTableData();
        Assert.assertEquals(willsKeyStagesData.get(0).getText(), "Draft copy will be available within a week");
        Assert.assertEquals(willsKeyStagesData.get(1).getText(), "1 – 2 week(s)");
        Assert.assertEquals(willsKeyStagesData.get(2).getText(), "Final copy within 2 weeks");
        Assert.assertEquals(willsKeyStagesData.get(3).getText(), "1 – 2 week(s)");

        // C. Validate will professionals table
        vpl.getProfessionalsMenu();
        List<WebElement> willQualificationData = vpl.validateProfessionalQualificationTable();
        Assert.assertEquals(willQualificationData.get(0).getText(), "LLB Hons, BL");
        Assert.assertEquals(willQualificationData.get(1).getText(), "University of Benin, College of Law");

        List<WebElement> willExperienceData = vpl.validateExperienceTableData();
        Assert.assertEquals(willExperienceData.get(0).getText(), "Wills");
        Assert.assertEquals(willExperienceData.get(1).getText(), "17 year(s)");
        Assert.assertEquals(willExperienceData.get(2).getText(), "100%");

        driver.navigate().back();

        // 03. Navigate to Purchase of Business & Franchise (Business & Commercial) - Price List
        vpl.getBusinessAndFranchisePage();
        Assert.assertEquals(vpl.validateWebpageLink(), "https://rocksolicitors.leapweb.co.uk/services/business-commercial-purchase-of-business-franchise/");

        // A. Validate Purchase of Business & Franchise service table
        List<WebElement> bAndFServiceData = vpl.validateServicesTableData();
        Assert.assertEquals(bAndFServiceData.get(0).getText(), "Purchase of Business up to £149,000.00");
        Assert.assertEquals(bAndFServiceData.get(1).getText(), "£1,599.00");

        // B. Validate Purchase of Business & Franchise key stages table
        vpl.getKeystagesMenu();
        List<WebElement>  bAndFKeyStagesData = vpl.validateKeyStagesTableData();
        Assert.assertEquals(bAndFKeyStagesData.get(0).getText(), "Exchange of Contract");
        Assert.assertEquals(bAndFKeyStagesData.get(1).getText(), "4 – 8 week(s)");

        // C. Validate Purchase of Business & Franchise professionals and qualifications table
        vpl.getProfessionalsMenu();
        List<WebElement> bAndFQualificationData = vpl.validateProfessionalQualificationTable();
        Assert.assertEquals(bAndFQualificationData.get(0).getText(), "LLB (Hons), BL"); // Potential Bug
        Assert.assertEquals(bAndFQualificationData.get(1).getText(), "University Of Benin, College of Law"); // Potential bug

        List<WebElement> bAndFExperienceData = vpl.validateExperienceTableData();
        Assert.assertEquals(bAndFExperienceData.get(0).getText(), "Property Law");
        Assert.assertEquals(bAndFExperienceData.get(1).getText(), "17 year(s)");
        Assert.assertEquals(bAndFExperienceData.get(2).getText(), "100%");

        driver.navigate().back();

        // 03. Navigate to Conveyancing & Property - Price List
        vpl.getConveyancingPropertyPage();
        Assert.assertEquals(vpl.validateWebpageLink(), "https://rocksolicitors.leapweb.co.uk/services/conveyancing-property-buy-to-let/"); // Potential bug

        // A. Validate conveyancing & Property service table
        List<WebElement> conveyancingAndPropertyServiceData = vpl.validateServicesTableData();
        //Assert.assertEquals(conveyancingAndPropertyServiceData.get(0).getText(), "Buy to Let Purchase for Properties up to £250,000.00 Fixed fee does not include standard Searches and any disbursements.");// Needs looking at!!
        Assert.assertEquals(conveyancingAndPropertyServiceData.get(1).getText(), "£999.00");

        // B. Validate conveyancing & Property key stages table
        vpl.getKeystagesMenu();
        List<WebElement>  conveyancingAndPropertyKeyStagesData = vpl.validateKeyStagesTableData();
        Assert.assertEquals(conveyancingAndPropertyKeyStagesData.get(0).getText(), "Exchange of Contract");
        Assert.assertEquals(conveyancingAndPropertyKeyStagesData.get(1).getText(), "4 – 6 week(s)");

        // C. Validate conveyancing & Property professionals and qualifications table
        vpl.getProfessionalsMenu();
        List<WebElement>  conveyancingAndPropertyQualificationData = vpl.validateProfessionalQualificationTable();
        Assert.assertEquals( conveyancingAndPropertyQualificationData.get(0).getText(), "LLB(Hons)"); // Potential Bug
        Assert.assertEquals( conveyancingAndPropertyQualificationData.get(1).getText(), "University of Benin"); // Potential bug
        Assert.assertEquals( conveyancingAndPropertyQualificationData.get(2).getText(), "QLTT"); // Potential Bug
        Assert.assertEquals( conveyancingAndPropertyQualificationData.get(3).getText(), "College Of Law"); // Potential bug
        Assert.assertEquals( conveyancingAndPropertyQualificationData.get(4).getText(), "QLTT"); // Potential Bug
        Assert.assertEquals( conveyancingAndPropertyQualificationData.get(5).getText(), "BPP Law School"); // Potential bug

        List<WebElement> conveyancingAndPropertyExperienceData = vpl.validateExperienceTableData();
        Assert.assertEquals(conveyancingAndPropertyExperienceData.get(0).getText(), "Property Law");
        Assert.assertEquals(conveyancingAndPropertyExperienceData.get(1).getText(), "17 year(s)");
        Assert.assertEquals(conveyancingAndPropertyExperienceData.get(2).getText(), "100%");

        log.info("View Price List Test Completed successfully");
    }

    @Test(priority = 3)
    public void provideInformationTest() throws InterruptedException {

        log.info("Starting provide Information Test");
        ProvideInformation pf = new ProvideInformation(driver);
        // 01. Get provide Information screen
        pf.getProvideInformationPage();
        Thread.sleep(2500);

        // 02. Validate onboard forms
        List<WebElement> getForms = pf.navigatesToForms();
        int count=0;
        for (int i = 0; i < getForms.size(); i++) {

                getForms.get(i).sendKeys(Keys.CONTROL,Keys.ENTER);
                Thread.sleep(2500);
                Set<String> windows = driver.getWindowHandles();
                Iterator<String> checkWindows = windows.iterator();
                String parentWindow= checkWindows.next();
                while(checkWindows.hasNext()){
                    driver.switchTo().window(checkWindows.next());
                    Assert.assertTrue(pf.getOnboardUrls().contains("https://forms.lawconnect.co.uk/onboard?setupId="));
                    driver.close();
                }
                Thread.sleep(2500);
                driver.switchTo().window(parentWindow);
                count++;
                if(count>=11){
                    break;
                }
        }


        log.info("Provide Information Test Completed successfully");

    }

    @Test(priority = 4)
    public void makePaymentTest(){
        log.info("Starting make payment Test");

        MakeAPayment map =new MakeAPayment(driver);

        // 01. Get make a payment page
        map.getMakePaymentPage();

        // 02. Validate url
        Assert.assertEquals(map.validateUrl(), "https://rocksolicitors.leapweb.co.uk/paynow/");

        // 03. Validate page title
        // Assert.assertEquals(map.getPageTitle(), "Pay Your Bill");

        // 04. Enter Reference number
        //map.setYourReferenceNumber().sendKeys("Tester001");


        log.info("Make payment Test Completed successfully");
    }

    @Test(priority = 5)
    public void readYourDocumentTest() throws InterruptedException {
        log.info("Starting read your document Test");

        ReadYourDocuments ryd =new ReadYourDocuments(driver);

        // 01. Get read your document page
        ryd.getReadYourDocumentPage();
        Thread.sleep(2500);

        // 02. Validate url
        String actual  = ryd.getReadYourDocumentsPageUrl();
        String expected ="https://rocksolicitors.leapweb.co.uk/read-your-documents/";
        Assert.assertEquals(actual,expected );



        log.info("Read your document Test Completed successfully");
    }

    @Test(priority = 6)
    public void bookAnAppointment() throws InterruptedException {
        log.info("Starting book an appointment Test");

        BookAnAppointment baa =new BookAnAppointment(driver);

        // 01. Get book an appointment page
        baa.getBookAnAppointmentPage();
        Thread.sleep(2500);

        // 02. Validate url
        String actual  = baa.getCurrentUrl();
        String expected ="https://rocksolicitors.leapweb.co.uk/book-an-appointment/";
        Assert.assertEquals(actual,expected );

        log.info("Book an appointment Test Completed successfully");
    }

    @AfterTest
    public void tearDown(){
        log.info("Closing Browser");
        driver.quit();
        driver=null;
    }


}
