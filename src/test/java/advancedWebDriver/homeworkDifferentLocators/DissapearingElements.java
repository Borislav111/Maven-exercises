package advancedWebDriver.homeworkDifferentLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class DissapearingElements {
    private WebDriver driver;
    private WebDriverWait smallWait;
    final String homeUrl = "https://the-internet.herokuapp.com/";
    @BeforeSuite
    protected final void setupTestSuite() {
        WebDriverManager.edgedriver().setup();
    }

    @BeforeMethod
    protected final void setUpDriver() {
        this.driver = new EdgeDriver();
       // this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        smallWait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void DissapearingElements() {
        driver.get(homeUrl + "disappearing_elements");
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://the-internet.herokuapp.com/disappearing_elements");

        driver.navigate().refresh();

        WebElement content = driver.findElement(By.id("content"));
        List<WebElement> liElements = content.findElements(By.cssSelector("li"));

        System.out.println("The elements are:");
        for (WebElement list : liElements) {
            System.out.print(list.getText() + " ");
        }
    }
    @AfterMethod
    public void closeDriver(){
        driver.close();
    }
}
