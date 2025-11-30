package tests3;

public class UserAgent {
    private final String osType;
    private final String browserType;

    public UserAgent(String userAgentString) {
        this.osType = parseOperatingSystem(userAgentString);
        this.browserType = parseBrowser(userAgentString);
    }

    private String parseOperatingSystem(String userAgentString) {
        if (userAgentString == null || userAgentString.isEmpty()) {
            return "Unknown";
        }

        String ua = userAgentString.toLowerCase();

        if (ua.contains("windows")) {
            return "Windows";
        } else if (ua.contains("mac os")) {
            return "macOS";
        } else if (ua.contains("linux")) {
            return "Linux";
        } else {
            return "Other";
        }
    }

    private String parseBrowser(String userAgentString) {
        if (userAgentString == null || userAgentString.isEmpty()) {
            return "Unknown";
        }

        String ua = userAgentString.toLowerCase();

        if (ua.contains("edg/") || ua.contains("edge/")) {
            return "Edge";
        } else if (ua.contains("firefox") || ua.contains("fxios")) {
            return "Firefox";
        } else if (ua.contains("chrome") && !ua.contains("edg/") && !ua.contains("edge/")) {
            return "Chrome";
        } else if (ua.contains("opera") || ua.contains("presto")) {
            return "Opera";
        } else {
            return "Other";
        }
    }

    // Геттеры
    public String getOsType() {
        return osType;
    }

    public String getBrowserType() {
        return browserType;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "osType='" + osType + '\'' +
                ", browserType='" + browserType + '\'' +
                '}';
    }
}