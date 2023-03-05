package WebDriverExamples.nonMatchingpasswords;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

// Open URL
// Open Login page
// Verify that the correct url is displayed
// Click Register link
// Verify that the correct url is displayed
// Verify that the header of register form is displayed
// Enter valid username
// Enter valid email
// Enter valid password
// Enter different password in verify password field
// Verify that the X icon is displayed next to verify password field
// Click Sign up button
// Verify that the registration is failed

public class NonMatchingPass {
    private static WebDriver driver;
    final static String homeUrl = "http://training.skillo-bg.com/posts/all";
    final static String loginUrl = "http://training.skillo-bg.com/users/login";
    final static String regUrl = "http://training.skillo-bg.com/users/register";

    @BeforeClass
    public void setUp() {
        System.out.println("~~~ Setting up WebDriver ~~~");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(homeUrl);
    }

    @Test
    public void openLoginForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        System.out.println("1. Open login form");
        clickElement(By.id("nav-link-login"), 3);
        wait.until(ExpectedConditions.urlToBe(loginUrl));
    }

    @Test(dependsOnMethods = "openLoginForm")
    public void openRegisterForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        System.out.println("2. Open registration form");
        clickElement(By.cssSelector("a[href='/users/register']"), 3);
        wait.until(ExpectedConditions.urlToBe(regUrl));
        WebElement regHeader = driver.findElement(By.xpath("//h4[text()='Sign up']"));
        wait.until(ExpectedConditions.visibilityOf(regHeader));
    }

    @Parameters({"username", "email", "password"})
    @Test(dependsOnMethods = "openRegisterForm")
    public void enterCredentials(String username, String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        System.out.println("3. Enter valid credentials");
        WebElement usernameField = driver.findElement(By.name("username"));
        enterText(usernameField, username);

        WebElement emailField = driver.findElement(By.cssSelector("input[formcontrolname='email']"));
        enterText(emailField, email);

        WebElement passwordField = driver.findElement(By.name("password"));
        enterText(passwordField, password);

        System.out.println("4. Enter different password");
        WebElement confimrPass = driver.findElement(By.name("verify-password"));
        confimrPass.sendKeys("1234567aaaa");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("is-invalid")));
        WebElement invalidMsg = driver.findElement(By.className("invalid-feedback"));
        System.out.println("The error message is: " + invalidMsg.getText());

        System.out.println("5. Try to Sign in");
        clickElement(By.id("sign-in-button"), 2);
        SoftAssert soft = new SoftAssert();

        WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("toast-message")));
        String toastMessage = toastMsg.getText().trim();
        soft.assertEquals(toastMessage, "Registration failed!", "Registration is:");
        soft.assertAll();
    }

    private WebElement clickElement(By locator, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        return element;
    }

    private void enterText(WebElement element, String text) {
        element.sendKeys(text);

        Boolean validInput = element.getAttribute("class").contains("is-valid");
        Assert.assertTrue(validInput, "Field is invalid!");
    }

    @AfterClass
    public void closeDriver() {
        driver.close();
    }
}
