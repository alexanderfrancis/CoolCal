import java.sql.*;
public class CalData {

    public static void insertUser(String Name,String Pass ){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test_schema","root","");


            String query=" insert into users (Name, Password)"
                    + " values (?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, Name);
            preparedStmt.setString (2, Pass);

            preparedStmt.execute();

            con.close();
        }catch (Exception e){
            System.out.println(e);
        }


        // does stuff
    }

    public static void deleteEvent(){
        // does stuff
    }

    public static void insertEvent(){
        // does stuff
    }

    public static void openConnection(){

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
        insertUser("TIff","mydog");
        openConnection();




    }

}
