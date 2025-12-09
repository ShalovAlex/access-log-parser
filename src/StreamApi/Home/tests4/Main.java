package StreamApi.Home.tests4;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics();
        long currentTime = System.currentTimeMillis();
        stats.addVisit(currentTime,
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36",
                "https://google.com/search",
                "192.168.1.100");
        stats.addVisit(currentTime + 1000,
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36",
                "https://google.com/search?q=next",
                "192.168.1.100");
        stats.addVisit(currentTime + 2000,
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36",
                null,
                "192.168.1.100");
        stats.addVisit(currentTime + 1500,
                "Chrome/91.0.4472.124 Safari/537.36",
                "https://yandex.ru/search",
                "192.168.1.101");
        stats.addVisit(currentTime + 500,
                "Firefox/89.0",
                "https://nova-news.ru/wp-login.php",
                "192.168.1.102");
        stats.addVisit(currentTime + 2500,
                "Firefox/89.0",
                "https://github.com",
                "192.168.1.102");
        stats.addVisit(currentTime,
                "Googlebot/2.1 (+http://www.google.com/bot.html)",
                "https://example.com",
                "192.168.1.103");
        System.out.println("Пиковая посещаемость в секунду: " +
                stats.getPeakVisitsPerSecond() + " посещений/сек");
        System.out.println("Максимальная посещаемость одним пользователем: " +
                stats.getMaxVisitsBySingleUser() + " посещений");
        Set<String> refererDomains = stats.getRefererDomains();
        for (String domain : refererDomains) {
            System.out.println("- " + domain);
        }
    }
}