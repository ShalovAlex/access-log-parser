package tests4.tests4_Collections2;

import tests4.tests4_Collections2.LogEntry.*;
import tests4.tests4_Collections2.Statistics.*;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics();

        stats.addEntry(new LogEntry("/page1.html", 200, "Chrome"));
        stats.addEntry(new LogEntry("/page2.html", 404, "Firefox"));
        stats.addEntry(new LogEntry("/page3.html", 200, "Chrome"));
        stats.addEntry(new LogEntry("/page4.html", 200, "Safari"));
        stats.addEntry(new LogEntry("/page5.html", 404, "Chrome"));
        stats.addEntry(new LogEntry("/page6.html", 200, "Firefox"));

        System.out.println("Общее количество записей: " + Statistics.getTotalEntries());
        System.out.println("Несуществующие страницы: " + Statistics.getNotFoundPages());

        Map<String, Double> browserStats = Statistics.getBrowserStatistics();

        System.out.println("\nСтатистика браузеров (доли):");
        for (Map.Entry<String, Double> entry : browserStats.entrySet()) {
            System.out.printf("%s: %.3f (%.1f%%)\n",
                    entry.getKey(),
                    entry.getValue(),
                    entry.getValue() * 100);
        }
    }
}