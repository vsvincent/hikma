package hikma.reminder.api;

import kong.unirest.Unirest;
import hikma.reminder.api.Configuration;

public class Access {
    public void getTimingsByAddress(String address){
        Unirest.get("%s%s%s".formatted(
                Configuration.baseUrl,
                Configuration.byAddress,
                Configuration.addQueryParameter("address", address,true)));
    }
    public void getTimingsByCity(String country, String city){
        Unirest.get("%s%s%s%s".formatted(
                Configuration.baseUrl,
                Configuration.byAddress,
                Configuration.addQueryParameter("address", city,true),
                Configuration.addQueryParameter("address", country,false)));
    }
}
