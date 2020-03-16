package gui;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class WindowControls {

    Stage stage;
    Scene scene;

    /*
       closes the window when esc is pressed
     */

    public WindowControls(Stage stage, Scene scene) {
        this.scene = scene;
        this.stage = stage;

        scene.addEventFilter(KeyEvent.KEY_PRESSED, keys -> {
            if(keys.getCode() == KeyCode.ESCAPE) {
                stage.close();
                System.exit(0);
            }
        });
    }
}
