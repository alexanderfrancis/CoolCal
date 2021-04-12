package sample;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {

    public static void main(String[] args) {

    }

    public void notifyUser(String recipient, String eventTitle, String url) {
        Timer t = new Timer();
        TimerTask hour = new TimerTask() {
            @Override
            public void run() {
                //check if new events are needing display

                //get client email address
                //call mail functions
            }
        };

        //run TimerTask code each hour (1000ms * 60 sec * 60 min)
        t.schedule(hour, 0L, 1000 * 60 * 60);
    }

}
