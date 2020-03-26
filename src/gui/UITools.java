package gui;


import client.Client;
import client.LocalUser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.Main;

import java.io.IOException;
import java.net.Socket;

public class UITools {

    public void maxCharLength(TextField field, int length) {

        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                //is the text is not greater than the max and is not empty
                if (field.getText().length() > length) {
                    String s = field.getText().substring(0, length);
                    field.setText(s);
                }
            }
        });
    }

    public void sendMessage(String username, String color, String message) {
        Main.client.out.println("!name!"+username+" !color!"+color+" !message!"+message);
        Main.client.out.flush();
    }

    public void sendUserData(String username, String password) {
        Main.client.out.println("!userinfo!" + username + ";" + password);
    }

    public void sendNewAccountForm(String username, String password, String color) {
        Main.client.out.println("!newuser!" + username + " " + password + " " + color);
    }

    public void checkIfNameTaken(String name) {
        Main.client.out.println("!checkDupes!" + name);
    }

    public boolean serverIsAlive(String IP, int PORT) {

        try (Socket s = new Socket(IP, PORT)) {
            return true;
        } catch (IOException e) {
        }
        return false;
    }

    public void setColorButton(SignUpWindow window, Button button, String color) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.color = color;
            }
        });
    }

    public void createUser(String username, String password, String profile) {

        if (!username.isEmpty() && !password.isEmpty()) {

            Main.stage.close();

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Main.stage.show();
            Main.stage.setScene(Main.messengerApp);

            sendNewAccountForm(username, password, profile);
        }
    }

    public void changeScene(Scene scene) {
        Main.stage.close();

        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Main.stage.setScene(scene);
        Main.stage.show();
    }

    public void startClient() {
        try {
            if (serverIsAlive(Client.IP, Client.PORT)) {
                Main.client = new Client();
                new Thread(Main.client).start();
            }
        } catch (Exception e) {
            System.err.println("server is currently unavailable");
        }
    }

    public Color StringToColor(String color) {

        Color userColor = null;

        switch(color) {
            case "blue":
                userColor = Color.BLUE;
                break;
            case "red":
                userColor = Color.RED;
                break;
            case "green":
                userColor = Color.GREEN;
                break;
            case "orange":
                userColor = Color.ORANGE;
                break;
            case "black":
                userColor = Color.BLACK;
                break;
        }
        return userColor;
    }

    public void instantiateLocalData() {
        Main.localUser = new LocalUser();
    }
}
