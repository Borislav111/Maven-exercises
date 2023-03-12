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

public class DynamicLoading {
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
        driver.get(homeUrl + "dynamic_loading");
    }

    @Test
    public void FirstDynamicLoading(){
        clickElement(By.partialLinkText("Example 1:"));
        String firstExampleText = "Example 1: Element on page that is hidden";
        String firstExample = driver.findElement(By.cssSelector(".example h4")).getText();
        Assert.assertEquals(firstExample, firstExampleText, "The text is:");

        clickElement(By.cssSelector("#start button"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));
        String finalText = driver.findElement(By.cssSelector("#finish h4")).getText();
        Assert.assertEquals(finalText, "Hello World!", "Message should be:");
    }

    @Test(priority = 1)
    public void SecondDynamicLoading(){
        clickElement(By.partialLinkText("Example 2:"));
        String secondExampleText = "Example 2: Element rendered after the fact";
        String secondExample = driver.findElement(By.cssSelector(".example h4")).getText();
        Assert.assertEquals(secondExample, secondExampleText, "Text should be:");

        clickElement(By.cssSelector("#start button"));
        WebElement finalText = driver.findElement(By.cssSelector("#finish h4")); // Passes because of implicitlyWait
        wait.until(ExpectedConditions.visibilityOf(finalText));
        Assert.assertEquals(finalText.getText(), "Hello World!", "Message should be:");
    }

    private WebElement clickElement(By locator){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        return element;
    }
    @AfterMethod
    public void closeDriver(){
        driver.close();
    }
}
