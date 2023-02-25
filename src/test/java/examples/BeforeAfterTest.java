package examples;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BeforeAfterTest {

    @BeforeTest   // За всеки BEFORE /тип setup/ Ако трябва да направим някакви неща ПРЕДИ тестовете / пример логин, навигация в конкретна страница../
    public void setUp() {
        System.out.println("Before all test methods");
    }

    @Test // Ако изпълним само този тест с бутончето до него, ще се изпълнят и before & after също
    public void testMethod1() {
        System.out.println("Test method1");
    }

    @Test
    public void testMethod2() {
        System.out.println("Test method2");
    }

    @AfterTest
    public void cleanUp() {
        System.out.println("After all the test methods");
    }
}
