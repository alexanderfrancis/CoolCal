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

    @Override
    public void init() {
        Button buttonLogin = new Button("OPEN");
        VBox vBoxLogin = new VBox();

        vBoxLogin.setSpacing(12);
        vBoxLogin.setPadding(new Insets(15,10,10,15));
        vBoxLogin.getChildren().addAll(
                new Label("Username"),
                new TextField(),
                new Label("Your Password"),
                new PasswordField(),
                new Button("Login"),

                new Label("Don't have an account? Register!"),
                new Button("Register"));
        root.getChildren().addAll(vBoxLogin);

        buttonLogin.setOnAction(actionEvent-> {
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
        Scene scene = new Scene(root,400,600);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("CoolCal Login");
        primaryStage.setAlwaysOnTop(true);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
