package WebDriverExamples.Homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class LoginTest {
    private static WebDriver driver;

    @BeforeClass
    private static void setUpDriver() {
        System.out.println("~~~ Setting up WebDriver ~~~");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = -1)
    private static void logIn() {
        driver.get("http://training.skillo-bg.com/posts/all");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement clickLogin = driver.findElement(By.id("nav-link-login"));
        clickLogin.click();

        WebElement username = driver.findElement(By.id("defaultLoginFormUsername"));
        username.sendKeys("Teet");

        WebElement password = driver.findElement(By.id("defaultLoginFormPassword"));
        password.sendKeys("Track1");

        WebElement signIn = driver.findElement(By.id("sign-in-button"));
        signIn.click();
    }

    @Test
    private static void likePost() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement like = driver.findElement(By.cssSelector(".far.fa-heart.fa-2x"));
        like.click();
//        WebElement followButton = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Follow']")));
//        followButton.click();

        WebElement follow = driver.findElement(By.cssSelector(".btn.btn-primary"));
        explicitWait.until(ExpectedConditions.visibilityOf(follow));
        follow.click();
    }

//    @Test(priority = 1)
//    private static void searchAndFollowUser() {
//        //   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//        WebElement inputName = driver.findElement(By.id("search-bar"));
//        inputName.sendKeys("ManagerUser");
////      inputName.click();
//        WebElement dropDownWait = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown-container")));
//
//
//        WebElement clickUser = driver.findElement(By.xpath("//*[contains(text(), 'ManagerUser')]"));
//        clickUser.click();
//        WebElement userFollowBtn = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-primary.profile-edit-btn")));
//        WebElement user = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='ManagerUser']")));
//        System.out.println("Username: " + user.getText() + " Follow/Unfollow button state is: " + userFollowBtn.getText());
//
//        WebElement followUser = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-primary.profile-edit-btn")));
//        followUser.click();
//    }

    @Test(priority = 2)
    private static void logOut(){
        WebElement logout = driver.findElement(By.cssSelector(".fas.fa-sign-out-alt.fa-lg"));
        logout.click();
    }

    @AfterClass
    private static void closeDriver(){
        driver.close();
    }
}


//      WebElement findUser = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown-container")));
//      WebElement findUser2 = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown-user.container-fluid")));
//      WebElement clickUser = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("//*[contains(text(), 'ManagerUser')]")));

//      WebElement findUser = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown-user.container-fluid")));

//      List<WebElement> elements = driver.findElements(By.cssSelector(".dropdown-user.container-fluid:has(a[href*='/users/3944'])"));
//        WebElement click = elements.get(1);
//        click.click();
//        }
//       // findUser.click();
//
//
//        //WebElement clickUser = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/users/3944']")));
//
//       // clickUser.click();
//      //  System.out.println("The user is displayed: " + findUser.isDisplayed());
//
