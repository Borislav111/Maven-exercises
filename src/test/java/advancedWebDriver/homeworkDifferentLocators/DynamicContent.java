package advancedWebDriver.homeworkDifferentLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DynamicContent {
    private WebDriver driver;
    final String homeUrl = "https://the-internet.herokuapp.com/";
    private WebDriverWait wait;

    @BeforeSuite
    protected final void setUpTestSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTestMethod() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    @Test
    public void Dynamic(){
        driver.get(homeUrl + "dynamic_content");

        WebElement text = driver.findElement(By.id("content"));
        List <WebElement> listText = text.findElements(By.className("large-10"));
        WebElement firstText = listText.get(1);
        System.out.println("First text: ");
        System.out.println(firstText.getText());

        driver.navigate().refresh();
        System.out.println("First text after refresh: ");
        WebElement firstTextAfterRefresh = driver.findElements(By.className("large-10")).get(1);
        wait.until(ExpectedConditions.visibilityOf(firstTextAfterRefresh));
        System.out.println(firstTextAfterRefresh.getText());
    }
    @AfterMethod
    public void driverClose(){
        driver.close();
    }
}
