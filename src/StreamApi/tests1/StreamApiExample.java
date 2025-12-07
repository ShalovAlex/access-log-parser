package StreamApi.tests1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiExample {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 4, 6, 8345, 7, 23));

//        Stream<Integer> stream = list.stream(); //Создали стрим
//        Stream<Integer> sortedStream = stream.sorted(); //Вызвали промежуточную операцию (сортировки по возрастанию)
//        List<Integer> resultList = sortedStream.collect(Collectors.toList()); //Вызвали терминальную операцию
//        System.out.println(resultList);
        // тоже самое
//        System.out.println(list.stream().sorted().collect(Collectors.toList()));

        // Вызываем стрим, лимит только 3 элемента, фильтруем (числа меньше 8), сортируем, переводим в лист
        // Порядок выбирается бизнес логикой
        List<Integer> resultList = list.stream().limit(3).filter(i -> i < 8).sorted().collect(Collectors.toList());

        System.out.println(resultList);
    }
}
