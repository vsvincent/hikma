package hikma.reminder.api;

public class Configuration {
    static final String baseUrl = "http://api.aladhan.com/v1/";
    static final String byAddress = "timingsByAddress";
    static final String byCity = "timingsByCity";
    public static String addQueryParameter(String parameterName, String parameterValue, Boolean firstParameter){
        if(firstParameter){
            return "?" + parameterName + "=" + parameterValue;
        }
        return "&" + parameterName + "=" + parameterValue;
    }

}
