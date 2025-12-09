package StreamApi.Home.tests3;

class UserAgent {
    private final String userAgentString;

    public UserAgent(String userAgentString) {
        this.userAgentString = userAgentString != null ? userAgentString : "";
    }

    public boolean isBot() {
        return userAgentString.toLowerCase().contains("bot");
    }
}