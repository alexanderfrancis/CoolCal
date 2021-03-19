package sample;

import java.sql.*;
import java.util.ArrayList; // import the ArrayList class
public class CalData{
    public static ArrayList<Event> events = new ArrayList<Event>(); // Create an ArrayList object


    public static void insertUser(String userName,String Pass,String firstName,String lastName,String email){

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
            preparedStmt.setString (5, Pass);

            preparedStmt.execute();
            System.out.println("SUCCESS");

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }


    }

    public static void getEvents(Integer userId){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query="SELECT * FROM events WHERE userId=? ";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, userId);


            ResultSet rs=preparedStmt.executeQuery();

            if (!rs.next()){
                System.out.println("No events");
            }
            else{
                while(rs.next()!=false){

                    String date=rs.getString("scheduledAt");
                    int gr=rs.getInt("type");

                    String title=rs.getString("title");

                    Event e = new Event(title, date, true, "purple");

                    events.add(e);
                    System.out.println("done events");
                }


            }
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

    public static void userLoginAuthentication(String userName,String Pass){

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

                String user_id=rs.getString("Id");
                System.out.println("Your user ID is " +user_id);
                System.out.println("Welcome back "+data_name+"!");

            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void insertEvent(String title, String description, Timestamp ScheduledAt, String content, String url, Integer type, Integer reminderCount, Integer reminderInterval, Integer userId){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query=" insert into events (userId,title,description, ScheduledAt, content, url, type, reminderCount, reminderInterval)"
                    + " values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, userId);
            preparedStmt.setString (2, title);
            preparedStmt.setString (3, description);
            preparedStmt.setTimestamp (4, ScheduledAt);
            preparedStmt.setString (5, content);
            preparedStmt.setString (6, url);
            preparedStmt.setInt (7, type);
            preparedStmt.setInt (8, reminderCount);
            preparedStmt.setInt (9, reminderInterval);

            preparedStmt.execute();

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
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




    public static void main(String[] args) {

        insertUser("Jeff_1234","mydog123","Jeff","larry","jeff@hotmail.com");
        openConnectionTest();
        userLoginAuthentication("jeff_1234","mydog123");
        Timestamp date = new Timestamp(System.currentTimeMillis());
//        insertEvent("dentist appointment","tommorow 2:30",date,"dsfdsfds","dsdsds",1,1,3,4);
        getEvents(4);

        System.out.println("For Loop");
        for (int counter = 0; counter < events.size(); counter++) {
            System.out.println(events.get(counter).name);
        }




    }

}