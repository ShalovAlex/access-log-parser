package tests2;

import java.io.BufferedReader;
import java.io.FileReader;

class BotAnalysis {
    private int totalRequests = 0;
    private int googlebotCount = 0;
    private int yandexbotCount = 0;

    public void analyzeBots(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            while ((line = reader.readLine()) != null) {
                totalRequests++;
                analyzeUserAgent(line);
            }

            printBotStatistics();
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void analyzeUserAgent(String logLine) {
        String userAgent = extractUserAgent(logLine);
        if (userAgent != null) {
            String program = extractProgramFromUserAgent(userAgent);
            if (program != null) {
                if ("Googlebot".equals(program)) {
                    googlebotCount++;
                } else if ("YandexBot".equals(program)) {
                    yandexbotCount++;
                }
            }
        }
    }

    private String extractUserAgent(String logLine) {
        int lastQuoteStart = logLine.lastIndexOf("\"");
        int lastQuoteEnd = logLine.lastIndexOf("\"", lastQuoteStart - 1);

        if (lastQuoteStart != -1 && lastQuoteEnd != -1 && lastQuoteStart > lastQuoteEnd) {
            return logLine.substring(lastQuoteEnd + 1, lastQuoteStart);
        }
        return null;
    }

    private String extractProgramFromUserAgent(String userAgent) {
        int startBracket = userAgent.indexOf('(');
        int endBracket = userAgent.indexOf(')');

        if (startBracket != -1 && endBracket != -1 && endBracket > startBracket) {
            String firstBrackets = userAgent.substring(startBracket + 1, endBracket);

            String[] parts = firstBrackets.split(";");
            if (parts.length >= 2) {
                String fragment = parts[1].trim();

                int slashIndex = fragment.indexOf('/');
                if (slashIndex != -1) {
                    return fragment.substring(0, slashIndex).trim();
                }
                return fragment;
            }
        }
        return null;
    }

    private void printBotStatistics() {
        System.out.println("Общее количество запросов: " + totalRequests);
        System.out.println("Запросов от Googlebot: " + googlebotCount);
        System.out.println("Запросов от YandexBot: " + yandexbotCount);

        if (totalRequests > 0) {
            double googlebotPercent = (double) googlebotCount / totalRequests * 100;
            double yandexbotPercent = (double) yandexbotCount / totalRequests * 100;

            System.out.printf("Доля Googlebot: %.2f%%\n", googlebotPercent);
            System.out.printf("Доля YandexBot: %.2f%%\n", yandexbotPercent);
        }
    }
    public static void main(String[] args) {
        BotAnalysis parser = new BotAnalysis();
        parser.analyzeBots("/Users/alex/Desktop/_git/AccessLogParser/src/tests2/test");
    }
}