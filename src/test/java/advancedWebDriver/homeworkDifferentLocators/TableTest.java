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

public class TableTest {
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
        driver.get(homeUrl + "challenging_dom");
    }

    @Test
    public void tableTest() {
        WebElement table = driver.findElement(By.className("large-10"));
        WebElement tableHeader = table.findElement(By.cssSelector("thead"));

        List<WebElement> tableHeaderNames = tableHeader.findElements(By.cssSelector("tr"));

        int dolorCellIndex = 0;

        for (WebElement dolorCol : tableHeaderNames) {
            String columnName = dolorCol.getText();
            if (columnName.equals("Dolor")) {
                dolorCellIndex = tableHeaderNames.indexOf(dolorCol);
                break;
            }
            System.out.println(dolorCol.getText());
        }

        WebElement tableRows = table.findElement(By.cssSelector("tbody"));
        List<WebElement> eachRow = tableRows.findElements(By.cssSelector("tr"));

        for (WebElement rows : eachRow) {
            List<WebElement> cells = rows.findElements(By.cssSelector("td"));

            String cellText = cells.get(dolorCellIndex).getText();
            if (cellText.equals("Adipisci1")) {
                System.out.println("Deleting row with value: Adipisci1");

                WebElement deleteBtn = rows.findElement(By.linkText("delete"));
                wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));
                deleteBtn.click();
                break;
            }
        }
    }
    @AfterMethod
    public void driverClose(){
        driver.close();
    }
}
