package examples;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
    @DataProvider(name = "generateValues")
    public Object[][] generateValues() {
        return new Object[][]{
                {"First-Value", "TEST", 1}, // 1 елемент - масив съдържащ стринг
                {"Second-Value", "TEST2", 2}}; // 2 елемент - масив -//-
    }
    @Test(dataProvider = "generateValues")
    public void testDataProvider(String val, String val2, int x) { // приема един параметър от тип СТРИНГ - ще бъде извикан 2 пъти от горния метод. Защото са 2 елемента
        System.out.println("Passed Parameter Is : " + val + " " + val2 + " " + x);
    }


    @DataProvider(name = "generateNumbers")
    public Object[][] generateNumbers() {
        return new Object[][]{
                {2, 3, 5},
                {5, 7, 9}};
    }
    @Test(dataProvider = "generateNumbers") // той консумира generateNumbers
    public void testDataProviderMultipleParameters(int a, int b, int expectedSum) {
        int sum = a + b;
        Assert.assertEquals(sum, expectedSum); // прави различни типове проверки
    }
    // в случая expectedSum е 5 и 9 от масива / последните числа /
    // метода смята първите 2 числа от масива пр: 2 + 3 и проверява дали са равни на expectedSum - 5 ДА
    // след това за другите 2 числа 5 + 7 - не е 9 ще фейлне
}
