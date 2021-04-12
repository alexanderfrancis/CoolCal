package sample;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {

    public static void main(String[] args) {
        Scheduler s = new Scheduler();
        s.notifyUser();
    }

    public boolean hourCheck(Timestamp db) {
        //grabbing current time
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        df.format(c.getTime());
        Timestamp current = new Timestamp(c.getTimeInMillis());

        String t1 = current.toString();
        String t2 = db.toString();

        t1 = t1.substring(14, 16);
        t2 = t2.substring(14, 16);

        int a = Integer.parseInt(t1);
        int b = Integer.parseInt(t2);

        if (a - b <= 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public void notifyUser() {
        Timer t = new Timer();
        TimerTask hour = new TimerTask() {
            @Override
            public void run() {
                CalData c = new CalData();
                ArrayList<MailInfo> a = c.mail();

                Mail mail = new Mail();

                for (int i = 0; i < a.size(); i++) {
                    MailInfo m = a.get(i);
                    Timestamp time = m.getregisteredAt();

                    if (hourCheck(time)) {
                        try {
                            mail.sendEmailTo(m.getnotified(), m.getemail(), m.getfirstName(), m.getlastName(), m.gettitle(), m.geturl(), m.getregisteredAt(), m.getId());
                            c.updateEvent(null, null,  null,  null, null, null, 1, m.getId());
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        //run TimerTask code each hour (1000ms * 60 sec * 60 min)
        t.schedule(hour, 0L, 1000 * 10);
    }
}
