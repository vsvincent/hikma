package hikma.reminder.prayer;

import java.time.ZonedDateTime;

public class PrayerTime implements BasePrayerTime{
    private Prayer prayer;
    private ZonedDateTime zonedDateTime;
    public PrayerTime(Prayer prayer, ZonedDateTime zonedDateTime){
        this.prayer = prayer;
        this.zonedDateTime = zonedDateTime;
    }

    public Prayer getPrayer() {
        return prayer;
    }

    public ZonedDateTime getTime() {
        return zonedDateTime;
    }
}
