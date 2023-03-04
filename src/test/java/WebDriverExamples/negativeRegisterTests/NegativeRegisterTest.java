package WebDriverExamples.negativeRegisterTests;

import com.sun.jna.WString;
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
import java.util.List;
import java.util.concurrent.TimeUnit;


public class NegativeRegisterTest {
    private static WebDriver driver;
    final static String homeUrl = "http://training.skillo-bg.com/posts/all";
    final static String loginUrl = "http://training.skillo-bg.com/users/login";
    final static String registerUrl = "http://training.skillo-bg.com/users/register";

    @BeforeClass
    private static void setUpDriver() {
        System.out.println("~~~ Setting up WebDriver ~~~");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // малки секунди до 10
        driver.get(homeUrl);
    }

    @Parameters({"username", "email", "password"})
    @Test
    public void negativeReg(String username, String email, String password) { // трябва да ги добавим като параметри
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        System.out.println("1. Navigate to login page.");
        clickElement(By.id("nav-link-login"), 5);
        wait.until(ExpectedConditions.urlToBe(loginUrl)); // подсигуряваме се че url е правилният

        System.out.println("2. Click on Register link.");
        clickElement(By.cssSelector("a[href='/users/register']"), 3);
        wait.until(ExpectedConditions.urlToBe(registerUrl));

        WebElement signUpHeader = driver.findElement(By.xpath("//app-register//h4[text()='Sign up']"));
        // Assert.assertTrue(signUpHeader.isDisplayed()); // 2 те неща проверяват дали това заглавие Sign up е видимо в страницата, не само в DOM
        wait.until(ExpectedConditions.visibilityOf(signUpHeader)); // разликата е че тук ще изчака някакво време докато стане visible

        System.out.println("3. Enter one character as username.");
        WebElement enterUsername = driver.findElement(By.name("username"));
        enterTextField(enterUsername, username);  // тук правим WebElement защото долу метода така го изисква void e и го извикваме + параметър, който може да подадем и в testng файла

        System.out.println("4. Enter invalid email");
        WebElement enterValidEmail = driver.findElement(By.cssSelector("input[formcontrolname='email']"));
        enterTextField(enterValidEmail, email);

        System.out.println("5. Enter invalid password");
        WebElement enterPassword = driver.findElement(By.name("password"));
        enterTextField(enterPassword, password);

        System.out.println("6. Re-enter invalid password");
        WebElement reEnterPassword = driver.findElement(By.name("verify-password"));
        enterTextField(reEnterPassword, password);


        System.out.println("7. Click Log in");
        clickElement(By.id("sign-in-button"), 2);
        WebElement toasMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("toast-message")));
        String toastMessage = toasMsg.getText().trim();
        Assert.assertEquals(toastMessage, "Registration failed!", "Successful registration!");
        wait.until(ExpectedConditions.urlToBe(registerUrl));

    }

    // За всички елементи които ще кликаме да проверяваме дали са в clickable state
    // WebElement return - може да ни потрябва като кликнем на някой елемент и после продължаваме итеракциията от него
    private WebElement clickElement(By locator, int waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        return element;
        // не става тук да сложа toast-message защото ще го търси на всяко кликане, а на мен ми трябва само на едно конкретно
    }

    private void enterTextField(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        SoftAssert soft = new SoftAssert(); //използвам soft за да не гърми, понеже на потвърди парола има тикче а не Х и така ми гърми, понеже паролата съвпада
        element.sendKeys(text);

        Boolean isInvalid = element.getAttribute("class").contains("is-invalid"); // проверяваме дали Х ще се покаже - искаме да се покаже
        soft.assertTrue(isInvalid, "The field is valid");
        Boolean dangerText = element.getAttribute("class").contains("invalid-feedback"); // проверяваме съобщението, което се показва като въведем невалидни данни
        Assert.assertFalse(dangerText, "Field is valid");


        //wait.until() чака всички елементи с класа invalid-feedback да станат видими на страницата и след това ги връща като лист от WebElements
        // цикъла обхожда листа, присвоява всеки WebElement на променливата showDangerText
        // за всеки WebElement цикъла принтира съдържанието му в конзолата
        List<WebElement> showDangerText = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".invalid-feedback")));
        for (WebElement invalidMsg : showDangerText) {
            System.out.println("The message is: " + invalidMsg.getText());
        }
    }

    @AfterMethod
    public void closeDriver() {
        driver.close();
    }
}


//        private WebElement enterText(By locator, String text){
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//            element.sendKeys(text);
//            return element;
//        }