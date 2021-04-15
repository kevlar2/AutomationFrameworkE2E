package pageObjects;

import BasePage.BaseActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProvideInformation extends BaseActions {

    // Navigate to Provide information page
    private By getProvideInformationScreen = By.cssSelector("div[class='genesis-nav-menu'] ul li:nth-child(3)");

    // Click on forms
    private By completeForms = By.cssSelector("a[class='portalapps-data-box']");



    // Driver Constructor for this page object
    public ProvideInformation(WebDriver driver) {
        super(driver);
    }

    public ProvideInformation(){
        super();
    }

    // Navigate to Provide information page
    public ProvideInformation getProvideInformationPage(){
        click(getProvideInformationScreen);
        return new ProvideInformation();
    }

    // Navigate to forms
    public List<WebElement> navigatesToForms(){
        return findElements(completeForms);
    }

    // Validate on-boarding url
    public String getOnboardUrls(){
        return getPageUrl();
    }



}
