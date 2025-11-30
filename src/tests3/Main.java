package tests3;

import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        String logLine = "37.231.123.209 - - [25/Sep/2022:06:25:04 +0300] \"GET /engine.php?rss=1&json=1&p=156&lg=1 HTTP/1.0\" 200 61096 \"https://nova-news.ru/search/?rss=1&lg=1\" \"Mozilla/5.0 (Linux; Android 6.0.1; SM-J500M Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.91 Mobile Safari/537.36\"";

        try {
            LogEntry entry = new LogEntry(logLine);
            Statistics stats = new Statistics();
            stats.addEntry(entry);

            // Форматирование даты для красивого вывода
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

            System.out.println("=== Parsed Log Entry ===");
            System.out.println("IP-адрес: " + entry.getIpAddress());
            System.out.println("Дата/время: " + entry.getTimestamp().format(formatter));
            System.out.println("HTTP-метод: " + entry.getHttpMethod());
            System.out.println("Путь запроса: " + entry.getRequestPath());
            System.out.println("Код ответа: " + entry.getStatusCode());
            System.out.println("Размер ответа: " + entry.getResponseSize() + " байт");
            System.out.println("Referer: " + (entry.getReferer() != null ? entry.getReferer() : "(отсутствует)"));
            System.out.println("ОС: " + entry.getUserAgent().getOsType());
            System.out.println("Браузер: " + entry.getUserAgent().getBrowserType());
            System.out.println("Средний трафик в час: " + stats.getTrafficRate() + " байт/час");
            System.out.println("========================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}