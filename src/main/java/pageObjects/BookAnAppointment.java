package pageObjects;

import BasePage.BaseActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookAnAppointment extends BaseActions {

    // Navigate to Book an appointment page
    private By getBookAnAppointmentScreen = By.cssSelector("div[class='genesis-nav-menu'] ul li:nth-child(6)");


    // Driver Constructor for this page object
    public BookAnAppointment(WebDriver driver) {
        super(driver);
    }

    public BookAnAppointment(){
        super();
    }

    public BookAnAppointment getBookAnAppointmentPage(){
        click(getBookAnAppointmentScreen);
        return new BookAnAppointment();
    }

    public String getCurrentUrl(){
        return getPageUrl();
    }
}
