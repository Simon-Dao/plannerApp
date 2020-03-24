package gui;

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

public class Contacts {

    VBox layout;
    ScrollPane friends;

    public Contacts(VBox contacts) {
        this.layout = contacts;
        layout.setId("ContactsPane");

        friends = createTopSection();
        Pane userInfo = createBottomSection();

        layout.getChildren().add(friends);
        layout.getChildren().add(userInfo);
    }

    private ScrollPane createTopSection() {
        /*
        Pane top = new Pane();
        top.setPrefHeight(100);

        Text topText = new Text("Search for people");

        TextField field = new TextField();
        field.setPromptText("find people!");
        field.setPrefWidth(130);
        field.setLayoutX(10);
        field.setLayoutY(40);

        top.getChildren().add(field);
        */
        ScrollPane root = new ScrollPane();

        VBox top = new VBox();
        top.setId("FriendScrollPane");
        top.setPrefHeight(420);

        VBox contact = new VBox();

        for(int i = 0; i<1; i++) {

            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: white;");
            //pane.setPrefHeight(80);
            pane.setMinHeight(40);

            Circle profile = new Circle(10);
            profile.setLayoutX(15);
            profile.setLayoutY(20);

            Text test = new Text("test");
            test.setFont(new Font("consolas", 14));
            test.setLayoutX(100);
            test.setLayoutY(30);
            test.setFill(Color.BLACK);

            Text test2 = new Text("whats up my diggaly foo");
            test.setFont(new Font("consolas", 13));
            test.setLayoutX(15);
            //test.setLayoutY(10);
            test.setFill(Color.BLACK);

            Rectangle bottom = new Rectangle(0,25,300,1);
            bottom.setFill(Color.GREY);

            pane.getChildren().add(profile);
            pane.getChildren().add(test);
            pane.getChildren().add(test2);
            //pane.getChildren().add(bottom);
            contact.getChildren().add(pane);
        }

        top.getChildren().add(contact);

        root.setContent(top);

        return root;
    }

    private Pane createBottomSection() {
        Pane bottom = new Pane();
        bottom.setId("bottomPane");
        bottom.setPrefHeight(80);

        return bottom;
    }
}
