package pageObjects;

import BasePage.BaseActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReadYourDocuments extends BaseActions {

    // Navigate to Read your document page
    private By getReadYourDocumentScreen = By.cssSelector("div[class='genesis-nav-menu'] ul li:nth-child(5)");



    // Driver Constructor for this page object
    public ReadYourDocuments(WebDriver driver) {
        super(driver);
    }

    public ReadYourDocuments(){
        super();
    }

    // Navigate to Read your document page
    public ReadYourDocuments getReadYourDocumentPage(){
        click(getReadYourDocumentScreen);
        return new ReadYourDocuments();
    }

    // Get page URL
    public String getReadYourDocumentsPageUrl(){
        return getPageUrl();
    }


}
