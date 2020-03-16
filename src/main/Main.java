package main;

import client.Client;
import gui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //TODO add a new friend system
    //TODO make the server store user data

    public static Client client;

    public static Stage stage;
    public static Scene messengerApp;
    public static Scene login;
    public static Scene signup;

    public static Pane signupWindow;

    public static AppUI app;

    /*

     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        SplitPane root = new SplitPane();
        Pane loginLayout = new Pane();
        signupWindow = new Pane();

        new LoginWindow(loginLayout);
        new SignUpWindow(signupWindow);
        app = new AppUI(root);

        signup = new Scene(signupWindow, 300, 300);
        new WindowControls(stage, signup);

        login = new Scene(loginLayout, 300, 250);
        new WindowControls(stage, login);

        messengerApp = new Scene(root, 500 ,500);
        messengerApp.getStylesheets().add(this.getClass().getResource("Style.css").toExternalForm());

        new WindowControls(stage, messengerApp);

        stage.setTitle("Messenger");
        //stage.setScene(login);
        stage.setScene(signup);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
