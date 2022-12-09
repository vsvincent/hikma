package hikma.reminder.prayer;

import java.time.ZonedDateTime;
import java.time.temporal.Temporal;

public interface BasePrayerTime {
    Prayer getPrayer();
    ZonedDateTime getTime();
}
