package hikma.reminder.prayer;

import java.time.ZonedDateTime;

public class PrayerTime {
    private Prayer prayer;
    private ZonedDateTime zonedDateTime;
    public PrayerTime(Prayer prayer, ZonedDateTime zonedDateTime){
        this.prayer = prayer;
        this.zonedDateTime = zonedDateTime;
    }

    public Prayer getPrayer() {
        return prayer;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
