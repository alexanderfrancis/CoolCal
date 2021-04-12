package sample;

import java.sql.Timestamp;

public class MultiEvent {

    String title;
    Integer eventID;
    String username;
    Integer userID;
    String description;
    Timestamp scheduledAt;
    String url;
    String firstName;
    String lastName;
    String email;
    int notified;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNotified() {
        return notified;
    }

    public void setNotified(int notified) {
        this.notified = notified;
    }

    public MultiEvent(Integer userID, String title, String username, Integer eventID, String description, Timestamp scheduledAt, String url, String firstName, String lastName, String email, int notified) {
        this.title = title;
        this.eventID = eventID;
        this.userID = userID;
        this.username = username;
        this.description = description;
        this.scheduledAt = scheduledAt;
        this.url = url;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.notified = notified;
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

    public Timestamp getScheduledAt() { return scheduledAt; }

    public void setScheduledAt(Timestamp getScheduledAt) { this.scheduledAt=scheduledAt; }

    public String getUrls() { return url; }

    public void setUrls(String getUrls) { this.url=url; }

    public void printEvent(){
        System.out.println("Event Title        :    " + this.getTitle());
        System.out.println("User ID              :    " + this.getUserID());
        System.out.println("Username        :    " + this.getUsername());
        System.out.println("Triggered At      :    " + this.getEventID());
    }

    public static void main(String[] args) {
    }
}
