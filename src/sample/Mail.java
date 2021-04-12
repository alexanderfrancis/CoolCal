package sample;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

public class Mail {

    public boolean sendEmailTo(int notified, String recipient, String firstName, String lastName, String title, String url, Timestamp time) throws MessagingException {

        if (notified == 0) {
            Properties p = new Properties();

            //gmail mail server settings
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");

            //user authentication details
            String user = "reallycoolcal@gmail.com";
            String pass = "SecretPassword";

            //verify credentials
            Session s = Session.getInstance(p, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            });

            //message creation
            Message m = new MimeMessage(s);
            try {
                m.setFrom(new InternetAddress(user));
                m.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                m.setSubject("CoolCal REMINDER");
                m.setText("Hey "+ firstName + " " + lastName + ", you have an event starting soon: | " + title + " |  at time: | " + time + " |. URL: " + url);
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }

            //send message
            Transport.send(m);
            System.out.println("Email sent.");

            return true;
        }
        else {
            return false;
        }
    }

    public void multiSend(ArrayList<MailInfo> m) throws MessagingException {
        for (MailInfo a : m) {
            sendEmailTo(a.getnotified(), a.getemail(), a.getfirstName(), a.getlastName(), a.gettitle(), a.geturl(), a.getregisteredAt());
        }
    }

    public static void main(String [] args) {
        Mail m = new Mail();
        Timestamp t = new Timestamp(0);

        //m.sendEmailTo(0, "reallycoolcal@gmail.com", "Alex", "Francis", "Wash Car", "google.ca", );
    }



}
