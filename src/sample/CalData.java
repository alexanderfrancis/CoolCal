package sample;


import java.sql.*;
import java.util.ArrayList; // import the ArrayList class
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class CalData{
    public static Integer current_user_id=-1;
    //    public static ArrayList<Event> events = new ArrayList<Event>(); // Create an ArrayList object


    public static boolean insertUser(String firstName,String lastName,String email,String userName,String password){

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

//            current=new User(-1, firstName, lastName, userName,
//                    email, password,
//                    null, null);

            con.close();
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;

    }
    public static void updateUserID() {
        current_user_id=-1;

    }






    public static boolean deleteEvent(Integer eventId){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query="DELETE from events WHERE  Id=(?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, eventId);


            preparedStmt.execute();
            System.out.println("SUCCESS");

            con.close();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    public static boolean deleteUser(Integer userId){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query="DELETE from users WHERE Id=(?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, userId);


            preparedStmt.execute();
            System.out.println("SUCCESSFUL USER DELETION");

            con.close();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
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
                String data_last=rs.getString("lastName");
                accepted=true;

                Integer user_id=rs.getInt("Id");
                System.out.println("Your user ID is " +user_id);
                System.out.println("Welcome back "+data_name+"!");
                String email=rs.getString("email");

                current_user_id=user_id;

            }

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return accepted;
    }

    public static boolean insertEvent(String title, String description, Timestamp ScheduledAt, Timestamp triggeredAt, String url, Integer userId, Integer notified){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query=" insert into events (userId,title,description, ScheduledAt, triggeredAt, url, notified)"
                    + " values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, userId);
            preparedStmt.setString (2, title);
            preparedStmt.setString (3, description);
            preparedStmt.setTimestamp (4, ScheduledAt);
            preparedStmt.setTimestamp (5, triggeredAt);
            preparedStmt.setString (6, url);
            preparedStmt.setInt (7, notified);

            preparedStmt.execute();

            con.close();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static ArrayList<User> getUsers(Integer Id){
        ArrayList<User> userinfo = new ArrayList<User>(); // Create an ArrayList object

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query="SELECT * FROM users WHERE Id=?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, Id);
            ResultSet rs=preparedStmt.executeQuery();

            while(rs.next()!=false){

                String firstName=rs.getString("firstName");
                String lastName=rs.getString("lastName");
                String userName=rs.getString("userName");
                String email=rs.getString("email");
                Integer id=rs.getInt("Id");
                String passwordHash=rs.getString("passwordHash");
                Timestamp registeredAt=rs.getTimestamp("registeredAt");
                Timestamp lastLogin=rs.getTimestamp("lastLogin");

                User u = new User(id, firstName, lastName, userName, email, passwordHash, registeredAt, lastLogin);

                userinfo.add(u);
            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return userinfo;
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

            while(rs.next()!=false){

                Timestamp date=rs.getTimestamp("scheduledAt");
                String title=rs.getString("title");
                Integer user_ID=rs.getInt("userId");
                String description=rs.getString("description");
                Integer ID=rs.getInt("id");
                String URL=rs.getString("url");
                Timestamp triggeredAt=rs.getTimestamp("triggeredAt");
                Integer notified=rs.getInt("notified");

                Event e = new Event(user_ID,title,date,description,URL,triggeredAt,notified,ID);

                events.add(e);
                System.out.println("done events");
            }

            con.close();
        }catch (Exception e){
            System.out.println(e);

        }
        return events;
    }

    public static ArrayList<Event> getTodaysEvents(){
        ArrayList<Event> today = new ArrayList<Event>(); // Create an ArrayList object

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query="SELECT * FROM events WHERE scheduledAt BETWEEN DATE(CURDATE()) AND DATE_ADD(CURDATE(), INTERVAL 1 DAY)";

            PreparedStatement preparedStmt = con.prepareStatement(query);

            ResultSet rs=preparedStmt.executeQuery();

            while(rs.next()!=false){

                Timestamp date=rs.getTimestamp("scheduledAt");
                String title=rs.getString("title");
                Integer user_ID=rs.getInt("userId");
                String description=rs.getString("description");
                Integer ID=rs.getInt("id");
                String URL=rs.getString("url");
                Timestamp triggeredAt=rs.getTimestamp("triggeredAt");
                Integer notified=rs.getInt("notified");


                Event e = new Event(user_ID,title, date, description, URL, triggeredAt, notified, ID);

                today.add(e);
            }

            con.close();
        }catch (Exception e){
            System.out.println(e);

        }
        return today;
    }




    public static boolean updateEvent(String title, String description, Timestamp ScheduledAt, Timestamp triggeredAt, String url, Integer notified, Integer Id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            PreparedStatement pstmt = null;
            if (title != null){
                pstmt = con.prepareStatement("update events set title=? where Id=?");
                pstmt.setString(1,title);
                pstmt.setInt(2,Id);

            }else if (description != null) {
                pstmt = con.prepareStatement("update events set description=? where Id=?");
                pstmt.setString(1, description);
                pstmt.setInt(2, Id);

            }else if (ScheduledAt != null){
                pstmt = con.prepareStatement("update events set ScheduledAt=? where Id=?");
                pstmt.setTimestamp(1, ScheduledAt);
                pstmt.setInt(2, Id);

            }else if (triggeredAt != null){
                pstmt = con.prepareStatement("update events set triggeredAt=? where Id=?");
                pstmt.setTimestamp(1, triggeredAt);
                pstmt.setInt(2, Id);

            }else if (url != null){
                pstmt = con.prepareStatement("update events set url=? where Id=?");
                pstmt.setString(1, url);
                pstmt.setInt(2, Id);

            }else if (notified != null){
                pstmt = con.prepareStatement("update events set notified=? where Id=?");
                pstmt.setInt(1, notified);
                pstmt.setInt(2, Id);

            }
            pstmt.executeUpdate();

            con.close();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static boolean openConnectionTest(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");
            Statement stat=con.createStatement();
            ResultSet rs=stat.executeQuery("select * from users");
            while (rs.next()){
                System.out.println(rs.getInt(1)+" "+ rs.getString(2));
            }
            con.close();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static Timestamp setEtime(int year, int month, int day, int hour, int minute, int second){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
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
        calendar.set(Calendar.HOUR_OF_DAY, hour);
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

    public static boolean userExists(String username){
        boolean result =false;
        ArrayList<User> user = new ArrayList<User>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query="SELECT * FROM users WHERE userName=?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, username);
            ResultSet rs=preparedStmt.executeQuery();

            while(rs.next()!=false){

                String firstName=rs.getString("firstName");
                String lastName=rs.getString("lastName");
                String userName=rs.getString("userName");
                String email=rs.getString("email");
                Integer id=rs.getInt("Id");
                String passwordHash=rs.getString("passwordHash");
                Timestamp registeredAt=rs.getTimestamp("registeredAt");
                Timestamp lastLogin=rs.getTimestamp("lastLogin");

                User u = new User(id, firstName, lastName, userName, email, passwordHash, registeredAt, lastLogin);
                user.add(u);
            }
            if (user.size() > 0) {
                result = true;
            }
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return result;
    }


    public static boolean insertGuest(Integer userId, Integer eventId){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query=" insert into guests (userID,eventID)"
                    + " values (?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, userId);
            preparedStmt.setInt (2, eventId);

            preparedStmt.execute();

            con.close();
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    //function that returns hosts name
    public static ArrayList<MultiEvent> join(Integer eventId) {
        ArrayList<MultiEvent> events = new ArrayList<MultiEvent>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query = "SELECT events.title, events.description, events.scheduledAt, events.url, events.Id, guests.userID, users.username, users.firstname, users.lastname, users.email,events.notified" +
                    "FROM events " +
                    "INNER JOIN guests ON events.Id = guests.eventID " +
                    "INNER JOIN users ON guests.userID = users.Id" +
                    "WHERE events.Id = ?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, eventId);
            ResultSet res=preparedStmt.executeQuery();

            while (res.next()) {
                String title = res.getString("title");
                Integer eventID = res.getInt("eventID");
                Integer userID = res.getInt("userID");
                String username = res.getString("userName");
                String description = res.getString("description");
                Timestamp scheduledAt = res.getTimestamp("scheduledAt");
                String url = res.getString("url");
                String firstname = res.getString("firstName");
                String lastname = res.getString("lastName");
                String email = res.getString("email");
                Integer notified = res.getInt("notified");

                MultiEvent u = new MultiEvent(userID, title, username, eventID, description, scheduledAt, url, firstname, lastname, email, notified);
                events.add(u);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return events;
    }

    public static ArrayList<MultiEvent> userjoin(Integer userId) {
        ArrayList<MultiEvent> events = new ArrayList<MultiEvent>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/calendar","root","");

            String query = "SELECT events.title, events.description, events.scheduledAt, events.url, guests.eventID, guests.userID, users.username, users.firstname, users.lastname, users.email, users.notified" +
                    "FROM events " +
                    "INNER JOIN guests ON events.userId = guests.userID " +
                    "INNER JOIN users ON users.Id = ?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, userId);
            ResultSet res=preparedStmt.executeQuery();

            while (res.next()) {
                String title = res.getString("title");
                Integer eventID = res.getInt("eventID");
                Integer userID = res.getInt("userID");
                String username = res.getString("userName");
                String description = res.getString("description");
                Timestamp scheduledAt = res.getTimestamp("scheduledAt");
                String url = res.getString("url");
                String firstname = res.getString("firstName");
                String lastname = res.getString("lastName");
                String email = res.getString("email");
                Integer notified = res.getInt("notified");

                MultiEvent u = new MultiEvent(userID, title, username, eventID, description, scheduledAt, url, firstname, lastname, email, notified);
                events.add(u);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return events;
    }


    public static ArrayList<MailInfo> mail(){
        ArrayList<MailInfo> mail = new ArrayList<MailInfo>();

        ArrayList<Event> today=getTodaysEvents();
        for (int counter = 0; counter < today.size(); counter++) {
            Integer id = today.get(counter).id;

            ArrayList<MultiEvent> events = join(id);
            for (int i = 0; i < events.size(); i++) {
                String title = events.get(i).title;
                String firstName = events.get(i).firstName;
                String lastName = events.get(i).lastName;
                String email = events.get(i).email;
                Timestamp date = events.get(i).scheduledAt;
                String url = events.get(i).url;
                String description = events.get(i).description;
                Integer notified = events.get(i).notified;

                MailInfo m = new MailInfo(firstName, lastName, email, date, title, url, description, notified, id);
                mail.add(m);

            }
        }
        return mail;
    }


    public static void main(String[] args) {
//        insertUser("John","Doe","doe.john@example.com","jdoe","1234");
        openConnectionTest();

        System.out.println(userLoginAuthentication("jeff_1234","mydog123"));
//        Timestamp date = new Timestamp(System.currentTimeMillis());
////        insertEvent("dentist appointment","tommorow 2:30",date,"dsfdsfds","dsdsds",1,1,3,4);
//        ArrayList<Event> events=getEvents(4);
//
//        System.out.println("For Loop");
//        for (int counter = 0; counter < events.size(); counter++) {
//            System.out.println(events.get(counter).title);
//        }

//        int year = 2021;
//        int month = 3; //starts at 0
//        int day = 12;
//        int hour = 8;
//        int minute = 50;
//        int second = 0;
//        Timestamp timestamp = setEtime(year,month,day,hour,minute,second);
//        Timestamp trigger = setTrigger(year,month,day,hour,minute,second);
//        insertEvent("fdsfds","dmkmdksa",timestamp,trigger,"www.google.com",7, 0);

//        boolean result = userExists("nico");
//        System.out.println(result);
//
//        insertGuest(7,40);
//        insertGuest(1, 40);

        ArrayList<MultiEvent> events = userjoin(7);
        for (int counter = 0; counter < events.size(); counter++) {
            System.out.println(events.get(counter).title);
            System.out.println(events.get(counter).eventID);
            System.out.println(events.get(counter).userID);
            System.out.println(events.get(counter).username);
        }


        //        insertEvent("test4", "test3", timestamp, trigger, "url", 1, 4, 0);

//        updateEvent("title_update",null,null,null,null,null,null,null);


//        ArrayList<User> userinfo=getUsers();
//        for (int counter = 0; counter < userinfo.size(); counter++) {
//            String userName = userinfo.get(counter).userName;
//            System.out.println(userName);
//            String email = userinfo.get(counter).email;
//            System.out.println(email);
//        }


//        ArrayList<MailInfo> today=mail();
//        for (int counter = 0; counter < today.size(); counter++) {
//            Timestamp date = today.get(counter).registeredAt;
//            String title = today.get(counter).title;
//            String url = today.get(counter).url;
//            String description = today.get(counter).description;
//            Integer notified = today.get(counter).notified;
//            String firstName = today.get(counter).firstName;
//            String lastName = today.get(counter).lastName;
//            String email = today.get(counter).email;
//
//            System.out.println("========");
//            System.out.println(firstName);
//            System.out.println(lastName);
//            System.out.println(email);
//            System.out.println(title);
//            System.out.println(description);
//            System.out.println(url);
//            System.out.println(date);
//            System.out.println(notified);
//        }


    }
}
