package StreamApi.Home.tests3;

public class Main {
    public static void main(String[] args) {
        int[] visits = {10, 20, 30, 40, 50};
        String[] userAgents = {
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64)",
                "Googlebot/2.1",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64)",
                "Bingbot/2.0"
        };

        Statistics stats = new Statistics(visits, userAgents);

        String[] logEntries = {
                "192.168.1.1 - - [10/Dec/2023:10:15:30 +0300] \"GET /index.html HTTP/1.1\" 200 1234 \"-\" \"Mozilla/5.0\"",
                "192.168.1.1 - - [10/Dec/2023:10:16:15 +0300] \"GET /about.html HTTP/1.1\" 200 567 \"-\" \"Mozilla/5.0\"",
                "192.168.1.2 - - [10/Dec/2023:10:17:45 +0300] \"GET /products HTTP/1.1\" 200 789 \"-\" \"Chrome/120.0\"",
                "192.168.1.2 - - [10/Dec/2023:10:18:20 +0300] \"GET /products/details HTTP/1.1\" 200 2345 \"-\" \"Chrome/120.0\"",
                "192.168.1.2 - - [10/Dec/2023:10:19:10 +0300] \"GET /cart HTTP/1.1\" 200 123 \"-\" \"Chrome/120.0\"",
                "192.168.1.3 - - [10/Dec/2023:10:20:05 +0300] \"GET /contact HTTP/1.1\" 200 4567 \"-\" \"Firefox/121.0\"",
                "192.168.1.100 - - [10/Dec/2023:10:21:30 +0300] \"GET /robots.txt HTTP/1.1\" 200 890 \"-\" \"Googlebot/2.1\"",
                "192.168.1.101 - - [10/Dec/2023:10:22:15 +0300] \"GET /sitemap.xml HTTP/1.1\" 200 3456 \"-\" \"Bingbot/2.0\"",
                "192.168.1.4 - - [10/Dec/2023:10:23:40 +0300] \"GET /api/data HTTP/1.1\" 404 123 \"-\" \"Safari/17.0\"",
                "192.168.1.4 - - [10/Dec/2023:10:24:25 +0300] \"GET /home HTTP/1.1\" 200 5678 \"-\" \"Safari/17.0\"",
                "192.168.1.1 - - [10/Dec/2023:10:25:10 +0300] \"GET /logout HTTP/1.1\" 200 1234 \"-\" \"Mozilla/5.0\""
        };

        for (String logEntry : logEntries) {
            stats.addEntry(logEntry);
        }

        double avgWithBots = Statistics.calculateAverageVisits(visits);
        System.out.println("Среднее количество посещений в час (включая ботов): " + avgWithBots);

        double avgWithoutBots = stats.calculateAverageVisitsWithoutBots();
        System.out.println("Среднее количество посещений в час (без ботов): " + avgWithoutBots);

        double avgErrorsPerHour = stats.calculateAverageErrorRequestsPerHour();
        System.out.println("Среднее количество ошибочных запросов в час: " + avgErrorsPerHour);

        double avgVisitsPerUser = stats.calculateAverageVisitsPerRealUser();
        System.out.println("Средняя посещаемость одним пользователем: " + String.format("%.2f", avgVisitsPerUser));
    }
}