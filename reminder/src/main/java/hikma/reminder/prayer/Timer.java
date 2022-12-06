package hikma.reminder.prayer;

import java.time.ZonedDateTime;
import java.util.Scanner;

import static hikma.reminder.console.Input.getUserInputExitKey;

public class Timer {
    private ZonedDateTime countdownTo;
    public Timer (ZonedDateTime countdownTo){
        this.countdownTo = countdownTo;
    }
    private Thread timerThread = new Thread(new Runnable() {
        public void run() {
            printCountdown(countdownTo);
        }});

    public Thread getTimerThread() {
        return timerThread;
    }

    public void printCountdown(ZonedDateTime countdownTo){
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

            String writeThis = hours + ":" + minutes + ":" + seconds;

            for (int i = 0; i < charsWritten; i++) {
                System.out.print("\b");
            }
            System.out.println(writeThis);
            charsWritten = writeThis.length();
            currentTime = ZonedDateTime.now();
        }
    }
}
