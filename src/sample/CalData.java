package sample;

import java.sql.*;
public class CalData{

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

    public static void insertEvent(String title,String description,Integer type,Integer userID){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query=" insert into events (userId,title,description,type)"
                    + " values (?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, userID);
            preparedStmt.setString (2, title);
            preparedStmt.setString (3, description);
            preparedStmt.setInt (4, type);

            preparedStmt.execute();

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

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




    }

}