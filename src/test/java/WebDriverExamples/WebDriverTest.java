package WebDriverExamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebDriverTest {
    private static WebDriver driver;

    @BeforeMethod
    private static void setUpDriver(){
        System.out.println("~~~ Setting up WebDriver ~~~");
        WebDriverManager.chromedriver().setup(); // ще ни свали chromeDriver
        driver = new ChromeDriver(); // инициализираме драйвъра, който е като атрибут на класа
        driver.manage().window().maximize(); // правим максимизирането на прозореца
    }

    @Test
    private static void openPage(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://training.skillo-bg.com/posts/all");
        System.out.println(driver.getTitle()); // може и на някаква променлива да се присвои от тип string
        WebElement login = driver.findElement(By.id("nav-link-login")); //WebElement представлява елемент.
        login.click(); // след като горе сме създали обекта може да го викнем и той да кликне на логин
        WebElement username = driver.findElement(By.name("usernameOrEmail"));
        username.sendKeys("Teet");
        WebElement password = driver.findElement(By.id("defaultLoginFormPassword"));
        System.out.println("Password is selected: " + password.isSelected()); // false e защото е за checkbox
        password.sendKeys("Track1");
       // password.clear(); // ще изтрие това което е въведено - за input полета най вече
        WebElement signIn = driver.findElement(By.id("sign-in-button"));
        signIn.click();
        System.out.println("Button text is: " + signIn.getText());
        WebElement logout = driver.findElement(By.xpath("//i[@class='fas fa-sign-out-alt fa-lg']"));
        logout.click();
    }
    @Test
    private static void dropDownTest(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.mobile.bg/pcgi/mobile.cgi");
        WebElement selectMercedes = driver.findElement(By.name("marka"));
        Select dropDownCategory = new Select(selectMercedes); // Може и по този начин
       // Select dropDownCategory = new Select(driver.findElement(By.name("marka"))); // подаваме му обект от тип WebElement
        dropDownCategory.selectByValue("Mercedes-Benz");
    }

    @Test
    private static void explicitWait(){
        driver.get("http://training.skillo-bg.com/posts/all");
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = driver.findElement(By.id("nav-link-login"));

        WebElement allPosts = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("app-all-posts"))); // локираме елемента И го проверяваме по условие дали е visible на всеки 500м. сек проверява за 10 сек.
// ще направи обекта ЧАК КОГАТО този локатор стане ВИДИМ
        // изчакваме елемента да се появи и ЧАК тогава правим долу клик на Логина
        loginBtn.click();
        explicitWait.until(ExpectedConditions.invisibilityOf(allPosts)); // тук очакваме invisibility на всички постове.
    }


    @AfterMethod
    private static void closeDriver(){
        System.out.println("~~~ Closing WebDriver ~~~");
        driver.close();
    }
}
