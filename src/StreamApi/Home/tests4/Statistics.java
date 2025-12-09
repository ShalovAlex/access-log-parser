package StreamApi.Home.tests4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Statistics {
    private Map<Integer, Integer> visitsPerSecond;
    private Set<String> refererDomains;
    private Map<String, Integer> visitsByIp;

    public Statistics() {
        visitsPerSecond = new HashMap<>();
        refererDomains = new HashSet<>();
        visitsByIp = new HashMap<>();
    }

    public void addVisit(long timestamp, UserAgent userAgent, String referer, String ip) {
        if (userAgent == null || userAgent.isBot()) {
            return;
        }

        int second = (int)(timestamp / 1000);
        visitsPerSecond.put(second, visitsPerSecond.getOrDefault(second, 0) + 1);
        if (ip != null && !ip.trim().isEmpty()) {
            visitsByIp.put(ip, visitsByIp.getOrDefault(ip, 0) + 1);
        }

        if (referer != null && !referer.trim().isEmpty()) {
            String domain = extractDomainFromReferer(referer);
            if (domain != null) {
                refererDomains.add(domain);
            }
        }
    }
    public void addVisit(long timestamp, String userAgentString, String referer, String ip) {
        addVisit(timestamp, new UserAgent(userAgentString), referer, ip);
    }

    public int getPeakVisitsPerSecond() {
        if (visitsPerSecond.isEmpty()) {
            return 0;
        }

        int maxVisits = 0;
        for (int visits : visitsPerSecond.values()) {
            if (visits > maxVisits) {
                maxVisits = visits;
            }
        }

        return maxVisits;
    }

    public int getMaxVisitsBySingleUser() {
        if (visitsByIp.isEmpty()) {
            return 0;
        }

        int maxVisits = 0;
        for (int visits : visitsByIp.values()) {
            if (visits > maxVisits) {
                maxVisits = visits;
            }
        }

        return maxVisits;
    }

    public Set<String> getRefererDomains() {
        return new HashSet<>(refererDomains);
    }

    private String extractDomainFromReferer(String referer) {
        try {
            String withoutProtocol = referer.replaceFirst("^(https?://)", "");
            String domainPart = withoutProtocol.split("/")[0];
            domainPart = domainPart.split(":")[0];
            if (domainPart.startsWith("www.")) {
                domainPart = domainPart.substring(4);
            }

            return domainPart.trim().toLowerCase();
        } catch (Exception e) {
            return null;
        }
    }
}