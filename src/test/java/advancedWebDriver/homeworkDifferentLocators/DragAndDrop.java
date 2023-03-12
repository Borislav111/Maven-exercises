package advancedWebDriver.homeworkDifferentLocators;

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class DragAndDrop {
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
        driver.get(homeUrl + "drag_and_drop");
    }

    @Test
    public void dragNDrop() {
        WebElement drag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-a")));
        WebElement drop = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-b")));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(drag, drop).perform();

    }

    @AfterMethod
    public void closeDriver() {
        driver.close();
    }
}
