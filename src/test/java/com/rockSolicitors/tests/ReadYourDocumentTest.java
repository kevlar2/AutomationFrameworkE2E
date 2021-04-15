package com.rockSolicitors.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ReadYourDocuments;

public class ReadYourDocumentTest extends BaseTest {

    private ReadYourDocuments readYourDocuments;

    // Initialising log4j object to create logs
    private static Logger log = LogManager.getLogger(ReadYourDocumentTest.class.getName());

    @Test
    public void validateReadYourDocumentPageUrl() throws InterruptedException {

        log.info("Starting validate read your document url Test");

        readYourDocuments =new ReadYourDocuments(driver);

        // 01. Get read your document page
        readYourDocuments.getReadYourDocumentPage();
        Thread.sleep(2500);

        // 02. Validate url
        String actual  = readYourDocuments.getReadYourDocumentsPageUrl();
        String expected ="https://rocksolicitors.leapweb.co.uk/read-your-documents/";
        Assert.assertEquals(actual,expected,"The actual url is different from the expected url");



        log.info("Validate read your document url test completed successfully");

    }

}
