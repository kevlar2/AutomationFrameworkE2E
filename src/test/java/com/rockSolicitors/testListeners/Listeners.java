package com.rockSolicitors.testListeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.rockSolicitors.tests.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.ExtentReporterNG;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    // accessing log4j logger
    private static Logger log = LogManager.getLogger(Listeners.class.getName());

    // Statically calling the extent report and assigning to a variable
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ExtentTest test;

    // Make your code thread safe when running in parallel mode
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();


    @Override
    public void onTestStart(ITestResult result) {
        log.info(result.getName() + " Started successfully");
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " Completed successfully");
        // When running single test at a time
        //test.log(Status.PASS, "Test Passed");

        // When running in parallel mode
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;

        String testMethodName = result.getMethod().getMethodName();
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            getScreenShotPath(testMethodName, driver);
            extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver), testMethodName);
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        log.error(testMethodName + " Failed. Screenshot was captured successfully. See reports folder");
        try {
            // logging Screenshot location
            log.info(getScreenShotPath(testMethodName, driver));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // When running single test at a time
        // test.fail(result.getThrowable());

        // When running in parallel mode
        extentTest.get().fail(result.getThrowable());
        extentTest.set(test.fail("Test failed"));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getMethod().getMethodName() + " Skipped successfully");
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        log.info(context.getName() + " Have finished testing successfully");
        extent.flush();

    }
}
