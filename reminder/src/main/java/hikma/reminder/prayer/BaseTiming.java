package hikma.reminder.prayer;

public interface BaseTiming {
    BasePrayerTime getNextPrayer();
    BasePrayerTime[] getAllPrayers();
}
