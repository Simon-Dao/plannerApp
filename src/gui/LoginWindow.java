package gui;

import client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;

import java.io.IOException;


public class LoginWindow {

    UITools tools = new UITools();

    public LoginWindow(Pane root) {

        try {
            if (tools.serverIsAlive(Client.IP, Client.PORT)) {
                Main.client = new Client();
                new Thread(Main.client).start();
            }
        } catch (Exception e) {
            System.err.println("server is currently unavailable");
        }

        Text loginText = new Text("Login");
        loginText.setFont(new Font("consolas", 20));
        loginText.setFill(Color.GREEN);
        loginText.setLayoutX(80);
        loginText.setLayoutY(60);

        TextField user_name = new TextField();
        user_name.setPromptText("username");
        user_name.setLayoutX(80);
        user_name.setLayoutY(80);

        Text usernameWarning = new Text("test");
        usernameWarning.setLayoutX(80);
        usernameWarning.setLayoutY(117);
        usernameWarning.setFill(Color.RED);
        usernameWarning.setFont(new Font("consolas", 13));

        TextField password = new TextField();
        password.setPromptText("password");
        password.setLayoutX(80);
        password.setLayoutY(125);

        Text passwordWarning = new Text("test");
        passwordWarning.setLayoutX(80);
        passwordWarning.setLayoutY(162);
        passwordWarning.setFill(Color.RED);
        passwordWarning.setFont(new Font("consolas", 13));

        Button apply = new Button("apply");
        apply.setStyle("-fx-background-color: #8BC34A; -fx-background-radius: 5;");
        apply.setLayoutX(80);
        apply.setLayoutY(180);
        apply.setPrefWidth(150);
        apply.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (tools.serverIsAlive(Client.IP, Client.PORT)) {

                    if (user_name.getText().length() != 0 && password.getText().length() != 0) {

                        tools.sendUserData(user_name.getText(), password.getText());

                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("[LOGINWINDOW] "+Client.serverResponse.startsWith("!userIsVerified!true"));

                        //TODO make the server recognise login info and stuff
                            if (Client.serverResponse.startsWith("!userIsVerified!true")) {
                                tools.changeScene(Main.messengerApp);
                            } else if (Client.serverResponse.startsWith("!userIsVerified!false")) {
                                usernameWarning.setText("wrong everything");
                            }
                        }
                } else {
                    System.err.println("server is currently unavailable");
                }
            }
        });

        Button signup = new Button("sign up!");
        signup.setStyle("-fx-background-color: whitesmoke; -fx-underline: true");
        signup.setLayoutX(127);
        signup.setLayoutY(210);
        signup.setOnAction(new EventHandler<ActionEvent>() {

            //changes scene to signup window
            @Override
            public void handle(ActionEvent event) {
                tools.changeScene(Main.signup);
            }
        });

        root.getChildren().add(loginText);
        root.getChildren().add(user_name);
        root.getChildren().add(usernameWarning);
        root.getChildren().add(password);
        root.getChildren().add(passwordWarning);
        root.getChildren().add(apply);
        root.getChildren().add(signup);
    }
}
