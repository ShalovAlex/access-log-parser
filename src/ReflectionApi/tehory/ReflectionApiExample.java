package ReflectionApi.tehory;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ReflectionApiExample {
    public static void main(String[] args) {
        Dog dog = new Dog("Moscow", "Vasya", 3);

        Class dogClass = dog.getClass();

        System.out.println(Arrays.toString(dogClass.getDeclaredFields()));

        for (Field declaredField : dogClass.getDeclaredFields()){
            System.out.println(declaredField.getName());
        }
    }
}
