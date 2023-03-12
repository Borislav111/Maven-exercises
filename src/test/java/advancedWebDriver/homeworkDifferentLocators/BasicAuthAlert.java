package advancedWebDriver.homeworkDifferentLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class BasicAuthAlert {
    private WebDriver driver;
    private WebDriverWait smallWait;
    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected final void setUpDriver() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        smallWait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void AlertAuth(){
        // By specifying userName and password in URL when accessing the page we can avoid system level dialog. This approach will work for HTTP and HTTPS pages.
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

        String getText = driver.findElement(By.cssSelector("div p")).getText();
        Assert.assertEquals(getText, "Congratulations! You must have the proper credentials.");
    }
    @AfterMethod
    public void closeDriver(){
        driver.close();
    }
}
