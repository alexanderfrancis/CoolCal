package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.control.Button;


public class ViewEventController implements Initializable {

    public Text titleLabel;
    public Text descriptionLabel;
    public  Text scheduledAtLabel;
    public  Text urlLabel;
    public  Text hostLabel;

    public Button delete;
    public Button backButton;
    public TableView TableView;
    public TableColumn nameGuest;
    public TableColumn emailGuest;
    public TextField inviteTextField;


    @Override
    public void initialize (URL arg0, ResourceBundle arg1){
        Integer eventId=CalenderMonth.eventId;

        ArrayList<Event> event=CalData.getEvent(eventId);

        for (Event i:event){
            String titleS=i.getTitle();
            titleLabel.setText(titleS);


            String desS=i.getDescription();
            descriptionLabel.setText(desS);

            Integer hosId=i.getUserId();
            ArrayList<User> u=CalData.getUsers(hosId);
            for (User name:u){
                hostLabel.setText(name.getFirstName()+" "+ name.getLastName());

            }
            descriptionLabel.setText(desS);

            Calendar cal=Calendar.getInstance();

            Timestamp t= i.getDate();
            cal.setTime(t);

            Integer month=cal.get(Calendar.MONTH);
            Integer day=cal.get(Calendar.DAY_OF_MONTH);
            Integer year=cal.get(Calendar.YEAR);
            scheduledAtLabel.setText(month+1+"/"+day+"/"+year);

            String url= i.getURL();

            urlLabel.setText(url);



        }
        ArrayList<MultiEvent> Guests=CalData.join(eventId);
        ObservableList<MultiEvent> guestEvent = FXCollections.observableArrayList();


        for(MultiEvent j: Guests){
            guestEvent.add(j);


        }
        nameGuest.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailGuest.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableView.setItems(guestEvent);

    }

    public void deleteButton() throws IOException {
        Integer eventId=CalenderMonth.eventId;

        CalData.deleteEvent(eventId);

//        Stage stage = (Stage) delete.getScene().getWindow();
        // do what you have to do
        Parent root = FXMLLoader.load(getClass().getResource("CalenderMonth.fxml"));

        Stage window = (Stage) delete.getScene().getWindow();
        window.setScene(new Scene(root, 800, 800));

//        stage.close();


    }
    public void backButton() throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("CalenderMonth.fxml"));

        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root, 800, 800));


    }

    public void inviteGuest() {

        String guestUsername=inviteTextField.getText();
        ArrayList<User> ifGuestFound=CalData.userExists(guestUsername);
        Integer eventId=CalenderMonth.eventId;

        if (!ifGuestFound.isEmpty()){
            for (User i: ifGuestFound){
                CalData.insertGuest(i.getId(),eventId);

            }

        }



    }

}