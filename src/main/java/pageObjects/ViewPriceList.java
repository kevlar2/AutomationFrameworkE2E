package pageObjects;

import BasePage.BaseActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ViewPriceList extends BaseActions {

    private By viewPriceList =By.cssSelector("div[class='genesis-nav-menu'] ul li:nth-child(2)");

    // wills-powers-of-attorney-wills
    private By wills =By.cssSelector("a[href*='wills-powers']");

    // Purchase of Business & Franchise - Price List
    private By businesAndFranchise =By.cssSelector("a[href*='business-commercial']");

    // conveyancing-property Buy to Let - Price List
    private By conveyancingProperty =By.cssSelector("a[href*='conveyancing-property']");

    // Grab page title of the pages within the view price list screen
    private By pageTitles =By.cssSelector("h1[class='lpl-heading']");

    // Sidebar menu selection for view price list price list page
    private By services =By.cssSelector("aside[class='lpl-sidebar-menu'] button:nth-child(1)");
    private By keyStages =By.cssSelector("aside[class='lpl-sidebar-menu'] button:nth-child(2)");
    private By professionals =By.cssSelector("aside[class='lpl-sidebar-menu'] button:nth-child(3)");

    // Tables elements within the sidebar menu selections
    private By servicesTable =By.cssSelector("table[class='lpl-service-table lpl-table'] tbody tr td");
    private By keyStagesTable =By.cssSelector("table[class='lpl-stage-table lpl-table'] tbody tr td");
    private By professionalQualificationTable = By.cssSelector("table[class='lpl-qualification-table lpl-table'] tbody tr td");
    private By experienceTable = By.cssSelector("table[class='lpl-experience-table lpl-table'] tbody tr td");



    // Driver Constructor for this page object
    public ViewPriceList(WebDriver driver) {
        super(driver);
    }

    public ViewPriceList(){
        super();
    }

    // Validate webpage link
    public String validateWebpageLink(){
        String actual  = getPageUrl();
        return actual;
    }

    // Navigate to View price list page
    public ViewPriceList getViewPriceListPage(){
        click(viewPriceList);
        return new ViewPriceList();
    }

    // Navigate to wills-powers-of-attorney-wills
    public ViewPriceList getWillsPowersOfAttorneyWillsPage(){
        click(wills);
        return new ViewPriceList();
    }

    // Navigate to Purchase of Business & Franchise page
    public ViewPriceList getBusinessAndFranchisePage(){
        click(businesAndFranchise);
        return new ViewPriceList();
    }

    // Navigate to conveyancing-property Buy to Let page
    public ViewPriceList getConveyancingPropertyPage(){
        click(conveyancingProperty);
        return new ViewPriceList();
    }

    // Validate page title
    public String validatePageTitle(){
        return getText(pageTitles);
    }


    // Navigate to menus on the side bar
    public ViewPriceList getServicesMenu(){
        click(services);
        return new ViewPriceList();
    }

    public ViewPriceList getKeystagesMenu(){
        click(keyStages);
        return new ViewPriceList();
    }

    public ViewPriceList getProfessionalsMenu(){
        click(professionals);
        return new ViewPriceList();
    }

    // Get elements with the sidebar menu

    public List<WebElement> validateServicesTableData(){
        return findElements(servicesTable);
    }

    public List<WebElement> validateKeyStagesTableData(){
        return findElements(keyStagesTable);
    }

    public List<WebElement> validateProfessionalQualificationTable(){
        return findElements(professionalQualificationTable);
    }

    public List<WebElement> validateExperienceTableData(){
        return findElements(experienceTable);
    }


}
