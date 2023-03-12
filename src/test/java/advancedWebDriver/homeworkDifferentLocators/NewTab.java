package advancedWebDriver.homeworkDifferentLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class NewTab {
    private WebDriver driver;
    final String homeUrl = "https://the-internet.herokuapp.com/";
    private WebDriverWait wait;

    @BeforeSuite
    protected final void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(homeUrl + "windows");
    }
    @Test
    public void OpenNewTab(){
        WebElement openTab = driver.findElement(By.linkText("Click Here"));
        openTab.click();

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        String newTab = tabs.get(1);

        driver.switchTo().window(newTab);

        String exampleText = driver.findElement(By.cssSelector(".example h3")).getText();
        Assert.assertEquals(exampleText, "New Window", "The message is");
    }
    @AfterMethod
    public void closeDriver(){
        driver.close();
    }
}
