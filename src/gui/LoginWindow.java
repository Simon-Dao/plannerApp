package gui;

import client.Client;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
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

        Text loginText = new Text("Login");
        loginText.setFont(new Font("consolas", 20));
        loginText.setFill(Color.web("#1e90ff"));
        loginText.setLayoutX(80);
        loginText.setLayoutY(60);

        TextField username = new TextField();
        username.setStyle("-fx-background-color: #CFD8DC; -fx-background-radius: 10;");
        username.setPromptText("username");
        username.setLayoutX(80);
        username.setLayoutY(80);
        tools.maxCharLength(username, 30);

        Text usernameWarning = new Text("");
        usernameWarning.setLayoutX(80);
        usernameWarning.setLayoutY(117);
        usernameWarning.setFill(Color.RED);
        usernameWarning.setFont(new Font("consolas", 13));

        //prevents username textfield from taking in ! and " " for security
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    String text = username.getText();

                    if (text.contains(" ")) {
                        usernameWarning.setText("cannot use space");
                        username.setText(text.replace(" ", ""));

                    } else if (text.contains("!")) {
                        usernameWarning.setText("cannot use ! symbol");
                        username.setText(text.replace("!", ""));

                    } else {
                        usernameWarning.setText("");
                    }

                } catch (StringIndexOutOfBoundsException e) {
                }
            }
        };
        username.addEventFilter(KeyEvent.KEY_RELEASED, eventHandler);

        TextField password = new TextField();
        password.setStyle("-fx-background-color: #CFD8DC; -fx-background-radius: 10;");
        password.setPromptText("password");
        password.setLayoutX(80);
        password.setLayoutY(125);

        Text passwordWarning = new Text("");
        passwordWarning.setLayoutX(80);
        passwordWarning.setLayoutY(162);
        passwordWarning.setFill(Color.RED);
        passwordWarning.setFont(new Font("consolas", 13));

        Button apply = new Button("apply");
        apply.setStyle("-fx-background-color: #1E90FF; -fx-background-radius: 5; -fx-text-fill: whitesmoke;");
        apply.setLayoutX(80);
        apply.setLayoutY(180);
        apply.setPrefWidth(150);
        apply.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (tools.serverIsAlive(Client.IP, Client.PORT)) {

                    if (username.getText().length() != 0 && password.getText().length() != 0) {

                        tools.sendUserData(username.getText(), password.getText());

                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("[LOGINWINDOW] "+Client.serverResponse.startsWith("!userIsVerified!true"));

                            if (Main.client.serverResponse.startsWith("!userIsVerified!true")) {
                                tools.changeScene(Main.messengerApp);
                            } else if (Main.client.serverResponse.startsWith("!userIsVerified!false")) {
                                usernameWarning.setText("user could not be verified");
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
        root.getChildren().add(username);
        root.getChildren().add(usernameWarning);
        root.getChildren().add(password);
        root.getChildren().add(passwordWarning);
        root.getChildren().add(apply);
        root.getChildren().add(signup);
    }
}
