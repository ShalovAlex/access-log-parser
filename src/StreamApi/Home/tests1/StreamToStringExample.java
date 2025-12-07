package StreamApi.Home.tests1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamToStringExample {

    public static String getStringFromStream(Stream<String> stringStream) {
        return stringStream.collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("Ехали", "медведи", "на", "велосипеде...");
        String result = getStringFromStream(words.stream());
        System.out.println(result);
    }
}
