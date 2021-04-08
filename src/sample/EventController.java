package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class EventController {


    public TextField TitleInput;
    public TextField DescriptionInput;
    public TextField ScheduledAtInput;
    public TextField triggeredAtInput;
    public TextField URLInput;
    public TextField typeInput;

    private StackPane root = new StackPane();
    private Stage stage;
    private DatePicker d =new DatePicker();
    public DatePicker date;
    public Spinner spinner;
    public Spinner spinnerTime;

    public Spinner spinnerSH;
    public Spinner spinnerSM;
    public DatePicker dateS;
    public Button insertEvent;




    public void insertEvent() throws Exception  {
        System.out.println("event has been inserted");


        Integer ID=CalData.current_user_id;

        LocalDate picked =date.getValue();

        Integer hour= (Integer) spinner.getValue();
        Integer minute= (Integer) spinnerTime.getValue();

        Integer hour_T= (Integer) spinnerSH.getValue();
        Integer minute_T= (Integer) spinnerSM.getValue();
        LocalDate trigger =dateS.getValue();


//        Instant instant= Instant.from(picked.atStartOfDay(ZoneId.systemDefault()));


//        java.util.Date day=Date.from(instant);

//        Integer Month=Integer.parseInt(String.valueOf(picked.getMonth()));

        Integer Month=picked.getMonthValue();
        Integer year=picked.getYear();
        Integer number=picked.getDayOfMonth();

        Integer Month_T=trigger.getMonthValue();
        Integer year_T=trigger.getYear();
        Integer number_T=trigger.getDayOfMonth();

        Timestamp s=CalData.setEtime(year,Month-1,number,hour,minute,0);
        Timestamp t=CalData.setEtime(year_T,Month_T-1,number_T,hour_T,minute_T,0);

//        Timestamp 2=CalData.setEtime(year,Month,number,hour,minute,0);

//        System.out.println(picked + "\n" + instant + "\n" + day+ "hour:"+hour+ "\n" + Month+"\n"+ year+"\n"+number);

        CalData.insertEvent(TitleInput.getText(),DescriptionInput.getText(),s,t,URLInput.getText(),Integer.parseInt(typeInput.getText()),ID);

        Parent root = FXMLLoader.load(getClass().getResource("Calendar_Main.fxml"));

        System.out.println("this is register button");



        Stage window = (Stage) insertEvent.getScene().getWindow();
        window.setScene(new Scene(root, 750, 800));



    }
    public void schedule() {
//        System.out.println("event has been inserted");



    }
}
