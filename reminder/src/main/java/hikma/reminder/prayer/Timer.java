package hikma.reminder.prayer;

import java.awt.*;
import java.time.ZonedDateTime;


public class Timer {
    //TODO replace with generic timer class using zoneddatetime instead of prayer time. Have PrayerTimer class extend.
    private PrayerTime prayerTime;
    private Label label;
    private String timeUpMessage;
    public Timer (PrayerTime prayerTime, Label label, String timeUpMessage){
        this.prayerTime = prayerTime;
        this.label = label;
        this.timeUpMessage = timeUpMessage;
    }
    private Thread timerThread = new Thread(() -> {
        printCountdown();
        timeUpNotification();
    });
    public Thread getTimerThread() {
        return timerThread;
    }

    private void printCountdown(){
        //TODO instead get next day's morning prayer
        if (prayerTime == null){
            label.setText("All of today's prayers are over");
            return;
        }
        int charsWritten = 0;
        ZonedDateTime currentTime = ZonedDateTime.now();
        //TODO replace with generice zoneddatetime not from prayertime
        while (prayerTime.getZonedDateTime().isAfter(currentTime.minusSeconds(1))) {
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                break;
            }
            //TODO replace with generice zoneddatetime not from prayertime
            long remainingTime = prayerTime.getZonedDateTime().toEpochSecond() - currentTime.toEpochSecond();

            String seconds = Integer.toString((int) (remainingTime % 60));
            String minutes = Integer.toString((int) ((remainingTime % 3600) / 60));
            String hours = Integer.toString((int) (remainingTime / 3600));

            if (seconds.length() < 2) {
                seconds = "0" + seconds;
            }

            if (minutes.length() < 2) {
                minutes = "0" + minutes;
            }

            if (hours.length() < 2) {
                hours = "0" + hours;
            }

            String countdown = hours + ":" + minutes + ":" + seconds;

            label.setText(countdown);
            charsWritten = countdown.length();
            currentTime = ZonedDateTime.now();
        }
    }
    private void timeUpNotification() {
        label.setText(timeUpMessage);
        label.setForeground(Color.GREEN);
    }
}
