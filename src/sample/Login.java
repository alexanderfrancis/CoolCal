package sample;

import javafx.application.Application;
import javafx.application.Platform;
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
    private static StackPane root = new StackPane();
    private static StackPane root4 = new StackPane();

    private  Stage stage;
    static Button buttonLogin = new Button("Login");
     static TextField username=new TextField();
    static PasswordField password=new PasswordField();

    static Button Register=new Button("Register");




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



    }

    public static void newroot(){
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

    }
//    public static StackPane root(){
//        VBox vBoxLogin = new VBox();
//
//        vBoxLogin.setSpacing(12);
//        vBoxLogin.setPadding(new Insets(15,10,10,15));
//        vBoxLogin.getChildren().addAll(
//                new Label("Username"),
//                username,
//                new Label("Your Password"),
//                password,
//                buttonLogin,
//
//                new Label("Don't have an account? Register!"),
//                Register);
//        root4.getChildren().addAll(vBoxLogin);
//        return root4;
//
//    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root_1 = FXMLLoader.load(getClass().getResource("Calendar_Main.fxml"));
        Parent root_2 = FXMLLoader.load(getClass().getResource("Register.fxml"));


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
        Register.setOnAction(actionEvent-> {
            stage = new Stage();
            StackPane stackPane = new StackPane();
            stage.setScene(new Scene(root_2, 245,600));
            stage.setAlwaysOnTop(true);
            stage.show();

        });
    }
    public static Stage getPrimaryStage() {
        newroot();
        Scene scene = new Scene(root4,800,600);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
        stage.setTitle("CoolCal Login");
        stage.setAlwaysOnTop(true);
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
