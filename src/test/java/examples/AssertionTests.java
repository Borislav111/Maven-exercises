package examples;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionTests {
    @Test
    public void testHardAssertion(){
        Assert.assertTrue(3 > 2); // това минава
        Assert.assertEquals("Text", "Test"); //това ще фейлне
    }

    @Test
    public void softAssertion(){
        SoftAssert soft = new SoftAssert();

        soft.assertTrue(5 == 6); // ще фейл
        soft.assertFalse(6 < 5); // ще мине
        // ще се изпълнят и ще фейлне когато имаме неуспешен assert

        soft.assertAll();
    }
}
