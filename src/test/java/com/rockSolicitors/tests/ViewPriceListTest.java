package com.rockSolicitors.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ViewPriceList;

import java.util.List;

public class ViewPriceListTest extends BaseTest {

    private ViewPriceList viewPriceList;

    // Initialising log4j object to create logs
    private static Logger log = LogManager.getLogger(ViewPriceListTest.class.getName());


    @Test()
    public void validatePriceListPage() throws InterruptedException {
        log.info("Starting View Price List Test");

        // Create a view price list object
        viewPriceList = new ViewPriceList(driver);

        // 01. Navigate to price list page
        viewPriceList.getViewPriceListPage();
        Thread.sleep(2500);

        log.info("Validating Price List url");

        Assert.assertEquals(viewPriceList.validateWebpageLink(),
                "https://rocksolicitors.leapweb.co.uk/view-price-list/", "" +
                        "The price list url expected is different from the actual url");

    }

    @Test(priority = 1)
    public void validateWillsPowerOfAttorneyWillsPage(){

        log.info("Validating wills power of attorney wills page");

        // 02. Navigate to wills power of attorney wills page
        viewPriceList.getWillsPowersOfAttorneyWillsPage();
        Assert.assertEquals(viewPriceList.validateWebpageLink(), "https://rocksolicitors.leapweb.co.uk/services/wills-powers-of-attorney-wills/");

        // A. Validate wills services list table
        List<WebElement> willsServicesData = viewPriceList.validateServicesTableData();
        Assert.assertEquals(willsServicesData.get(0).getText(), "Will Drafting");
        Assert.assertEquals(willsServicesData.get(1).getText(), "£250.00");

        // B. Validate wills key stages table
        viewPriceList.getKeystagesMenu();
        List<WebElement> willsKeyStagesData = viewPriceList.validateKeyStagesTableData();
        Assert.assertEquals(willsKeyStagesData.get(0).getText(), "Draft copy will be available within a week");
        Assert.assertEquals(willsKeyStagesData.get(1).getText(), "1 – 2 week(s)");
        Assert.assertEquals(willsKeyStagesData.get(2).getText(), "Final copy within 2 weeks");
        Assert.assertEquals(willsKeyStagesData.get(3).getText(), "1 – 2 week(s)");

        // C. Validate will professionals table
        viewPriceList.getProfessionalsMenu();
        List<WebElement> willQualificationData = viewPriceList.validateProfessionalQualificationTable();
        Assert.assertEquals(willQualificationData.get(0).getText(), "LLB Hons, BL");
        Assert.assertEquals(willQualificationData.get(1).getText(), "University of Benin, College of Law");

        List<WebElement> willExperienceData = viewPriceList.validateExperienceTableData();
        Assert.assertEquals(willExperienceData.get(0).getText(), "Wills");
        Assert.assertEquals(willExperienceData.get(1).getText(), "17 year(s)");
        Assert.assertEquals(willExperienceData.get(2).getText(), "100%");

        driver.navigate().back();
    }

