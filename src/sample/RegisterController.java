package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class RegisterController {
    public Button buttonRegister;
    public TextField userFirstNameInput;
    public TextField userLastNameInput;
    public TextField userEmailInput;
    public TextField userNameInput;
    public TextField userPasswordInput;


    public void start(Stage primaryStage) throws Exception {



    }


    public void userRegister() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Calendar_Main.fxml"));
        System.out.println("this is register button");

        boolean n = CalData.insertUser(userFirstNameInput.getText(), userLastNameInput.getText(), userEmailInput.getText(), userNameInput.getText(), userPasswordInput.getText());

        if (n==true) {
            Stage window = (Stage) buttonRegister.getScene().getWindow();
            window.setScene(new Scene(root, 750, 800));
        }
    }
}
