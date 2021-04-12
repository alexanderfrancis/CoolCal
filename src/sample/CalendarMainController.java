package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class CalendarMainController implements Initializable {

    public Button buttonNewEvent;
    public Button buttonLogout;
    public Button getMonth;
    public TableView TableView;
    public TableColumn EventName;
    public TableColumn DesName;
    public TableColumn DateName;
    public DatePicker date;



//    public TextField TitleInput;
//    public TextField DescriptionInput;
//    public TextField ScheduledAtInput;
//    public TextField triggeredAtInput;
//    public TextField URLInput;
//    public TextField typeInput;

//    private StackPane root = new StackPane();
    private Stage stage;

    static Button buttonLogin = new Button("Login");
    static TextField username=new TextField();
    static PasswordField password=new PasswordField();

    static Button Register=new Button("Register");
    private static StackPane root4 = new StackPane();


    public static StackPane root(){
        VBox vBoxLogin = new VBox();

        vBoxLogin.setSpacing(12);
        vBoxLogin.setPadding(new Insets(15,10,10,15));
        vBoxLogin.getChildren().addAll(
                new Label("Username"),
                username,
                new Label("Your Password"),
                password,
                buttonLogin,

                new Label("Don't have an account? Register!"),
                Register);
        root4.getChildren().addAll(vBoxLogin);
        return root4;

    }

    public void userLogout() throws Exception {
//        Stage stage=Login.carl();
        StackPane root4=root();
//        stage.show();
        Stage window = (Stage) buttonLogout.getScene().getWindow();
        window.setScene(new Scene(root4, 750, 800));

        Parent root5 = FXMLLoader.load(getClass().getResource("Calendar_Main.fxml"));


        buttonLogin.setOnAction(actionEvent->{
            boolean accept=CalData.userLoginAuthentication(username.getText(),password.getText());

            if (accept){


                window.setScene(new Scene(root5, 750, 800));

            }

        });
        Parent root_2 = FXMLLoader.load(getClass().getResource("Register.fxml"));

        Register.setOnAction(actionEvent-> {
            stage = new Stage();
            StackPane stackPane = new StackPane();
            stage.setScene(new Scene(root_2, 245,600));
            stage.setAlwaysOnTop(true);
            stage.show();

        });



    }
    public void getCalMonth() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("CalenderMonth.fxml"));
        System.out.println("this is month button");

        Stage window = (Stage) getMonth.getScene().getWindow();
//        window.setFullScreen(true);

        window.setScene(new Scene(root));

    }


    @FXML
    protected void eventShow(){
//        ObservableList<String> list  =FXCollections.observableArrayList();
//        TableView.setItems(list);
//
//        TableView.getItems().add("New Item");
        EventName.setCellValueFactory(new PropertyValueFactory<>("title"));
        DesName.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableView.setItems(getEventListonDate());
    }

    @Override
    public void initialize (URL arg0, ResourceBundle arg1){

        EventName.setCellValueFactory(new PropertyValueFactory<>("title"));
        DesName.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableView.setItems(getEventList());




    }
    public ObservableList<Event> getEventList(){
        ObservableList<Event> events  =FXCollections.observableArrayList();
        Integer ID=CalData.current_user_id;
        System.out.println("userid:"+ID);
        ArrayList<Event> eventList=CalData.getEvents(ID);

        for(Event name : eventList)
        {

            events.add(name);
        }




        return events;



    }


    ObservableList<Event> getEventListonDate(){
        ObservableList<Event> events  =FXCollections.observableArrayList();
        Integer ID=CalData.current_user_id;
        System.out.println(ID);
        ArrayList<Event> eventList=CalData.getEvents(ID);

        for(Event name : eventList)
        {
            Calendar cal= Calendar.getInstance();

            cal.setTime(name.getDate());
            LocalDate dat=date.getValue();
            Integer Month= dat.getMonthValue();
            Integer day= dat.getDayOfMonth();
            Integer year=dat.getYear();




            if (cal.get(Calendar.MONTH)==(Month-1) && cal.get(Calendar.DAY_OF_MONTH)==(day) && cal.get(Calendar.YEAR)==(year)){
                events.add(name);
            }

        }




        return events;



    }


    public void AddEvent() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("createEvent.fxml"));
        System.out.println("this is register button");




        Stage window = (Stage) buttonNewEvent.getScene().getWindow();
        window.setScene(new Scene(root, 750, 800));


    }

}
