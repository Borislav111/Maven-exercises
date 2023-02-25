package examples.Homework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework extends DataProvider {

    @Test(dataProvider = "twoNumbers", groups = "test")
    public void sum(int a, int b){
        long id = Thread.currentThread().getId();
        int expectedSum = 8;
        int sum = a + b;
       Assert.assertEquals(expectedSum, sum);
        System.out.println(sum + " - thread id: " + id);
    }
    @Test(dataProvider = "twoNumbers", groups = "test")
    public void subtraction(int a, int b){
        long id = Thread.currentThread().getId();
        int expectedSum = -2;
        int sum = a - b;
        Assert.assertEquals(expectedSum, sum);
        System.out.println(sum + " - thread id: " + id);
    }

    @Test(dataProvider = "twoNumbers",groups = "test")
    public void multiplication(int a, int b){
        long id = Thread.currentThread().getId();
        int expectedSum = 15;
        int sum = a * b;
        Assert.assertEquals(expectedSum, sum);
        System.out.println(sum + " - thread id: " + id);
    }

    @Test(priority = 1  ,dataProvider = "twoNumbers", groups = "test")
    public void divided(int a, int b){
        long id = Thread.currentThread().getId();
        double expectedSum = 0.6;
        double sum = a / b;
        Assert.assertEquals(expectedSum, sum);
        System.out.println(sum + " - thread id: " + id);
    }

    @Test(dataProvider = "twoNumbers", groups = "test")
    public void percent(int a, int b){
        long id = Thread.currentThread().getId();
        int expectedSum = 3;
        int sum = a % b;
        Assert.assertEquals(expectedSum, sum);
        System.out.println(sum + " - thread id: " + id);
    }
}
