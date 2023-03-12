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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class AddRemoveElements {
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
        this.driver = new ChromeDriver(options);

        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void addAndRemoveElements() {
        driver.get(homeUrl + "/add_remove_elements/");
        WebElement addElementBtn = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addElementBtn.click();
        addElementBtn.click();
        addElementBtn.click();
        Assert.assertTrue(addElementBtn.isDisplayed(), "Button should be enabled");

        WebElement elements = driver.findElement(By.id("elements"));
        List<WebElement> deleteElementBtn = elements.findElements(By.cssSelector("[class^=added-manually]")); // 3 buttons

        int size = deleteElementBtn.size();
        System.out.println("The number of Delete buttons is: " + size);

        WebElement thirdBtn = deleteElementBtn.get(2);
        WebElement secondBtn = deleteElementBtn.get(1);
        wait.until(ExpectedConditions.elementToBeClickable(thirdBtn));
        wait.until(ExpectedConditions.visibilityOf(secondBtn));
        thirdBtn.click();

        deleteElementBtn = elements.findElements(By.cssSelector("[class^=added-manually]")); // called again to update the list - 2 buttons
        size = deleteElementBtn.size();
        System.out.println("After deleting one button the size is: " + size);
        if (size == 3) {
            Assert.assertFalse(thirdBtn.isDisplayed(), "The button is still visible");
        }
        Assert.assertTrue(secondBtn.isDisplayed(), "The second button should be displayed");
    }

    @AfterMethod
    public void closeDriver() {
        driver.close();
    }
}
