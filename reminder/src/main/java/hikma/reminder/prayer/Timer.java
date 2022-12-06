package hikma.reminder.prayer;

import java.awt.*;
import java.time.ZonedDateTime;


public class Timer {
    private ZonedDateTime countdownTo;
    private Label label;
    private String timeUpMessage;
    public Timer (ZonedDateTime countdownTo, Label label, String timeUpMessage){
        this.countdownTo = countdownTo;
        this.label = label;
        this.timeUpMessage = timeUpMessage;
    }
    private Thread timerThread = new Thread(new Runnable() {
        public void run() {
            printCountdown();
            timeUpNotification();
        }});

    public Thread getTimerThread() {
        return timerThread;
    }

    private void printCountdown(){
        int charsWritten = 0;
        ZonedDateTime currentTime = ZonedDateTime.now();
        while (countdownTo.isAfter(currentTime.minusSeconds(1))) {
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                break;
            }
            long remainingTime = countdownTo.toEpochSecond() - currentTime.toEpochSecond();

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
