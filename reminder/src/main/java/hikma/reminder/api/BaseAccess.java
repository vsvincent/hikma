package hikma.reminder.api;

import kong.unirest.json.JSONObject;

public interface BaseAccess {
    JSONObject getTimingsByAddress(String address);
    JSONObject getTimingsByCity(String country, String city);
}
