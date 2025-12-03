package tests4_Collections1;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics();

        stats.addEntry("https://qwerty.com/s1", 200, "Windows 10");
        stats.addEntry("https://qwerty.com/s2", 404, "macOS");
        stats.addEntry("https://qwerty.com/s3", 200, "Linux");
        stats.addEntry("https://qwerty.com/s4", 200, "Windows 10");
        stats.addEntry("https://qwerty.com/s1", 200, "Windows 10");

        System.out.println("Существующих страниц: " + stats.getExistingPagesCount());

        Map<String, Double> osStats = stats.getOsStatistics();
        for (Map.Entry<String, Double> entry : osStats.entrySet()) {
            System.out.printf("%s: %.2f%%%n", entry.getKey(), entry.getValue() * 100);
        }
    }
}
