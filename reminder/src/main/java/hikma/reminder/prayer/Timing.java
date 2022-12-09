package hikma.reminder.prayer;

import hikma.reminder.api.BaseAccess;
import hikma.reminder.util.JsonHelper;
import hikma.reminder.util.TimeHelper;
import kong.unirest.json.JSONObject;

import java.time.Duration;
import java.time.ZonedDateTime;

import static hikma.reminder.api.Access.*;

public class Timing implements BaseTiming{
    private BasePrayerTime fajr;
    private BasePrayerTime dhuhr;
    private BasePrayerTime asr;
    private BasePrayerTime maghrib;
    private BasePrayerTime isha;

    private BaseAccess access;

    public Timing(BaseAccess access, String address){
        this.access = access;
        assignTimingsFromJson(access.getTimingsByAddress(address));
    }
    public Timing(String country, String city){
        assignTimingsFromJson(access.getTimingsByCity(country, city));
    }

    private void assignTimingsFromJson(JSONObject timings){
        fajr = new PrayerTime(Prayer.FAJR, JsonHelper.getZonedDateTimeFromEnumAndTimings(timings, Prayer.FAJR));
        dhuhr = new PrayerTime(Prayer.DHUHR, JsonHelper.getZonedDateTimeFromEnumAndTimings(timings, Prayer.DHUHR));
        asr = new PrayerTime(Prayer.ASR, JsonHelper.getZonedDateTimeFromEnumAndTimings(timings, Prayer.ASR));
        maghrib = new PrayerTime(Prayer.MAGHRIB, JsonHelper.getZonedDateTimeFromEnumAndTimings(timings, Prayer.MAGHRIB));
        isha = new PrayerTime(Prayer.ISHA, JsonHelper.getZonedDateTimeFromEnumAndTimings(timings, Prayer.ISHA));
    }

    public BasePrayerTime getNextPrayer()
    {
        ZonedDateTime currentTime = ZonedDateTime.now();
        BasePrayerTime[] prayerTimes = getAllPrayers();
        boolean allDurationsNegative = true;
        //Establish parallel duration time array
        Duration[] durations = new Duration[prayerTimes.length];
        for (int i = 0; i < prayerTimes.length; i++) {
            durations[i] = Duration.between(currentTime, prayerTimes[i].getTime());
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
    public BasePrayerTime[] getAllPrayers(){
        return new BasePrayerTime[]{
                fajr,
                dhuhr,
                asr,
                maghrib,
                isha
        };
    }
    public BasePrayerTime getFajr() {
        return fajr;
    }

    public BasePrayerTime getDhuhr() {
        return dhuhr;
    }

    public BasePrayerTime getAsr() {
        return asr;
    }

    public BasePrayerTime getMaghrib() {
        return maghrib;
    }

    public BasePrayerTime getIsha() {
        return isha;
    }
}
