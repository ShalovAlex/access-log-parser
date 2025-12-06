package tests4.tests4_Collections2;

import java.util.*;
import java.util.stream.Collectors;

public class Statistics {
    private static Set<String> notFoundPages = new HashSet<>();
    private static Map<String, Integer> browserCounts = new HashMap<>();
    private static int totalEntries = 0;
    public void addEntry(LogEntry entry) {
        totalEntries++;
        if (entry.getResponseCode() == 404) {
            notFoundPages.add(entry.getUrl());
        }
        String browser = entry.getBrowser();
        browserCounts.put(browser, browserCounts.getOrDefault(browser, 0) + 1);
    }

    public static List<String> getNotFoundPages() {
        return notFoundPages.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static Map<String, Double> getBrowserStatistics() {
        Map<String, Double> browserStats = new HashMap<>();
        if (totalEntries == 0) {
            return browserStats;
        }
        for (Map.Entry<String, Integer> entry : browserCounts.entrySet()) {
            String browser = entry.getKey();
            int count = entry.getValue();
            double proportion = (double) count / totalEntries;
            browserStats.put(browser, proportion);
        }
        return browserStats;
    }

    public static int getTotalEntries() {
        return totalEntries;
    }
}