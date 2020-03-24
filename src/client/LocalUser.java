package client;

import javafx.scene.paint.Color;

public class LocalUser {
    private String name;
    private String password;
    private String color;

    public void setName(String name) { this.name = name; }

    public void setPassword(String password) { this.password = password; }

    public void setColor(String color) { this.color = color; }

    public String getName() { return name; }

    public String getPassword() { return password; }

    public Color getColor() {

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

    public String getColorInString() {
        return color;
    }
}
