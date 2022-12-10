package hikma.reminder.util;

import hikma.reminder.prayer.Prayer;

import java.time.Duration;
import java.time.ZonedDateTime;

public class TimeHelper implements BaseTimeHelper {
    //Exists to improve the testability of the Timing class
    public ZonedDateTime getCurrentTime(){
        return ZonedDateTime.now();
    }

    public static int getLowestDuration(Duration[] durations){
        if (durations.length == 0)
        {
            throw new IllegalArgumentException();
        }
        int lowestDurationIndex = -1;
        for (int i = 0; i < durations.length; i++) {
            if(lowestDurationIndex == -1
                    || durations[lowestDurationIndex].isNegative()
                    || durations[i].compareTo(durations[lowestDurationIndex]) < 0)
            {
                lowestDurationIndex = i;
            }
        }
        return lowestDurationIndex;
    }
}
