package StreamApi.tests1.tests1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApiExample {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 4, 6, 8345, 7, 23, 7, 6, 5, 77));

        //Свой способ сортировки
//        Comparator<Integer> comparator = new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2.compareTo(o1);
//            }
//        };
//        System.out.println(list.stream().sorted().collect(Collectors.toList()));
//        System.out.println(list.stream().sorted(comparator).collect(Collectors.toList()));
// ИЛИ
//        Comparator<Integer> comparator = (o1, o2) -> o2.compareTo(o1);
//        System.out.println(list.stream().sorted().collect(Collectors.toList()));
//        System.out.println(list.stream().sorted(comparator).collect(Collectors.toList()));
// ИЛИ
//        System.out.println(list.stream().sorted((o1, o2) -> o2.compareTo(o1)).collect(Collectors.toList()));
// ИЛИ
//        System.out.println(list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));

//        List<Integer> resultlist = list.stream()
//                .filter(i -> i < 5)         //Берем i и сравниваем i < 5
//                .sorted(Comparator.reverseOrder())
//                .limit(5)
//                .collect(Collectors.toList());
//
//        System.out.println(resultlist);
        // Integer превратили в String .map
        List<String> resultlist = list.stream()
                .filter(i -> i > 5)         //Берем i и сравниваем i < 5
                .sorted(Comparator.reverseOrder())
                .limit(5)
                .map(i -> i.toString())
                .collect(Collectors.toList());

        System.out.println(resultlist);
    }
}
