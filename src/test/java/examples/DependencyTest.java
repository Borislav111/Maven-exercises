package examples;

import org.testng.annotations.Test;

public class DependencyTest {

    // може да се добави в () alwaysRun = true - така винаги ще се изпълни
    // посочва се името на групата
    @Test(dependsOnGroups = "group.dependency") // Зависи на всички тестове които са в тази група
    public void testDependsOnGroup(){ // като го изпълняваме ще ПРОВЕРИ дали ВСИЧКИ ТЕСТОВЕ от тази група са изпълнени и МИНАЛИ и чак тогава ще го изпълни
        System.out.println("This method depends on group.dependency");
    }

    @Test(groups = "group.dependency")
    public void testGroupDependency(){
        System.out.println("This is a test part of group.dependency");
    }

    @Test(groups = "group.dependency") // ако примерно този не се изпълни и testDependsOnGroup няма да се изпълни
    public void testGroupDependency2(){
        System.out.println("This is a second part of group.dependency");
    }

    // също имаме за метод, зависещ от друг метод
    // посочва се името на МЕТОДА
    @Test(dependsOnMethods = "testMethodDependency")
    public void testDependOnMethod(){
        System.out.println("This depends on methodaaaa");
    }

    @Test
    public void testMethodDependency(){
        System.out.println("Methodaaaa"); // ако този фейлне и горния - зависещия ще прекъсне
    }
}
