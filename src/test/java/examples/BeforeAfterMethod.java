package examples;

import org.testng.annotations.*;

public class BeforeAfterMethod {
//Изпълнява се ПРЕДИ ВСЕКИ метод с @TEST, After след ВСЕКИ ЕДИН метод с @Test
    @BeforeMethod
    public void setUp() {
        System.out.println("Before each test methods");
    }

    @Test
    public void testMethod1() {
        System.out.println("Test method1");
    }

    @Test
    public void testMethod2() {
        System.out.println("Test method2");
    }

    @AfterMethod
    public void cleanUp() {
        System.out.println("After each test methods");
    }
}
