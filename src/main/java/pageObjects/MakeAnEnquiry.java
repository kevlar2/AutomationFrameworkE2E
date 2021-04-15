package pageObjects;

import BasePage.BaseActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MakeAnEnquiry extends BaseActions {

    private By EnquiryScreen = By.cssSelector("button[data-id='231']");
    private By firstName = By.cssSelector("input[name='input_1.3']");
    private By lastName = By.cssSelector("input#input_7_1_6");
    private By email = By.cssSelector("input[id='input_7_2']");
    private By telephoneNumber = By.cssSelector("*[id='input_7_5']");
    private By messageField = By.cssSelector("*[id='input_7_8']");
    private By howDidYouHearAboutUs =By.cssSelector("*[id='input_7_9']");
    private By submit =By.cssSelector("input[type='submit']"); // input[id='gform_submit_button_7']
    private By addressDetails =By.cssSelector("ul[class='address-details'] li");
    private By contactDetails = By.cssSelector("ul[class='contact-details'] li");
    private By getFnameAndLnameWarning =By.cssSelector("div[id='validation_message_7_1']");
    private By getEmailWarning =By.cssSelector("div[id='validation_message_7_2']");


    // Driver Constructor for this page object
    public MakeAnEnquiry(WebDriver driver) {
        super(driver);
    }

    public MakeAnEnquiry(){
        super();
    }

    public MakeAnEnquiry getEnquiryScreen(){
        click(EnquiryScreen);
        return new MakeAnEnquiry();
    }

    // Methods to enter details
    public MakeAnEnquiry enterPersonDetails(String fName, String lName, String setEmail, String telephoneNum){
        enter(firstName, fName);
        enter(lastName, lName);
        enter(email, setEmail);
        enter(telephoneNumber, telephoneNum);

        return new MakeAnEnquiry();
    }

    // Enter message and other text
    public MakeAnEnquiry enterMessage(String messageToEnter){
        enter(messageField, messageToEnter);

        return new MakeAnEnquiry();
    }

    public MakeAnEnquiry setHowDidYouHearAboutUs(String textToEnter){
        enter(howDidYouHearAboutUs, textToEnter);

        return new MakeAnEnquiry();
    }

    // To clear field use below methods
    public MakeAnEnquiry clearField(String fieldToClear){
        switch (fieldToClear){
            case "fName":
                clear(firstName);
                break;
            case "lName":
                clear(lastName);
                break;
            case "email":
                clear(email);
                break;
            case "telephoneNum":
                clear(telephoneNumber);
                break;
            default:
                throw new RuntimeException();
        }

        return new MakeAnEnquiry();
    }

    // using javascript executor to interact with the checkbox button
    public MakeAnEnquiry jsExecutor(String scriptToExecute){
        jsExecutorClick(scriptToExecute);
        return new MakeAnEnquiry();
    }

    public  MakeAnEnquiry submitForm(){
        click(submit);

        return new MakeAnEnquiry();
    }

    public String getFameAndLameWarn(){
        return getText(getFnameAndLnameWarning);
    }

    public String getEmailWarn(){
        return getText(getEmailWarning);
    }

    // Add elements to a list
    public List<WebElement> getAddressDetails(){
        return findElements(addressDetails);
    }

    public List<WebElement> getContactDetails(){
        return findElements(contactDetails);
    }



}