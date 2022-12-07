package hikma.reminder.util;

import hikma.reminder.prayer.Prayer;

import java.time.Duration;

public class TimeHelper {
    public static int getLowestDuration(Duration[] durations){
        if (durations.length == 0)
        {
            throw new IllegalArgumentException();
        }
        int lowestDurationIndex = -1;
        for (int i = 0; i < durations.length; i++) {
            if(lowestDurationIndex == -1 || durations[i].compareTo(durations[lowestDurationIndex]) >= 1)
            {
                lowestDurationIndex = i;
            }
        }
        return lowestDurationIndex;
    }
}
