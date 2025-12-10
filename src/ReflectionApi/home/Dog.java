package ReflectionApi.home;

import java.util.ArrayList;
import java.util.List;

public class Dog {
    public static String breed = "Like"; // порода
    public String name;
    public int age;
    public List<String> friendsName = new ArrayList<>();

    public Dog(String name, int age, List<String> friendsName) {
        this.name = name;
        this.age = age;
        this.friendsName = friendsName;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", friendsName=" + friendsName +
                '}';
    }
}