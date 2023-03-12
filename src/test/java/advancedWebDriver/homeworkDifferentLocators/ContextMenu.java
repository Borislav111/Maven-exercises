package advancedWebDriver.homeworkDifferentLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class ContextMenu {
    private WebDriver driver;
    private WebDriverWait smallWait;
    final String homeUrl = "https://the-internet.herokuapp.com/";

    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected final void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        smallWait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void ContextMenu() {
        driver.get(homeUrl + "context_menu");

        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/context_menu", "Url is:");
        WebElement box = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(box).perform(); // RIGHT-CLICK

        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        Assert.assertEquals(textAlert, "You selected a context menu", "The message is:");
        alert.accept();
        String contextHeader = driver.findElement(By.cssSelector("div h3")).getText();
        Assert.assertEquals(contextHeader, "Context Menu", "The message should be:");
    }
    @AfterMethod
    public void closeDriver(){
        driver.close();
    }
}
