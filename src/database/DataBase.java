package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

public class DataBase {

    HashMap<String, User> table = new HashMap<>();

    /*
        checks if the username already exists if not then add the user
     */
    public void addRecord(String name, String password, String color) {
        User newUser = new User(name,password,color);

        if( !table.containsKey(name)) {
            table.put(name, newUser);
        } else {

        }
    }

    public void getRecord() {

    }
}
