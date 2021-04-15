package pageObjects;

import BasePage.BaseActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MakeAPayment extends BaseActions {

    // Navigate to Make A Payment page
    private By getMakeAPayment =By.cssSelector("div[class='genesis-nav-menu'] ul li:nth-child(4)");

    private By pageTitle =By.cssSelector(".checkout-heading");

    private  By youReferenceNumber =By.id("txtRefNum");

    private By nextButton =By.id("btnPayNow");

    private By errorMessage =By.id("errorMsg");

    private By whatIsARefNumber =By.linkText("What is a reference number");

    private By closeWindowsXButton =By.cssSelector("#btnRefNumCloseModalWdw > span");

    private By pageCloseButton =By.id("btnRefNumCloseModal");



    // Driver Constructor for this page object

    public MakeAPayment(WebDriver driver) {
        super(driver);
    }

    public MakeAPayment(){
        super();
    }

    // Navigate to Make A Payment page
    public MakeAPayment getMakePaymentPage(){
        click(getMakeAPayment);
        return new MakeAPayment();
    }

    // Validate url
    public String validateUrl(){
        return getPageUrl();
    }

    // Validate page title
    public String getPageTitle(){
        return getText(pageTitle);
    }

    // Enter Your Reference number
    public MakeAPayment setYourReferenceNumber(String referenceNumber){
        enter(youReferenceNumber, referenceNumber);
        return new MakeAPayment();
    }
}
