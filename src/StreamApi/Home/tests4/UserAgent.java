package StreamApi.Home.tests4;

class UserAgent {
    private final String userAgentString;

    public UserAgent(String userAgentString) {
        this.userAgentString = userAgentString;
    }

    public boolean isBot() {
        if (userAgentString == null) {
            return true;
        }
        return userAgentString.toLowerCase().contains("bot");
    }

    @Override
    public String toString() {
        return userAgentString;
    }
}
