package AbstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class AbstractComponents {

    public abstract String getPageUrl();

    public abstract WebElement findElement(By locator);

    public abstract List<WebElement> findElements(By locator);

    public abstract void click(By locator);

    public abstract void enter(By locator, String textToEnter);

    public abstract void clear(By locator);

    public abstract void refreshPage();

    public abstract String getText(By locator);

    public abstract boolean isSelected(By locator);

    public abstract boolean isDisplayed(By locator);

    public abstract void jsExecutorClick(String scriptToExecute);


}
