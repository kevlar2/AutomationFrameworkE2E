package com.rockSolicitors.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BookAnAppointment;

public class BookAnAppointmentTest extends BaseTest {

    private BookAnAppointment bookAnAppointment;

    // Initialising log4j object to create logs
    private static Logger log = LogManager.getLogger(BookAnAppointmentTest.class.getName());

    @Test
    public void validateBookAnAppointmentPageUrl() throws InterruptedException {

        log.info("Validating book an appointment page url");

        bookAnAppointment =new BookAnAppointment(driver);

        // 01. Get book an appointment page
        bookAnAppointment.getBookAnAppointmentPage();
        Thread.sleep(2500);

        // 02. Validate url
        String actual  = bookAnAppointment.getCurrentUrl();
        String expected ="https://rocksolicitors.leapweb.co.uk/book-an-appointment/";
        Assert.assertEquals(actual,expected,"The actual url is different from the expected url");

        log.info("Validate book an appointment Test Completed successfully");
    }

}
