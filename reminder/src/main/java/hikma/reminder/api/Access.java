package hikma.reminder.api;

import kong.unirest.*;
import kong.unirest.json.JSONObject;
public class Access {
    public static JSONObject getJson(HttpRequest httpRequest){
        return new JSONObject(httpRequest.asJson().getBody().toString());
    }
    public static JSONObject getTimingsByAddress(String address){
        return getJson(
                Unirest.get("%s%s%s".formatted(
                Configuration.baseUrl,
                Configuration.byAddress,
                Configuration.addQueryParameter("address", address,true))))
                .getJSONObject(Configuration.JSON_DATA_KEY);
    }
    public static JSONObject getTimingsByCity(String country, String city){
        return getJson(
                Unirest.get("%s%s%s%s".formatted(
                Configuration.baseUrl,
                Configuration.byAddress,
                Configuration.addQueryParameter("address", city,true),
                Configuration.addQueryParameter("address", country,false))))
                .getJSONObject(Configuration.JSON_DATA_KEY);
    }
}
