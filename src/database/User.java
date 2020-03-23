package database;

import java.util.ArrayList;

public class User {

    public String name;
    public String password;
    public String color;

    public ArrayList<User> friends = new ArrayList<>();

    public User(String name, String password, String color) {
        this.name = name;
        this.password = password;
        this.color = color;
    }
}
