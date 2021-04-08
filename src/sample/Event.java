package sample;

import java.sql.Timestamp;

public class Event {

    Integer userID;
    Timestamp date;
    Timestamp triggeredAt;

    String title;
    String description;
    String URL;
    Integer recurring;
    Integer id;

    public Event(Integer userID,String title, Timestamp date, Integer type,String description,String URL,Timestamp triggeredAt,Integer id) {
        this.title = title;
        this.date = date;
        this.id=id;

        this.userID=userID;
        this.URL=URL;
        this.triggeredAt=triggeredAt;
        this.description=description;
        this.recurring=type;


    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getRecurring() {
        return recurring;
    }

    public void setRecurring(Integer recurring) {
        this.recurring = recurring;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description=description; }

    public String getURL() { return URL; }

    public void setURL(String URL) { this.URL=URL; }

    public Timestamp getTriggeredAt() { return triggeredAt; }

    public void setTriggeredAt(Timestamp triggeredAt) { this.triggeredAt=triggeredAt; }

    public Integer getUserId() { return userID; }

    public void setUserId(Integer userId) { this.userID=userId; }
    public Integer getId() { return id; }

    public void setId(Integer Id) { this.id=Id; }

    public void printEvent(){
        System.out.println("Event Title        :    " + this.getTitle());
        System.out.println("Date              :    " + this.getDate());
        System.out.println("Recurring type        :    " + this.getRecurring());
        System.out.println("Triggered At      :    " + this.getTriggeredAt());
        System.out.println("Description      :    " + this.getDescription());

    }

    public static void main(String[] args) {
//        Event e = new Event("Boba", "March 16, 2021", true);

//        e.printEvent();
    }
}
