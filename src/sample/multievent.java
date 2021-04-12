package sample;

import java.sql.Timestamp;

public class multievent {

    String title;
    Integer eventID;
    String username;
    Integer userID;
    String description;
    Timestamp scheduledAt;
    String url;

    public multievent(Integer userID,String title, String username, Integer eventID, String description, Timestamp scheduledAt, String url) {


        this.title = title;
        this.eventID=eventID;
        this.userID=userID;
        this.username=username;
        this.description=description;
        this.scheduledAt=scheduledAt;
        this.url=url;

    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserID() { return userID; }

    public void setUserID(Integer userID) { this.userID=userID; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username=username; }

    public Integer getEventID() { return eventID; }

    public void setEventID(Integer getEventID) { this.eventID=eventID; }

    public String getDescription() { return description; }

    public void setDescription(String getDescription) { this.description=description; }

    public Timestamp getscheduledAt() { return scheduledAt; }

    public void setscheduledAt(Timestamp getscheduledAt) { this.scheduledAt=scheduledAt; }

    public String geturls() { return url; }

    public void seturls(String geturls) { this.url=url; }

    public void printEvent(){
        System.out.println("Event Title        :    " + this.getTitle());
        System.out.println("User ID              :    " + this.getUserID());
        System.out.println("Username        :    " + this.getUsername());
        System.out.println("Triggered At      :    " + this.getEventID());

    }

    public static void main(String[] args) {
//        Event e = new Event("Boba", "March 16, 2021", true);

//        e.printEvent();
    }
}
