package main;

import client.Client;
import client.LocalUser;
import gui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static Client client;

    public static Stage stage;
    public static Scene messengerApp;
    public static Scene login;
    public static Scene signup;

    private UITools tools = new UITools();

    public static Pane signupPane;

    public static SignUpWindow signupWindow;

    public static AppUI app;

    public static LocalUser localUser;

    /*
        TODO add a friend system
        TODO create a contacts section on the left
        TODO make it search for people and then send them a friend request
        TODO make the person's color appear depending on login
     */
    @Override
    public void start(Stage stage) throws Exception {

        tools.startClient();

        this.stage = stage;

        SplitPane root = new SplitPane();
        Pane loginLayout = new Pane();
        signupPane = new Pane();

        new LoginWindow(loginLayout);
        signupWindow = new SignUpWindow(signupPane);
        app = new AppUI(root);

        signup = new Scene(signupPane, 300, 300);
        new WindowControls(stage, signup);

        login = new Scene(loginLayout, 300, 250);
        new WindowControls(stage, login);

        messengerApp = new Scene(root, 500, 500);
        messengerApp.getStylesheets().add(this.getClass().getResource("Style.css").toExternalForm());

        new WindowControls(stage, messengerApp);

        stage.setTitle("Messenger");
        //stage.setScene(signup);
        stage.setScene(login);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
