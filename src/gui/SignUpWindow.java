package gui;

import client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;

import java.io.IOException;

//TODO get why the messenger starts even when the username is taken

public class SignUpWindow {

    UITools tools = new UITools();

    public String color;

    public Text usernameWarning;

    public SignUpWindow(Pane root) {

        //starting the client
        try {
            if (tools.serverIsAlive(Client.IP, Client.PORT)) {
                Main.client = new Client();
                new Thread(Main.client).start();
            } else {
                System.err.println("server is currently unavailable");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        TextField username = new TextField();
        tools.maxCharLength(username, 20);
        username.setLayoutX(45);
        username.setLayoutY(70);

        usernameWarning = new Text("");
        usernameWarning.setLayoutX(45);
        usernameWarning.setLayoutY(105);
        usernameWarning.setFill(Color.RED);
        usernameWarning.setFont(new Font("consolas", 13));

        EventHandler<KeyEvent> usernameEventFilter = new EventHandler<KeyEvent>() {

            //if you type a space or a exclamation point then put up an error message

            @Override
            public void handle(KeyEvent event) {
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

        username.addEventFilter(KeyEvent.KEY_RELEASED, usernameEventFilter);

        TextField password = new TextField();
        tools.maxCharLength(username, 20);
        password.setLayoutX(45);
        password.setLayoutY(130);

        Text passwordWarning = new Text("");
        passwordWarning.setLayoutX(45);
        passwordWarning.setLayoutY(165);
        passwordWarning.setFill(Color.RED);
        passwordWarning.setFont(new Font("consolas", 13));

        EventHandler<KeyEvent> passwordEventFilter = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                //if you type a space or a exclamation point then put up an error message

                try {
                    String text = password.getText();

                    if (text.contains(" ")) {
                        passwordWarning.setText("cannot use space");

                        password.setText(text.replace(" ", ""));

                    } else if (text.contains("!")) {
                        passwordWarning.setText("cannot use ! symbol");
                        password.setText(text.replace("!", ""));
                    } else {
                        passwordWarning.setText("");
                    }
                } catch (StringIndexOutOfBoundsException e) {
                }
            }
        };

        password.addEventFilter(KeyEvent.KEY_RELEASED, passwordEventFilter);

        Text usernameText = new Text("username");
        usernameText.setFont(new Font("consolas", 15));
        usernameText.setFill(Color.GREEN);
        usernameText.setLayoutX(45);
        usernameText.setLayoutY(60);

        Text passwordText = new Text("password");
        passwordText.setFont(new Font("consolas", 15));
        passwordText.setFill(Color.GREEN);
        passwordText.setLayoutX(45);
        passwordText.setLayoutY(120);

        Text profileText = new Text("choose a new profile color");
        profileText.setFont(new Font("consolas", 15));
        profileText.setFill(Color.GREEN);
        profileText.setLayoutX(45);
        profileText.setLayoutY(180);

        Text signupText = new Text("create an account");
        signupText.setLayoutX(15);
        signupText.setLayoutY(25);
        signupText.setFont(new Font("colsolas", 23));
        signupText.setFill(Color.GREEN);

        HBox colorSelection = new HBox(10);
        colorSelection.setLayoutX(45);
        colorSelection.setLayoutY(190);

        Button blue = new Button();
        blue.setPrefWidth(35);
        tools.setColorButton(this, blue, "blue");
        blue.setStyle("-fx-background-color: blue; -fx-background-radius: 5;");

        Button red = new Button();
        red.setPrefWidth(35);
        tools.setColorButton(this, red, "red");
        red.setStyle("-fx-background-color: red; -fx-background-radius: 5;");

        Button green = new Button();
        green.setPrefWidth(35);
        tools.setColorButton(this, green, "green");
        green.setStyle("-fx-background-color: green; -fx-background-radius: 5;");

        Button orange = new Button();
        orange.setPrefWidth(35);
        tools.setColorButton(this, orange, "orange");
        orange.setStyle("-fx-background-color: orange; -fx-background-radius: 5;");

        Button black = new Button();
        black.setPrefWidth(35);
        tools.setColorButton(this, black, "black");
        black.setStyle("-fx-background-color: black; -fx-background-radius: 5;");

        colorSelection.getChildren().add(blue);
        colorSelection.getChildren().add(red);
        colorSelection.getChildren().add(green);
        colorSelection.getChildren().add(orange);
        colorSelection.getChildren().add(black);

        Button create = new Button("create");
        create.setLayoutX(45);
        create.setLayoutY(240);
        create.setStyle("-fx-background-color: #8BC34A; -fx-background-radius: 5;");
        create.setOnAction(new EventHandler<ActionEvent>() {

            //adds a user to the database
            @Override
            public void handle(ActionEvent event) {

                try {

                    if (!username.getText().isEmpty() && !password.getText().isEmpty() && !color.isEmpty()) {
                        String name = username.getText();
                        String pass = password.getText();

                        tools.checkIfNameTaken(name);

                    /*
                        sends a request to the database to see if the username is taken
                     */
                        //System.out.println("test " + Client.serverResponse);

                        Thread.sleep(300);

                        if (Client.serverResponse.equals("!nametaken!false")) {
                            System.out.println("[SIGNUPWINDOW] creating user");
                            tools.createUser(name, pass, color);
                        } else if (Client.serverResponse.equals("!nametaken!true")) {
                            usernameWarning.setText("username is unavailable");
                        }
                    } else {
                        if (username.getText().isEmpty()) {
                            usernameWarning.setText("field not filled out");
                        }
                        if (password.getText().isEmpty()) {
                            passwordWarning.setText("field not filled out");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        Button login = new Button("back to login");
        login.setLayoutX(40);
        login.setLayoutY(270);
        login.setStyle("-fx-background-color: whitesmoke; -fx-underline: true;");
        login.setOnAction(new EventHandler<ActionEvent>() {

            //changes the scene to login screen
            @Override
            public void handle(ActionEvent event) {
                tools.changeScene(Main.login);
            }
        });

        root.getChildren().add(signupText);
        root.getChildren().add(usernameText);
        root.getChildren().add(passwordText);
        root.getChildren().add(profileText);
        root.getChildren().add(username);
        root.getChildren().add(usernameWarning);
        root.getChildren().add(password);
        root.getChildren().add(passwordWarning);
        root.getChildren().add(colorSelection);
        root.getChildren().add(create);
        root.getChildren().add(login);
    }
}
