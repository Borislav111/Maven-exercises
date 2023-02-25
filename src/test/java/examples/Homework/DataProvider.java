package examples.Homework;

import org.testng.annotations.Test;

public class DataProvider {
    @Test(dependsOnGroups = "test")
    @org.testng.annotations.DataProvider(name = "twoNumbers")
    public Object[][] createNumbers(){
        return new Object[][]{
                {3, 5}};
    }
}
