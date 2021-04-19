package BasePage;

import AbstractComponent.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BaseActions extends AbstractComponents {

    private WebDriver driver;

    public BaseActions(WebDriver driver) {
        this.driver = driver;
    }

    public BaseActions(){
        super();
    }

    public String getPageUrl(){
        return driver.getCurrentUrl();
    }

    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    public void click(By locator){
        findElement(locator).click();
    }

    public void enter(By locator, String textToEnter){
        findElement(locator).sendKeys(textToEnter);
    }

    public void clear(By locator){
        findElement(locator).clear();
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }

    public String getText(By locator){
        return findElement(locator).getText();
    }

    public boolean isSelected(By locator){
        try {
            return findElement(locator).isSelected();
             }catch(org.openqa.selenium.NoSuchElementException exception){
            return false;
        }

    }

    public boolean isDisplayed(By locator){
        try {
            return findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException exception){
            return false;
        }

    }

    public void jsExecutorClick(String scriptToExecute){
        ((JavascriptExecutor) driver).executeScript(scriptToExecute);
    }

}
