package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import popup.TextFields;

import java.util.ArrayList;

//TODO make the program search depending on what the input starts with

public class SearchBarSection {

    public TextField search;

    private String[] suggestions = null;

    public SearchBarSection(Pane pane) {
        search = new TextField();
        search.setPromptText("search for others!");
        search.setLayoutX(10);
        search.setLayoutY(26);

        EventHandler searchBarEvent = new EventHandler() {
            @Override
            public void handle(Event event) {

                int suggestionCount = 0;
                ArrayList<Integer> indexes = new ArrayList<>();

                for (int i = 0; i < Test.users.length; i++) {
                    if(Test.users[i].toLowerCase().startsWith(search.getText())) {
                        indexes.add(i);
                        suggestionCount++;
                    }
                }

                suggestions = new String[suggestionCount];

                for (int i = 0; i < suggestions.length; i++) {
                   suggestions[i] = Test.users[indexes.get(i)];
                   //System.out.println(suggestions[i]);
                }
                System.out.println();
                for (String j: suggestions) {
                    System.out.println(j);
                }

                TextFields.bindAutoCompletion(search,suggestions);

            }

        };

       //search.addEventFilter(KeyEvent.KEY_RELEASED, searchBarEvent);

       //TextFields.bindAutoCompletion(search,suggestions);
        String[] test = {"by","hi","try","guy","aasd"};

        TextFields.bindAutoCompletion(search,test);
        pane.getChildren().add(search);
    }
}
