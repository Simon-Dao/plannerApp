package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class ClientHandler implements Runnable, Serializable {

    ArrayList<ClientHandler> clients;

    private Socket client;

    private BufferedReader in;

    public PrintWriter out;

    public int id;

    /*
        instantiate the buffered reader and the print writer
     */
    ClientHandler(Socket client, ArrayList<ClientHandler> clients, int id) throws IOException {
        this.id = id;
        this.client = client;
        this.clients = clients;

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }

    /*
       waits for socket to receive a message
       if the message start with !userinfo! then it means user is logging in
       if it starts with !newuser! then the user is signing up
       if it doesn't start with that then it is a message to other clients

       if user disconnects then server user count goes down by 1
       finally closes io

     */
    @Override
    public void run() {
        try {
            while (true) {

                String request = in.readLine();

                System.out.println(request + " " + id);

                if (request.startsWith("!userinfo!")) {
                    //make code that checks if that account exists
                    String name = request.substring(10, request.indexOf(";"));
                    String password = request.substring(request.indexOf(";") + 1);

                    //TODO make the database return login errors like name doesn't exist and that stuff

                    //System.out.printf("%s%s",name, password);

                    if (Server.dataBase.verifyLoginData(name, password)) {
                        out.println("!userIsVerified!true !userColor!" + Server.dataBase.getUser(name).color);
                    } else {
                        out.println("!userIsVerified!false");
                    }

                } else if (request.startsWith("!newuser!")) {

                    String[] userData = parseRequest(request);

                    Server.dataBase.addRecord(this, userData[0], userData[1], userData[2]);
                    //Server.dataBase.getRecord();

                } else if (request.startsWith("!checkDupes!")) {

                    //parse request and find out if the name is taken
                    String name = request.substring(12);

                    //System.out.println("[CLIENTHANDLER] " + name + " is taken: " + Server.dataBase.checkForDupe(name));

                    if (Server.dataBase.checkForDupe(name)) {
                        out.println("!nametaken!true");
                    } else if (!Server.dataBase.checkForDupe(name)) {
                        out.println("!nametaken!false");
                    }
                } else if (request.startsWith("!name!")) {
                    //System.out.println("[CLIENTHANDLER] sending message "+request);
                    int[] div = {request.indexOf(" "), request.substring(request.indexOf(" ") + 1).indexOf(" ") + request.indexOf(" ")};
                    String name = request.substring(6, request.indexOf(" "));
                    String color = request.substring(div[0] + 8, div[1] + 1);
                    String message = request.substring(div[1] + 11);
                    outToAll(name, color, message);
                }
            }
        } catch (Exception e) {
            Server.USER_COUNT--;
        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void outToAll(String name, String color, String msg) {
        for (ClientHandler c : clients) {

            System.out.println(clients.size());

            if (c.id != this.id) {
                c.out.println("!name!" + name + " !color!" + color + " !message!" + msg);
            }
        }
    }

    private String[] parseRequest(String str) {

        int[] div = {str.indexOf(" "), str.substring(str.indexOf(" ") + 1).indexOf(" ") + str.indexOf(" ")};

        String name = str.substring(9, str.indexOf(" "));
        String password = str.substring(div[0] + 1, div[1] + 1);
        String color = str.substring(div[1] + 2);

        String[] result = {name, password, color};

        return result;
    }
}

