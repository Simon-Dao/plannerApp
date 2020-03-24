package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.Main;

public class AppUI {

    public UITools tools = new UITools();

    private static TextField textField;

    public Contacts contactsArea;
    public MessageArea messageArea;

    public AppUI(VBox root) {

        Pane searchBar = createTopHalf();
        searchBar.setId("me");

        SplitPane messageAndContacts = createBottomHalf();

        root.getChildren().add(searchBar);
        root.getChildren().add(messageAndContacts);
    }

    public Pane createTopHalf() {
        Pane pane = new Pane();
        pane.setPrefHeight(60);

        Text test = new Text("hello");
        pane.getChildren().add(test);

        return pane;
    }

    public SplitPane createBottomHalf() {
        SplitPane messageAndContacts = new SplitPane();
        messageAndContacts.setPrefHeight(440);
        //left side
        VBox contacts = new VBox();
        //contacts.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        contactsArea = new Contacts(contacts);

        //right side
        VBox rightSide = new VBox();

        //chat area
        ScrollPane messages = new ScrollPane();
        messageArea = new MessageArea(messages);

        //bottom text field
        Pane sendMessage = new Pane();

        textField = new TextField();
        tools.maxCharLength(textField, 30);
        textField.setPromptText("send a message!");

        Button send = new Button("send");
        send.setLayoutX(160);
        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (textField.getText().length() != 0) {
                    System.out.println(Main.localUser.getColor() == null);
                    messageArea.addMessage(Main.localUser.getName(), textField.getText(), Main.localUser.getColor());
                    tools.sendMessage(Main.localUser.getName(), Main.localUser.getColorInString(), textField.getText());
                    textField.clear();
                }
            }
        });

        send.setContentDisplay(ContentDisplay.RIGHT);

        sendMessage.getChildren().add(textField);
        textField.getParent().requestFocus();
        sendMessage.getChildren().add(send);

        AnchorPane messageBox = new AnchorPane();
        messageBox.setBottomAnchor(sendMessage, (double) 35);
        messageBox.setTopAnchor(sendMessage, (double) 10);
        messageBox.setLeftAnchor(sendMessage, (double) 10);
        messageBox.setRightAnchor(sendMessage, (double) 30);

        messageBox.getChildren().add(sendMessage);

        messageAndContacts.getItems().add(contacts);
        messageAndContacts.getItems().add(rightSide);

        rightSide.getChildren().add(messages);
        rightSide.getChildren().add(messageBox);

        return messageAndContacts;
    }
}
