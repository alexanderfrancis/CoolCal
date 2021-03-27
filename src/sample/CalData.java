package sample;

import java.sql.*;
import java.util.ArrayList; // import the ArrayList class
import java.util.Calendar;

public class CalData{
//    public static ArrayList<Event> events = new ArrayList<Event>(); // Create an ArrayList object


    public static void insertUser(String firstName,String lastName,String email,String userName,String password){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query=" insert into users (firstName,lastName,userName,email,passwordHash)"
                    + " values (?,?,?,?,MD5(?))";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, firstName);
            preparedStmt.setString (2, lastName);
            preparedStmt.setString (3, userName);
            preparedStmt.setString (4, email);
            preparedStmt.setString (5, password);

            preparedStmt.execute();
            System.out.println("SUCCESS");

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }


    }




        public static void deleteEvent(Integer eventId){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query="DELETE from events WHERE  Id=(?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, eventId);


            preparedStmt.execute();
            System.out.println("SUCCESS");

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static void deleteUser(Integer userId){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query="DELETE from users WHERE Id=(?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, userId);


            preparedStmt.execute();
            System.out.println("SUCCESSFUL USER DELETION");

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static boolean userLoginAuthentication(String userName,String Pass){
        boolean accepted=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

//          String query="SELECT * FROM users WHERE userName='"+ userName +"' AND passwordHash=MD5('"+Pass+"')";
            String query="SELECT * FROM users WHERE userName=? AND passwordHash=MD5(?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, userName);
            preparedStmt.setString (2, Pass);

            ResultSet rs=preparedStmt.executeQuery();

            if (!rs.next()){
                System.out.println("wrong username or password");
            }
            else{
                String data_name=rs.getString("firstName");
                accepted=true;

                String user_id=rs.getString("Id");
                System.out.println("Your user ID is " +user_id);
                System.out.println("Welcome back "+data_name+"!");

            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return accepted;
    }

    public static void insertEvent(String title, String description, Timestamp ScheduledAt, Timestamp triggeredAt, String url, Integer type, Integer userId){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query=" insert into events (userId,title,description, ScheduledAt, triggeredAt, url, type)"
                    + " values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, userId);
            preparedStmt.setString (2, title);
            preparedStmt.setString (3, description);
            preparedStmt.setTimestamp (4, ScheduledAt);
            preparedStmt.setTimestamp (5, triggeredAt);
            preparedStmt.setString (6, url);
            preparedStmt.setInt (7, type);

            preparedStmt.execute();

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static ArrayList<Event> getEvents(Integer userId){
        ArrayList<Event> events = new ArrayList<Event>(); // Create an ArrayList object

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query="SELECT * FROM events WHERE userId=? ";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, userId);


            ResultSet rs=preparedStmt.executeQuery();

//            if (!rs.next()){
//                System.out.println("No events");
//            }
//            else{
//
            while(rs.next()!=false){

                Timestamp date=rs.getTimestamp("scheduledAt");

                String title=rs.getString("title");
                Integer user_ID=rs.getInt("userId");
                String description=rs.getString("description");
                Integer ID=rs.getInt("id");
                Integer type=rs.getInt("type");
                String URL=rs.getString("url");
                Timestamp triggeredAt=rs.getTimestamp("triggeredAt");


                Event e = new Event(user_ID,title,date,type,description,URL,triggeredAt,ID);

                events.add(e);
                System.out.println("done events");
            }


//            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return events;


    }


//    public static void updateEvent(String title, Integer Id){
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");
//
//            String query = "UPDATE events SET title = ? WHERE Id = " + Id;
//            PreparedStatement preparedStmt = con.prepareStatement(query);
//            preparedStmt.setString (1, title);
//
//            preparedStmt.executeUpdate(query);
//            con.close();
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }


    public static void openConnectionTest(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test_schema","root","");
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery("select * from users");
            while (rs.next()){
                System.out.println(rs.getInt(1)+" "+ rs.getString(2));
            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }
    public static Timestamp setEtime(int year, int month, int day, int hour, int minute, int second){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());

        return timestamp;
    }

    public static Timestamp setTrigger(int year, int month, int day, int hour, int minute, int second){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);

        int min = 0;
        if (minute < 5){
            min = 60 - 5 + minute;
            calendar.set(Calendar.MINUTE, min);
        }else{
            calendar.set(Calendar.MINUTE, minute-5);
        }
        Timestamp trigger = new Timestamp(calendar.getTimeInMillis());

        return trigger;
    }




    public static void main(String[] args) {

        insertUser("Jeff_1234","mydog123","Jeff","larry","jeff@hotmail.com");
        openConnectionTest();
        System.out.println(userLoginAuthentication("jeff_1234","mydog123"));
        Timestamp date = new Timestamp(System.currentTimeMillis());
//        insertEvent("dentist appointment","tommorow 2:30",date,"dsfdsfds","dsdsds",1,1,3,4);
        ArrayList<Event> events=getEvents(4);

        System.out.println("For Loop");
        for (int counter = 0; counter < events.size(); counter++) {
            System.out.println(events.get(counter).title);
        }


        int year = 2021;
        int month = 0;
        int day = 8;
        int hour = 19;
        int minute = 50;
        int second = 0;
        Timestamp timestamp = setEtime(year,month,day,hour,minute,second);
        Timestamp trigger = setTrigger(year,month,day,hour,minute,second);

        insertEvent("test4", "test3", timestamp, trigger, "url", 1, 4);

    }

}