    @Test(priority = 2)
    public void validatePurchaseOfBusinessAndFranchise(){

        log.info("Validating purchase of business and franchise page (Business & Commercial)");

        // (Business & Commercial) - Price List

        // Navigate to Purchase of Business & Franchise (Business & Commercial) - Price List
        viewPriceList.getBusinessAndFranchisePage();
        Assert.assertEquals(viewPriceList.validateWebpageLink(), "https://rocksolicitors.leapweb.co.uk/services/business-commercial-purchase-of-business-franchise/");

        // A. Validate Purchase of Business & Franchise service table
        List<WebElement> bAndFServiceData = viewPriceList.validateServicesTableData();
        Assert.assertEquals(bAndFServiceData.get(0).getText(), "Purchase of Business up to £149,000.00");
        Assert.assertEquals(bAndFServiceData.get(1).getText(), "£1,599.00");

        // B. Validate Purchase of Business & Franchise key stages table
        viewPriceList.getKeystagesMenu();
        List<WebElement>  bAndFKeyStagesData = viewPriceList.validateKeyStagesTableData();
        Assert.assertEquals(bAndFKeyStagesData.get(0).getText(), "Exchange of Contract");
        Assert.assertEquals(bAndFKeyStagesData.get(1).getText(), "4 – 8 week(s)");

        // C. Validate Purchase of Business & Franchise professionals and qualifications table
        viewPriceList.getProfessionalsMenu();
        List<WebElement> bAndFQualificationData = viewPriceList.validateProfessionalQualificationTable();
        Assert.assertEquals(bAndFQualificationData.get(0).getText(), "LLB (Hons), BL"); // Potential Bug
        Assert.assertEquals(bAndFQualificationData.get(1).getText(), "University Of Benin, College of Law"); // Potential bug

        List<WebElement> bAndFExperienceData = viewPriceList.validateExperienceTableData();
        Assert.assertEquals(bAndFExperienceData.get(0).getText(), "Property Law");
        Assert.assertEquals(bAndFExperienceData.get(1).getText(), "17 year(s)");
        Assert.assertEquals(bAndFExperienceData.get(2).getText(), "100%");

        driver.navigate().back();

    }

    @Test(priority = 3)
    public void validateConveyancingAndPropertyPage(){

        log.info("Validating  conveyancing & property page");

        // 03. Navigate to Conveyancing & Property - Price List
        viewPriceList.getConveyancingPropertyPage();
        Assert.assertEquals(viewPriceList.validateWebpageLink(), "https://rocksolicitors.leapweb.co.uk/services/conveyancing-property-buy-to-let/"); // Potential bug

        // A. Validate conveyancing & Property service table
        List<WebElement> conveyancingAndPropertyServiceData = viewPriceList.validateServicesTableData();
        //Assert.assertEquals(conveyancingAndPropertyServiceData.get(0).getText(), "Buy to Let Purchase for Properties up to £250,000.00 Fixed fee does not include standard Searches and any disbursements.");// Needs looking at!!
        Assert.assertEquals(conveyancingAndPropertyServiceData.get(1).getText(), "£999.00");

        // B. Validate conveyancing & Property key stages table
        viewPriceList.getKeystagesMenu();
        List<WebElement>  conveyancingAndPropertyKeyStagesData = viewPriceList.validateKeyStagesTableData();
        Assert.assertEquals(conveyancingAndPropertyKeyStagesData.get(0).getText(), "Exchange of Contract");
        Assert.assertEquals(conveyancingAndPropertyKeyStagesData.get(1).getText(), "4 – 6 week(s)");

        // C. Validate conveyancing & Property professionals and qualifications table
        viewPriceList.getProfessionalsMenu();
        List<WebElement>  conveyancingAndPropertyQualificationData = viewPriceList.validateProfessionalQualificationTable();
        Assert.assertEquals( conveyancingAndPropertyQualificationData.get(0).getText(), "LLB(Hons)"); // Potential Bug
        Assert.assertEquals( conveyancingAndPropertyQualificationData.get(1).getText(), "University of Benin"); // Potential bug
        Assert.assertEquals( conveyancingAndPropertyQualificationData.get(2).getText(), "QLTT"); // Potential Bug
        Assert.assertEquals( conveyancingAndPropertyQualificationData.get(3).getText(), "College Of Law"); // Potential bug
        Assert.assertEquals( conveyancingAndPropertyQualificationData.get(4).getText(), "QLTT"); // Potential Bug
        Assert.assertEquals( conveyancingAndPropertyQualificationData.get(5).getText(), "BPP Law School"); // Potential bug

        List<WebElement> conveyancingAndPropertyExperienceData = viewPriceList.validateExperienceTableData();
        Assert.assertEquals(conveyancingAndPropertyExperienceData.get(0).getText(), "Property Law");
        Assert.assertEquals(conveyancingAndPropertyExperienceData.get(1).getText(), "17 year(s)");
        Assert.assertEquals(conveyancingAndPropertyExperienceData.get(2).getText(), "100%");

        log.info("View Price List Test Completed successfully");
    }

}
