package hikma.reminder.util;

import hikma.reminder.api.Configuration;
import kong.unirest.HttpRequest;
import kong.unirest.json.JSONObject;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class JsonHelper {
    public static JSONObject getJson(HttpRequest httpRequest){
        return new JSONObject(httpRequest.asJson().getBody().toString());
    }
    public static ZonedDateTime getZonedDateTimeFromEnumAndTimings(JSONObject timings,Enum enumerator){
        LocalTime time = LocalTime.parse(timings
                .getJSONObject(Configuration.JSON_TIMING_KEY)
                .getString(EnumHelper
                        .getPascalCaseString(enumerator)));
        String timezone = timings.getJSONObject(Configuration.JSON_META_KEY)
                .getString(Configuration.JSON_TIMEZONE_KEY);
        ZoneId zoneId = ZoneId.of(timezone);
        JSONObject gregorianJson = timings.getJSONObject(Configuration.JSON_DATE_KEY)
                .getJSONObject(Configuration.JSON_GREGORIAN_KEY);
        LocalDate date = LocalDate.parse(
                gregorianJson
                        .getString(Configuration.JSON_DATE_KEY), DateTimeFormatter.ofPattern(Configuration.DATE_FORMAT));
        LocalDateTime localDateTime = time.atDate(date);
        return localDateTime.atZone(zoneId);
    }
}
