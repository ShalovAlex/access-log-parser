package ReflectionApi.home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ReflectionApi.home.Reset.resetObjectWithStatics;

public class Cat {
    private static String breed = "Persian"; // порода
    public String name;
    private int age;
    private List<String> friendsName = new ArrayList<>();

    public Cat(String name, int age, List<String> friendsName) {
        this.name = name;
        this.age = age;
        this.friendsName = friendsName;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", friendsName=" + friendsName +
                '}';
    }

    public static void main(String[] args) throws IllegalAccessException {
        Cat cat = new Cat("Vasya", 10, new ArrayList<>(Arrays.asList("Anton", "Oleg", "Igor")));

        System.out.println("До обнуления: " + cat);

        resetObjectWithStatics(cat);

        System.out.println("После обнуления: " + cat);
        System.out.println("Возраст (примитив не изменился): " + cat.age);

        Dog dog = new Dog("Rex", 14, new ArrayList<>(Arrays.asList("Alex", "Stas", "Ivan")));

        System.out.println("До обнуления: " + dog);

        resetObjectWithStatics(dog);

        System.out.println("После обнуления: " + dog);
        System.out.println("Возраст (примитив не изменился): " + dog.age);

    }
}

