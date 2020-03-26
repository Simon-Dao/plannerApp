package gui;

import client.Client;
import client.Friend;
import client.LocalUser;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;

import java.util.ArrayList;

public class Contacts {

    private VBox layout;
    private VBox contact;
    private ScrollPane friends;
    private UITools tools = new UITools();

    public Contacts(VBox contacts) {
        this.layout = contacts;
        layout.setId("ContactsPane");

        friends = createTopSection();
        Pane userInfo = createBottomSection();

        layout.getChildren().add(friends);
        layout.getChildren().add(userInfo);
    }

    private ScrollPane createTopSection() {
        ScrollPane root = new ScrollPane();

        VBox top = new VBox();
        top.setId("FriendScrollPane");
        top.setPrefHeight(420);
        contact = new VBox();

        addContacts();

        top.getChildren().add(contact);

        root.setContent(top);

        return root;
    }

    private Pane createBottomSection() {
        Pane bottom = new Pane();
        bottom.setId("bottomPane");
        bottom.setPrefHeight(80);

        Circle profileColor = new Circle(20,20,15,Main.localUser.getColor());

        //name color settings
        Text username = new Text(Main.localUser.getName());
        username.setFont(new Font("consolas", 15));
        username.setFill(Color.WHITESMOKE);
        username.setLayoutX(40);
        username.setLayoutY(23);

        bottom.getChildren().add(profileColor);
        bottom.getChildren().add(username);

        return bottom;
    }

    private void addContacts() {

        ArrayList<Friend> friends = Main.localUser.friends;

        for(int i = 0; i<friends.size(); i++) {
            addContact(friends.get(i).username,friends.get(i).color);
        }
    }

    private void addContact(String username, String color) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #DCEAEA ;");
        pane.setPrefWidth(226);
        pane.setMinHeight(50);
        pane.setPrefHeight(50);
        pane.setMaxHeight(50);

        Circle profile = new Circle(15);
        profile.setLayoutX(20);
        profile.setLayoutY(22);
        profile.setFill(tools.StringToColor(color));

        Text name = new Text(username);
        name.setFont(new Font("consolas", 17));
        name.setLayoutX(40);
        name.setLayoutY(18);
        name.setFill(Color.BLACK);

        Text message = new Text("message");
        message.setFont(new Font("consolas", 12));
        message.setLayoutX(40);
        message.setLayoutY(35);
        message.setFill(Color.BLUE);

        pane.getChildren().add(profile);
        pane.getChildren().add(name);
        pane.getChildren().add(message);
        contact.getChildren().add(pane);
    }
}
