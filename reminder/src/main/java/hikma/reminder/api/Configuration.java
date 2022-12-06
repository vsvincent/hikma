package hikma.reminder.api;

public class Configuration {
    private static String baseUrl = "http://api.aladhan.com/v1/";
    public String getBaseUrl() {
        return baseUrl;
    }
    private static String byAddress = "timingsByAddress/:date_or_timestamp";
    public String getByAddress() {
        return byAddress;
    }
    private static String byCity = "timingsByCity/:date_or_timestamp";
    public String getByCity() {
        return byCity;
    }
}
