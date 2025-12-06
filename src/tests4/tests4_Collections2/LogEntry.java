package tests4.tests4_Collections2;

class LogEntry {
    private String url;
    private int responseCode;
    private String browser;

    public LogEntry(String url, int responseCode, String browser) {
        this.url = url;
        this.responseCode = responseCode;
        this.browser = browser;
    }

    public String getUrl() {
        return url;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getBrowser() {
        return browser;
    }
}