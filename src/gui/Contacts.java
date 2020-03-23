package gui;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Contacts {

    VBox layout;

    public Contacts(VBox contacts) {
        this.layout = contacts;

        Pane topSection = new Pane();

        //Color greenTint = Color.web("#8BC34A");
        //topSection.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        //Text icon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.THUMBS_UP, "4em");
        ScrollPane friends = new ScrollPane();

        layout.getChildren().add(topSection);
        layout.getChildren().add(friends);
    }
}
