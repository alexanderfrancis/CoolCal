package sample;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {

    public boolean sendEmailTo(String recipient) throws MessagingException {

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
            m.setText("Hey, you have an event starting soon!");
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        //send message
        Transport.send(m);
        System.out.println("Email sent.");

        return true;
    }


    public static void main(String [] args) throws MessagingException {
        Mail m = new Mail();
        m.sendEmailTo("reallycoolcal@gmail.com");
    }



}
