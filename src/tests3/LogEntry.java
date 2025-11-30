package tests3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {
    private final String ipAddress;
    private final LocalDateTime timestamp;
    private final HttpMethod httpMethod;
    private final String requestPath;
    private final int statusCode;
    private final long responseSize;
    private final String referer;
    private final UserAgent userAgent;

    public LogEntry(String logLine) {
        String regex = "^([\\d.]+) - - \\[([^\\]]+)\\] \"([A-Z]+) ([^\"]+)\" (\\d{3}) (\\d+) \"([^\"]*)\" \"([^\"]*)\"$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(logLine);

        if (matcher.matches()) {
            this.ipAddress = matcher.group(1);
            this.timestamp = parseTimestamp(matcher.group(2));
            this.httpMethod = parseHttpMethod(matcher.group(3));
            this.requestPath = matcher.group(4);
            this.statusCode = Integer.parseInt(matcher.group(5));
            this.responseSize = Long.parseLong(matcher.group(6));
            this.referer = matcher.group(7).isEmpty() ? null : matcher.group(7);
            this.userAgent = new UserAgent(matcher.group(8));
        } else {
            throw new IllegalArgumentException("Invalid log line format: " + logLine);
        }
    }

    private LocalDateTime parseTimestamp(String timestampStr) {
        // Формат: 25/Sep/2022:06:25:04 +0300
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        return LocalDateTime.parse(timestampStr, formatter);
    }

    private HttpMethod parseHttpMethod(String methodStr) {
        try {
            return HttpMethod.valueOf(methodStr);
        } catch (IllegalArgumentException e) {
            return HttpMethod.GET; // или выбросить исключение
        }
    }

    // Геттеры
    public final String getIpAddress() {
        return ipAddress;
    }

    public final LocalDateTime getTimestamp() {
        return timestamp;
    }

    public final HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public final String getRequestPath() {
        return requestPath;
    }

    public final int getStatusCode() {
        return statusCode;
    }

    public final long getResponseSize() {
        return responseSize;
    }

    public final String getReferer() {
        return referer;
    }

    public final UserAgent getUserAgent() {
        return userAgent;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "ipAddress='" + ipAddress + '\'' +
                ", timestamp=" + timestamp +
                ", httpMethod=" + httpMethod +
                ", requestPath='" + requestPath + '\'' +
                ", statusCode=" + statusCode +
                ", responseSize=" + responseSize +
                ", referer='" + referer + '\'' +
                ", userAgent=" + userAgent +
                '}';
    }
}