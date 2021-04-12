package sample;

import java.sql.Timestamp;
import java.util.Date;

public class MailInfo {
    String firstName;
    String lastName;
    String email;
    Timestamp registeredAt;
    String title;
    String url;
    String description;
    Integer notified;
    Integer Id;

    public MailInfo (String firstName, String lastName, String email, Timestamp registeredAt, String title, String url, String description, Integer notified, Integer Id) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registeredAt = registeredAt;
        this.title = title;
        this.url = url;
        this.description = description;
        this.notified = notified;
        this.Id = Id;
    }

    public String getfirstName() { return firstName; }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() { return lastName; }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public String getemail() { return email; }

    public void setemail(String email) {
        this.email = email;
    }

    public Timestamp getregisteredAt() { return registeredAt; }

    public void setRegisteredAt(Timestamp registeredAt) { this.registeredAt = registeredAt; }

    public String gettitle() { return title; }

    public void settitle(String title) {
        this.title = title;
    }

    public String geturl() { return url; }

    public void seturl(String url) {
        this.url = url;
    }

    public String getdescription() { return description; }

    public void setdescription(String description) {
        this.description = description;
    }

    public Integer getnotified() { return notified; }

    public void setnotified(Integer notified) {
        this.notified = notified;
    }

    public Integer getId() { return Id; }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public void printMailInfo(){
        System.out.println("Name                   :      " + this.getfirstName() + " " + this.getlastName());
        System.out.println("Email                  :      " + this.getemail());
        System.out.println("Registration Date      :      " + this.getregisteredAt());
        System.out.println("Title      :      " + this.gettitle());
        System.out.println("url      :      " + this.geturl());
        System.out.println("Description      :      " + this.getdescription());
        System.out.println("notified      :      " + this.getnotified());

    }

    public static void main(String[] args) {
        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());

        MailInfo m = new MailInfo("Paul", "Blart",
                "segwayspeeddemon@fake.io", ts, "test", ".com","test", 0, 1);
        m.printMailInfo();
    }
}
