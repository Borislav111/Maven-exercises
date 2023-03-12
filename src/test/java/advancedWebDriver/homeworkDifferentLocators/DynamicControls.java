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

public class DynamicControls {
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(homeUrl + "dynamic_controls");
    }

    @Test
    public void CheckBoxTest() {
        WebElement checkBox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkbox-example input[label='blah']")));
        checkBox.click();
        Assert.assertTrue(checkBox.isSelected(), "Checkbox should be selected");

        WebElement removeCheckboxBtn = driver.findElement(By.xpath("//button[text()='Remove']"));
        String addBtn = "Add";
        removeCheckboxBtn.click();

        wait.until(ExpectedConditions.textToBe(By.cssSelector("#checkbox-example button"), "Add"));
        String btnText = driver.findElement(By.cssSelector("#checkbox-example button")).getText();
        Assert.assertEquals(btnText, addBtn, "The button text is");

        String msg = driver.findElement(By.cssSelector("#checkbox-example p")).getText();
        Assert.assertEquals(msg, "It's gone!", "The message is");
    }

    @Test(priority = 1)
    public void EnableDisableTest() {
        WebElement form = driver.findElement(By.id("input-example"));
        WebElement inputField = form.findElement(By.cssSelector("input"));
        Assert.assertFalse(inputField.isEnabled(), "The field must be disabled");

        WebElement enableBtn = form.findElement(By.cssSelector("button"));
        String enableBtnText = enableBtn.getText();
        Assert.assertEquals(enableBtnText, "Enable", "The button text is");
        enableBtn.click();

        wait.until(ExpectedConditions.elementToBeClickable(inputField));
        String msg = form.findElement(By.id("message")).getText();
        Assert.assertEquals(msg, "It's enabled!", "The message is");
        inputField.sendKeys("Hello!");

        WebElement disableBtn = enableBtn;
        disableBtn.click();
    }

    @AfterMethod
    public void closeDriver() {
        driver.close();
    }
}
