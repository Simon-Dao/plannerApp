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
import main.Main;

    /*
        creates the area where messages can be displayed
     */

public class MessageArea {

    public ScrollPane scrollPane;
    public VBox textArea;

    public MessageArea(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;

        textArea = new VBox();
        for(int i=0; i<25; i++) {
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

    public void addMessage(String text, Color color) {
        Pane p = new Pane();

        Circle c = new Circle(10, color);
        c.setTranslateX(15);
        c.setTranslateY(15);

        Text l = new Text(text);
        l.setLayoutX(30);
        l.setLayoutY(20);
        l.setFill(Color.BLACK);
        l.setFont(new Font("consolas", 15));
        p.getChildren().add(c);
        p.getChildren().add(l);

        textArea.getChildren().add(p);
    }
}
