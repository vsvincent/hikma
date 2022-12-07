package hikma.reminder.api;

public class Configuration {
    static final String baseUrl = "http://api.aladhan.com/v1/";
    static final String byAddress = "timingsByAddress";
    static final String byCity = "timingsByCity";
    public static final String JSON_DATA_KEY = "data";
    public static final String JSON_TIMING_KEY = "timings";
    public static final String JSON_DATE_KEY = "date";
    public static final String JSON_GREGORIAN_KEY = "gregorian";
    public static final String JSON_META_KEY = "meta";
    public static final String JSON_TIMEZONE_KEY = "timezone";
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static String addQueryParameter(String parameterName, String parameterValue, Boolean firstParameter){
        if(firstParameter){
            return "?" + parameterName + "=" + parameterValue;
        }
        return "&" + parameterName + "=" + parameterValue;
    }

}
