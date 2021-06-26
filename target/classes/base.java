package resources;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class base {

    public WebDriver driver;
    public Properties prop;
    private static Logger log = LogManager.getLogger(base.class.getName());

    // Webdriver initialisation utility
    public WebDriver initialiseDriver() throws IOException {

        // Creates the property object
        prop = new Properties();

        // This allows for you to read the file and it requires file path
        FileInputStream fips = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
        prop.load(fips);

        // To allow maven set the browser to be used via maven/ Jenkins(mvn test -Dbrowser=chrome)
        String browserName = System.getProperty("browser");

        // Enables you to choose browser, depending on request or choice form data.properties file.
        // String browserName = prop.getProperty("browser");

        switch (browserName){
            case "chrome":
                //System.setProperty("wbdriver.chrome.driver", System.getProperty("user.dir") + "\\webdrivers\\chromedriver.exe"); //System.getProperty("user.dir") + "//webdrivers
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                log.info("Starting chrome browser");
                break;
            case "headless":
                System.setProperty("wbdriver.chrome.driver", System.getProperty("user.dir") + "\\webdrivers\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                driver = new ChromeDriver(options);
                log.info("Starting chrome browser in headless mode");
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                log.info("Starting firefox browser");
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                log.info("Starting internet explorer browser");
                break;
            case "microsoft-edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                log.info("Starting microsoft edge browser");
                break;
            default:
                log.error("Invalid browser requested");
        }

        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;

    }

    // Screenshot utility
    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {

        // Path to store screenshot \\reports\\ -> folder
        String pathName = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";

        // Takes the screenshot
        TakesScreenshot ts =(TakesScreenshot) driver;

        // Creates a file and send the to the assigned destination
        FileHandler.copy(ts.getScreenshotAs(OutputType.FILE),new File(pathName));

        return pathName;

    }

    // Javascript executor utility
    public void jsExecutor(String scriptToExecute){
        ((JavascriptExecutor) driver).executeScript(scriptToExecute);
    }

}
