package StreamApi.Home.tests2;

import java.util.ArrayList;
import java.util.Arrays;

public class IsForEach {
    public static void printList(ArrayList<Integer> list) {
        list.forEach(element -> System.out.println(element));
    }

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(3, 31, 6, 98, 52));
        printList(numbers);
    }
}