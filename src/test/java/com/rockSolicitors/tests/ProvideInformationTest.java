package com.rockSolicitors.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ProvideInformation;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ProvideInformationTest extends BaseTest {

    private ProvideInformation provideInformation;

    // Initialising log4j object to create logs
    private static Logger log = LogManager.getLogger(ProvideInformationTest.class.getName());

    @Test
    public void validateOnboardFormsUrl() throws InterruptedException {

        log.info("Validating onboard forms");

        provideInformation = new ProvideInformation(driver);

        // 01. Get provide Information screen
        provideInformation.getProvideInformationPage();
        Thread.sleep(2500);

        // 02. Validate onboard forms
        List<WebElement> getForms = provideInformation.navigatesToForms();
        int count=0;
        for (int i = 0; i < getForms.size(); i++) {

            getForms.get(i).sendKeys(Keys.CONTROL,Keys.ENTER);
            Thread.sleep(2500);
            Set<String> windows = driver.getWindowHandles();
            Iterator<String> checkWindows = windows.iterator();
            String parentWindow= checkWindows.next();
            while(checkWindows.hasNext()){
                driver.switchTo().window(checkWindows.next());
                Assert.assertTrue(provideInformation.getOnboardUrls()
                        .contains("https://forms.lawconnect.co.uk/onboard?setupId="),
                        "Expected url to contain -> https://forms.lawconnect.co.uk/onboard?setupId="
                                + " but got -> "
                                + provideInformation.getOnboardUrls());
                driver.close();
            }
            Thread.sleep(2500);
            driver.switchTo().window(parentWindow);
            count++;
            if(count>=11){
                break;
            }
        }


        log.info("Onboard forms validation completed successfully");
    }


}
