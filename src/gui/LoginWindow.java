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

        Text hello = new Text("Login");
        hello.setFont(new Font("consolas",20));
        hello.setFill(Color.GREEN);
        hello.setLayoutX(80);
        hello.setLayoutY(70);

        TextField user_name = new TextField();
        user_name.setPromptText("username");
        user_name.setLayoutX(80);
        user_name.setLayoutY(100);

        TextField password = new TextField();
        password.setPromptText("password");
        password.setLayoutX(80);
        password.setLayoutY(130);

        Button apply = new Button("apply");
        apply.setStyle("-fx-background-color: #8BC34A; -fx-background-radius: 5;");
        apply.setLayoutX(80);
        apply.setLayoutY(170);
        apply.setPrefWidth(150);
        apply.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (tools.serverIsAlive(Client.IP, Client.PORT)) {

                    if (user_name.getText().length() != 0 && password.getText().length() != 0) {

                        tools.changeScene(Main.messengerApp);

                        tools.sendUserData(user_name.getText(), password.getText());
                    }
                } else {
                    System.err.println("go away server is down");
                }
            }
        });

        Button signup = new Button("sign up!");
        signup.setStyle("-fx-background-color: whitesmoke; -fx-underline: true");
        signup.setLayoutX(127);
        signup.setLayoutY(200);
        signup.setOnAction(new EventHandler<ActionEvent>() {

            //changes scene to signup window
            @Override
            public void handle(ActionEvent event) { tools.changeScene(Main.signup); }});

        root.getChildren().add(hello);
        root.getChildren().add(user_name);
        root.getChildren().add(password);
        root.getChildren().add(apply);
        root.getChildren().add(signup);
    }
}
