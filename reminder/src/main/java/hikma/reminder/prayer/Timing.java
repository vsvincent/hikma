package hikma.reminder.prayer;

import hikma.reminder.util.JsonHelper;
import hikma.reminder.util.TimeHelper;
import kong.unirest.json.JSONObject;

import java.time.Duration;
import java.time.ZonedDateTime;

import static hikma.reminder.api.Access.*;

public class Timing {
    private PrayerTime fajr;
    private PrayerTime dhuhr;
    private PrayerTime asr;
    private PrayerTime maghrib;
    private PrayerTime isha;

    public Timing(String address){
        assignTimingsFromJson(getTimingsByAddress(address));
    }
    public Timing(String country, String city){
        assignTimingsFromJson(getTimingsByCity(country, city));
    }

    private void assignTimingsFromJson(JSONObject timings){
        fajr = new PrayerTime(Prayer.FAJR, JsonHelper.getZonedDateTimeFromEnumAndTimings(timings, Prayer.FAJR));
        dhuhr = new PrayerTime(Prayer.DHUHR, JsonHelper.getZonedDateTimeFromEnumAndTimings(timings, Prayer.DHUHR));
        asr = new PrayerTime(Prayer.ASR, JsonHelper.getZonedDateTimeFromEnumAndTimings(timings, Prayer.ASR));
        maghrib = new PrayerTime(Prayer.MAGHRIB, JsonHelper.getZonedDateTimeFromEnumAndTimings(timings, Prayer.MAGHRIB));
        isha = new PrayerTime(Prayer.ISHA, JsonHelper.getZonedDateTimeFromEnumAndTimings(timings, Prayer.ISHA));
    }

    public PrayerTime getNextPrayer()
    {
        ZonedDateTime currentTime = ZonedDateTime.now();
        PrayerTime[] prayerTimes = getAllPrayers();
        boolean allDurationsNegative = true;
        //Establish parallel duration time array
        Duration[] durations = new Duration[prayerTimes.length];
        for (int i = 0; i < prayerTimes.length; i++) {
            durations[i] = Duration.between(currentTime, prayerTimes[i].getZonedDateTime());
            if (!durations[i].isNegative())
            {
                allDurationsNegative = false;
            }
        }
        //TODO get fajr prayer of the following day
        if (allDurationsNegative)
        {
            return null;
        }
        return prayerTimes[TimeHelper.getLowestDuration(durations)];
    }
    public PrayerTime[] getAllPrayers(){
        return new PrayerTime[]{
                fajr,
                dhuhr,
                asr,
                maghrib,
                isha
        };
    }
    public PrayerTime getFajr() {
        return fajr;
    }

    public PrayerTime getDhuhr() {
        return dhuhr;
    }

    public PrayerTime getAsr() {
        return asr;
    }

    public PrayerTime getMaghrib() {
        return maghrib;
    }

    public PrayerTime getIsha() {
        return isha;
    }
}
