package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.awt.datatransfer.FlavorEvent;

public class Login extends Application {
    private StackPane root = new StackPane();
    private Stage stage;
    Button buttonLogin = new Button("Login");
    TextField username=new TextField();
    PasswordField password=new PasswordField();

    Button Register=new Button("Register");




    public void init() {

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
        root.getChildren().addAll(vBoxLogin);



//        buttonLogin.setOnAction(actionEvent->stage.setScene(new Scene(root,800,600))
//        {
//
//            CalData.userLoginAuthentication(username.getText(),password.getText());
//            if(stage!=null){
//                stage.requestFocus();
//
//                return;
//            }
//
//            stage = new Stage();
//            StackPane stackPane = new StackPane();
//            stage.setScene(new Scene(stackPane, 200,200));
//            stage.show();
//        }
//        );


        Register.setOnAction(actionEvent-> {

//            CalData.insertUser();
            if(stage!=null){
                stage.requestFocus();

                return;
            }

            stage = new Stage();
            StackPane stackPane = new StackPane();
            stage.setScene(new Scene(stackPane, 200,200));
            stage.show();
        });
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root_1 = FXMLLoader.load(getClass().getResource("Calendar_Main.fxml"));

        Scene scene = new Scene(root,800,600);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("CoolCal Login");
        primaryStage.setAlwaysOnTop(true);



        buttonLogin.setOnAction(actionEvent->{
            boolean accept=CalData.userLoginAuthentication(username.getText(),password.getText());

            if (accept){


                primaryStage.setScene(new Scene(root_1,800,600));

            }

        });





    }


    public static void main(String[] args) {
        launch(args);
    }

}
