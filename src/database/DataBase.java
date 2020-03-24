package database;

import server.ClientHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class DataBase {

    HashMap<String, User> table = new HashMap<>();

    private ArrayList<String> keys = new ArrayList<>();

    public DataBase() {
        User simoan = new User("simon", "password", "green");

        table.put("simon", simoan);
        keys.add("simon");
    }

    /*
        checks if the username already exists if not then add the user
     */
    public void addRecord(ClientHandler client, String name, String password, String color) {
        User newUser = new User(name, password, color);

        if (!table.containsKey(name)) {
            keys.add(name);
            table.put(name, newUser);
        }
    }

    public boolean checkForDupe(String name) {

        boolean result = true;

        if (!keys.contains(name)) {
            result = false;
        }

        return result;
    }

    public void getRecords() {

        System.out.println();
        for (int i = 0; i < table.size(); i++) {
            System.out.println("[SERVER] name     " + table.get(keys.get(i)).name);
            System.out.println("[SERVER] password " + table.get(keys.get(i)).password);
            System.out.println("[SERVER] color    " + table.get(keys.get(i)).color + "\n");
        }
        System.out.println();
    }

    public User getUser(String key) {
        return table.get(key);
    }

    public boolean verifyLoginData(String name, String password) {

        boolean verified = false;

        if (table.containsKey(name)) {
            if (table.get(name).password.equals(password)) {
                verified = true;
            } else
                verified = false;
        }
        return verified;
    }
}
