package tests4_Collections1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Statistics {
    private final Set<String> existingPages = new HashSet<>();
    private final Map<String, Integer> osCount = new HashMap<>();
    private int totalOsEntries = 0;

    public void addEntry(String url, int responseCode, String operatingSystem) {
        if (responseCode == 200) {
            existingPages.add(url);
        }

        if (operatingSystem != null && !operatingSystem.isEmpty()) {
            osCount.put(operatingSystem, osCount.getOrDefault(operatingSystem, 0) + 1);
            totalOsEntries++;
        }
    }

    public Set<String> getExistingPages() {
        return new HashSet<>(existingPages);
    }

    public int getExistingPagesCount() {
        return existingPages.size();
    }

    public Map<String, Double> getOsStatistics() {
        Map<String, Double> osStatistics = new HashMap<>();

        if (totalOsEntries == 0) {
            return osStatistics;
        }

        for (Map.Entry<String, Integer> entry : osCount.entrySet()) {
            double proportion = (double) entry.getValue() / totalOsEntries;
            osStatistics.put(entry.getKey(), proportion);
        }

        return osStatistics;
    }
}