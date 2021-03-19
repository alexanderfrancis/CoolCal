package sample;

public class Event {

    String name;
    String date;
    boolean recurring;
    String colour;

    public Event(String name, String date, boolean recurring, String colour) {
        this.name = name;
        this.date = date;
        this.recurring = recurring;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void printEvent(){
        System.out.println("Event Name        :    " + this.getName());
        System.out.println("Date              :    " + this.getDate());
        System.out.println("Recurring?        :    " + this.isRecurring());
        System.out.println("Calendar Colour   :    " + this.getColour());
    }

    public static void main(String[] args) {
        Event e = new Event("Boba", "March 16, 2021", true, "purple");

        e.printEvent();
    }
}
