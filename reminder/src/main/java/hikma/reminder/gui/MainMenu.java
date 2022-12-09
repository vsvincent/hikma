package hikma.reminder.gui;

import hikma.reminder.api.Access;
import hikma.reminder.api.BaseAccess;
import hikma.reminder.prayer.BaseTiming;
import hikma.reminder.prayer.PrayerTimer;
import hikma.reminder.prayer.Timer;
import hikma.reminder.prayer.Timing;

import java.awt.*;
import java.awt.event.*;

public class MainMenu {
        private Frame frame;
        private Panel panel;
        private Label headerLabel;
        private Label prayerTimerHeaderLabel;
        private Label prayerTimerLabel;
        private Label statusLabel;
        private BaseTiming prayerTiming;
        static final BaseAccess access = new Access();
        static final String FRAME_TITLE = "Hikma Religious Reminder";
        static final String PRAYER_TIMER_HEADER = "Time until Prayer: ";

   public MainMenu(){
            initializeGui();
            displayNavbar();
   }

        private void initializeGui(){
            frame = new Frame(FRAME_TITLE);
            frame.setSize(400,400);
            frame.setLayout(new GridLayout(3, 1));
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent){
                    System.exit(0);
                }
            });
            headerLabel = new Label();
            headerLabel.setAlignment(Label.CENTER);
            prayerTimerHeaderLabel = new Label();
            prayerTimerHeaderLabel.setBounds(50, 100, 100, 30);
            prayerTimerHeaderLabel.setText(PRAYER_TIMER_HEADER);
            prayerTimerLabel = new Label();
            prayerTimerLabel.setBounds(150, 100, 100, 30);
            statusLabel = new Label();
            statusLabel.setAlignment(Label.CENTER);
            statusLabel.setSize(350,100);

            panel = new Panel();
            panel.setLayout(new FlowLayout());

            frame.add(prayerTimerHeaderLabel);
            frame.add(prayerTimerLabel);
            frame.setLayout(null);
            frame.setVisible(true);
        }

        private void displayNavbar(){
            final MenuBar menuBar = new MenuBar();

            Menu prayerMenu = new Menu("Prayer");
            Menu fastingMenu = new Menu("Fasting");
            final Menu helpMenu = new Menu("Help");

            MenuItem timingMenuItem = new MenuItem("Timings");
            timingMenuItem.setActionCommand("Timings");

            MenuItem prayerHistoryMenuItem = new MenuItem("History");
           prayerHistoryMenuItem.setActionCommand("History");

            final CheckboxMenuItem showPrayerTimer =
                    new CheckboxMenuItem("Show Prayer timer", true);
            prayerTiming = new Timing(access, "Franz-Mehring Platz 3");
            Timer timer = new PrayerTimer(prayerTiming.getNextPrayer(), prayerTimerLabel, "Time to pray");
            timer.getTimerThread().start();

            showPrayerTimer.addItemListener(e -> {
                if(showPrayerTimer.getState()){
                    frame.add(prayerTimerLabel);
                }else{
                    frame.remove(prayerTimerLabel);
                }
            });


            prayerMenu.add(timingMenuItem);
            prayerMenu.add(prayerHistoryMenuItem);
            prayerMenu.addSeparator();
            prayerMenu.add(showPrayerTimer);

            menuBar.add(prayerMenu);
            menuBar.add(fastingMenu);
            menuBar.add(helpMenu);

            frame.setMenuBar(menuBar);
            frame.setVisible(true);
        }


}
