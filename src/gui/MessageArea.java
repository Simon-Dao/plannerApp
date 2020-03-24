package gui;

import client.Client;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

    /*
        creates the area where messages can be displayed
     */

public class MessageArea {

    public ScrollPane scrollPane;
    public VBox textArea;

    public MessageArea(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;

        textArea = new VBox();
        for (int i = 0; i < 25; i++) {
            textArea.getChildren().add(makeSpace());
        }

        scrollPane.setContent(textArea);
        scrollPane.vvalueProperty().bind(textArea.heightProperty());
    }

    public Pane makeSpace() {
        Pane p = new Pane();

        Text l = new Text("");
        l.setLayoutY(20);
        p.getChildren().add(l);

        return p;
    }

    public void addMessage(String username, String text, Color color) {
        Pane p = new Pane();

        Circle c = new Circle(15, color);
        c.setTranslateX(25);
        c.setTranslateY(15);

        Text name = new Text(username);
        name.setLayoutX(45);
        name.setLayoutY(9);
        name.setFill(Color.BLACK);
        name.setFont(new Font("consolas", 12));

        Text message = new Text(text);
        message.setLayoutX(45);
        message.setLayoutY(25);
        message.setFill(Color.BLACK);
        message.setFont(new Font("consolas", 15));

        p.getChildren().add(c);
        p.getChildren().add(name);
        p.getChildren().add(message);

        textArea.getChildren().add(p);
    }
}
