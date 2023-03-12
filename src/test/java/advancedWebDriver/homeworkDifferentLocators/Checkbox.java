package advancedWebDriver.homeworkDifferentLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Checkbox {
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void testCheckbox() {
        driver.get(homeUrl + "/checkboxes");
        //WebElement firstCheckbox = driver.findElements(By.cssSelector("#checkboxes input")).get(0); // може и така без метода
        clickCheckboxes(By.cssSelector("#checkboxes input"), 0);
       // clickCheckboxes(By.cssSelector("#checkboxes input"),1);
    }

    private WebElement clickCheckboxes(By locator, int index){
//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        List<WebElement> element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        WebElement checkbox = element.get(index);
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected(), "The checkbox with index " + index + " must be selected!");
        return checkbox;
    }
    @AfterMethod
    public void closeDriver(){
        driver.close();
    }
}
