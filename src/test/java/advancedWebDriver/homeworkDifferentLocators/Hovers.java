package advancedWebDriver.homeworkDifferentLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Hovers {
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
        driver.get(homeUrl + "hovers");
    }
    @Test
    public void Hover(){
       List<WebElement> element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("img")));
       WebElement thirdUser = element.get(3);

       Actions action = new Actions(driver);
       action.moveToElement(thirdUser).perform();

       WebElement displayedText = driver.findElements(By.className("figcaption")).get(2);
       String text = displayedText.getText();
//       System.out.println(text);
       Assert.assertEquals(text, "name: user3\n" +
                "View profile", "The text should be:");
    }
    @AfterMethod
    public void closeDriver(){
        driver.close();
    }
}
