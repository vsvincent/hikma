package hikma.reminder;

import hikma.reminder.api.Access;
import hikma.reminder.api.BaseAccess;
import hikma.reminder.api.Configuration;
import hikma.reminder.prayer.BasePrayerTime;
import hikma.reminder.prayer.BaseTiming;
import hikma.reminder.prayer.Prayer;
import hikma.reminder.prayer.Timing;
import hikma.reminder.util.BaseTimeHelper;
import hikma.reminder.util.TimeHelper;
import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TimingTests {
    private final String fajrTime = "05:50";
    private final String dhuhrTime = "11:53";
    private final String asrTime = "13:38";
    private final String maghribTime = "15:52";
    private final String ishaTime = "17:48";
    private String validAddress = "10 Downing Street, London";
    private final BaseTimeHelper timeHelper = new TimeHelper();
    private JSONObject goodResponse;
    public TimingTests()
    {
        //Create dummy ideal response JSONObject
        goodResponse = new JSONObject();
        goodResponse.put("Fajr", fajrTime);
        goodResponse.put("Dhuhr", dhuhrTime);
        goodResponse.put("Asr", asrTime);
        goodResponse.put("Maghrib", maghribTime);
        goodResponse.put("Isha", ishaTime);
        goodResponse = new JSONObject().put(Configuration.JSON_TIMING_KEY, goodResponse);

        JSONObject meta = new JSONObject().put(Configuration.JSON_TIMEZONE_KEY, "Europe/London");
        goodResponse = goodResponse.put(Configuration.JSON_META_KEY, meta);

        JSONObject date = new JSONObject().put(Configuration.JSON_DATE_KEY, "10-12-2022");
        date = new JSONObject().put(Configuration.JSON_GREGORIAN_KEY, date);
        goodResponse = goodResponse.put(Configuration.JSON_DATE_KEY, date);
    }

    @Test
    public void Timing_InvalidAddress_ExceptionThrown()
    {
        //arrange
        String invalidAddress = "Waw大家好aw||||eewa§ÄÖÜÜÖÖ%$&";
        String badRequestResponseString = "\"Unable to locate address (even via google geocoding). It is probably invalid!\"";
        JSONObject badRequestResponse = new JSONObject().put(Configuration.JSON_DATA_KEY,badRequestResponseString);

        BaseAccess mockAccess = mock(Access.class);
        when(mockAccess.getTimingsByAddress(invalidAddress)).thenReturn(badRequestResponse);

        BaseTiming timing;
        //assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Timing(mockAccess, timeHelper, invalidAddress);
        });
    }

    @Test
    public void getAllPrayers_ValidAddress_ValidTimingsReturned()
    {
        //arrange

        BaseAccess mockAccess = mock(Access.class);
        when(mockAccess.getTimingsByAddress(validAddress)).thenReturn(goodResponse);

        BaseTiming timing = new Timing(mockAccess, timeHelper, validAddress);
        BasePrayerTime[] actualPrayerTimes;

        //act
        actualPrayerTimes = timing.getAllPrayers();

        //assert
        assertEquals(actualPrayerTimes[0].getTime().toLocalTime().toString(), fajrTime);
        assertEquals(actualPrayerTimes[1].getTime().toLocalTime().toString(), dhuhrTime);
        assertEquals(actualPrayerTimes[2].getTime().toLocalTime().toString(), asrTime);
        assertEquals(actualPrayerTimes[3].getTime().toLocalTime().toString(), maghribTime);
        assertEquals(actualPrayerTimes[4].getTime().toLocalTime().toString(), ishaTime);
    }
    @Test
    public void getNextPrayer_CurrentTimeBetweenFajrAndDhuhr_ReturnDhuhr()
    {
        //arrange
        ZonedDateTime dateTime = ZonedDateTime.parse("2022-12-10T10:30Z[Europe/London]");
        TimeHelper mockTimeHelper = mock(TimeHelper.class);

        BaseAccess mockAccess = mock(Access.class);
        when(mockAccess.getTimingsByAddress(validAddress)).thenReturn(goodResponse);

        when(mockTimeHelper.getCurrentTime()).thenReturn(dateTime);
        BaseTiming timing = new Timing(mockAccess, mockTimeHelper, validAddress);

        Prayer expectedPrayer = Prayer.DHUHR;
        Prayer actualPrayer;
        //act
        actualPrayer = timing.getNextPrayer().getPrayer();
        //assert
        assertEquals(expectedPrayer, actualPrayer);
    }
}
