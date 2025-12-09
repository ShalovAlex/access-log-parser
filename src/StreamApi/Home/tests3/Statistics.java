package StreamApi.Home.tests3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Statistics {
    private int[] visitsUser;
    private int errorCount = 0;
    private int hoursCount = 0;
    private int totalRealVisits = 0;
    private Set<String> uniqueRealUserIPs = new HashSet<>();

    public Statistics(int[] visitsPerHour, String[] userAgents) {
        if (visitsPerHour == null || userAgents == null || visitsPerHour.length != userAgents.length) {
            visitsUser = new int[0];
            return;
        }

        hoursCount = visitsPerHour.length;
        visitsUser = new int[visitsPerHour.length];

        for (int i = 0; i < visitsPerHour.length; i++) {
            UserAgent userAgent = new UserAgent(userAgents[i]);
            if (!userAgent.isBot()) {
                visitsUser[i] = visitsPerHour[i];
            }
        }
    }

    public void addEntry(String logEntry) {
        if (logEntry == null || logEntry.isEmpty()) {
            return;
        }
        String[] parts = logEntry.split("\\s+");
        if (parts.length < 1) {
            return;
        }
        String ipAddress = parts[0];
        String userAgentString = "";
        String[] quotedParts = logEntry.split("\"");
        if (quotedParts.length >= 6) {
            userAgentString = quotedParts[quotedParts.length - 2];
        }
        UserAgent userAgent = new UserAgent(userAgentString);
        if (!userAgent.isBot()) {
            totalRealVisits++;
            uniqueRealUserIPs.add(ipAddress);
        }

        if (quotedParts.length >= 3) {
            String statusAndSize = quotedParts[2].trim();
            String[] statusParts = statusAndSize.split("\\s+");

            if (statusParts.length >= 1) {
                try {
                    int statusCode = Integer.parseInt(statusParts[0]);
                    if (statusCode >= 400 && statusCode <= 599) {
                        errorCount++;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    public double calculateAverageErrorRequestsPerHour() {
        if (hoursCount == 0) {
            return 0.0;
        }

        return (double) errorCount / hoursCount;
    }

    public double calculateAverageVisitsPerRealUser() {
        if (uniqueRealUserIPs.isEmpty()) {
            return 0.0;
        }

        return (double) totalRealVisits / uniqueRealUserIPs.size();
    }

    public static double calculateAverageVisits(int[] visitsPerHour) {
        if (visitsPerHour == null || visitsPerHour.length == 0) {
            return 0.0;
        }

        return Arrays.stream(visitsPerHour)
                .average()
                .orElse(0.0);
    }

    public double calculateAverageVisitsWithoutBots() {
        return calculateAverageVisits(visitsUser);
    }
}