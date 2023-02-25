package examples;

import org.testng.annotations.Test;
import java.io.IOException;

public class TestExceptions {
    // очаква всеки един метод да хвърли дадения exception тогава теста ще мине
    // Ще фейлне ако не е хвърлен
    @Test(expectedExceptions = RuntimeException.class)
    public void testExceptions() {
        throw new RuntimeException("This is Pass");
    }
    @Test(expectedExceptions = {IOException.class, NullPointerException.class})
    public void testExceptions1() {
        throw new NullPointerException("This is Pass");
    }
    @Test(expectedExceptions = {IOException.class})
    public void testExceptions2() {
        throw new NullPointerException("This is Fail");
    }
    @Test
    public void testExceptions3() throws Exception { // някой по call stack трябва да обработи този exception
        throw new IOException("This is Fail");
    }
}
