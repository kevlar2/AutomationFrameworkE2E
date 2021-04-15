package pageObjects;

import BasePage.BaseActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecommendUs extends BaseActions {

    private By referOurFirm = By.xpath("//div[@class='recommend-us']");

    // Your Friend field
    private By yourFriendFirstName =By.cssSelector("input[id='referral-name-first']");
    private By yourFriendLastName =By.cssSelector("input[id='referral-name-last']");
    private By yourFriendEmailAddress =By.xpath("//input[@id='referral-email']");

    // Your Name field
    private By yourFirstName =By.cssSelector("input[id='your-name-first']");
    private By yourLastName =By.cssSelector("input[id='your-name-last']");
    private By yourEmailAddress =By.xpath("//input[@id='your-email']");

    private By sendButton =By.cssSelector("div[class='jconfirm-buttons'] button[class='btn export_confirmation']");
    private By cancelButton =By.cssSelector("button[class='btn edit_cancel']");
    private By closeXButton = By.cssSelector(
            "div[class='jconfirm-box jconfirm-hilight-shake jconfirm-type-default jconfirm-type-animated'] div[class='jconfirm-closeIcon']");

    private By messagePreview = By.xpath("//div[@id='email-content']");

    // Warning locator
    private By errorMessage = By.cssSelector("div p[id='referral-warning']");


    // Driver Constructor for this page object
    public RecommendUs(WebDriver driver) {
        super(driver);
    }

    public RecommendUs(){
        super();
    }

    // Get recommendUs screen
    public RecommendUs getReferOurFirm(){
        click(referOurFirm);
        return new RecommendUs();
    }

    // Your friend fields
    public RecommendUs enterYourFriendDetails(String firstName, String lastName, String emailAddress){
        enter(yourFriendFirstName, firstName);
        enter(yourFriendLastName, lastName);
        enter(yourFriendEmailAddress, emailAddress);
        return new RecommendUs();
    }

    // Your fields
    public RecommendUs enterYourDetails(String firstName, String lastName, String emailAddress){
        enter(yourFirstName, firstName);
        enter(yourLastName, lastName);
        enter(yourEmailAddress, emailAddress);

        return new RecommendUs();
    }

    // use this method to clear fields. e.g.yourFriendFirstName
    public RecommendUs clearFields(String fieldToClear){
        switch (fieldToClear){
            case "yourFriendFirstName":
                clear(yourFriendFirstName);
                break;
            case "yourFriendLastName":
                clear(yourFriendLastName);
                break;
            case "yourFriendEmailAddress":
                clear(yourFriendEmailAddress);
                break;
            case "yourFirstName":
                clear(yourFirstName);
                break;
            case "yourLastName":
                clear(yourLastName);
                break;
            case "yourEmailAddress":
                clear(yourEmailAddress);
                break;
            default:
                throw new RuntimeException();
        }

        return new RecommendUs();
    }

    // Get message text
    public String getMessagePreviewText(){
        return getText(messagePreview);
    }

    // Grab all error message using that locator in the refer our firm screen
    public String getErrorMessage(){
        return getText(errorMessage);
    }

    // Intractable buttons
    public RecommendUs sendForm(){
        click(sendButton);
        return new RecommendUs();
    }

    public RecommendUs cancelForm(){
        click(cancelButton);

        return new RecommendUs();
    }

    public RecommendUs closeXForm(){
        click(closeXButton);

        return new RecommendUs();
    }

    public RecommendUs cancelFormAndRefresh(){
        // This method should help with form canceling and page refresh
        cancelForm();
        refreshPage();

        return new RecommendUs();
    }


}
