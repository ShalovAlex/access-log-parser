package tests2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class LogParser {
    public void parseLogLine(String logLine) {
        String regex = "^([\\d.]+) - - \\[([^\\]]+)\\] \"([A-Z]+) " +
                "([^\"]+)\" (\\d{3}) (\\d+) \"([^\"]*)\" \"([^\"]*)\"$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.matches()) {
            String ipAddress = matcher.group(1);
            String timestamp = matcher.group(2);
            String httpMethod = matcher.group(3);
            String requestPath = matcher.group(4);
            String statusCode = matcher.group(5);
            String responseSize = matcher.group(6);
            String referer = matcher.group(7);
            String userAgent = matcher.group(8);

            System.out.println("IP-адрес: " + ipAddress);
            System.out.println("Дата/время: " + timestamp);
            System.out.println("HTTP-метод: " + httpMethod);
            System.out.println("Путь запроса: " + requestPath);
            System.out.println("Код ответа: " + statusCode);
            System.out.println("Размер ответа: " + responseSize + " байт");
            System.out.println("Referer: " + (referer.isEmpty() ? "(пусто)" : referer));
            System.out.println("User-Agent: " + userAgent);
            System.out.println("---");
        } else {
            System.out.println("Строка не соответствует ожидаемому формату: " + logLine);
        }
    }

    public void parseLogFile(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                int length = line.length();

                if (length > 1024) {
                    throw new TooLongLineException("Обнаружена строка длинной " + length + " " + (lineCount + 1) +
                            ". Максимально допустимая длина: 1024 символа.");
                }

                lineCount++;
                parseLogLine(line);
            }

            System.out.println("Общее количество строк в файле: " + lineCount);
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
class TooLongLineException extends RuntimeException {
    public TooLongLineException(String message) {
        super(message);
    }

    public static void main(String[] args) {
        LogParser parser = new LogParser();
        parser.parseLogFile("/Users/alex/Desktop/_git/AccessLogParser/src/tests2/test");
    }
}