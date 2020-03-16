package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class ClientHandler implements Runnable, Serializable {

    ArrayList<ClientHandler> clients;

    private Socket         client;

    private BufferedReader in;

    private PrintWriter    out;

    public int id;

    /*
        instantiate the buffered reader and the print writer
     */
    ClientHandler(Socket client, ArrayList<ClientHandler> clients, int id) throws IOException {
        this.id = id;
        this.client = client;
        this.clients = clients;

        in  = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter   (client.getOutputStream(), true);
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
        try{
            while(true) {

                String request = in.readLine();

                if(request.startsWith("!userinfo!")) {
                    //make code that checks if that account exists

                } else if(request.startsWith("!newuser!")){
                    //make code that adds a person to the accounts
                    String name = request.substring(9, request.indexOf(" "));


                } else {
                    //System.out.println("[CLIENTHANDLER] sending message "+request);
                    outToAll(id+" "+request);
                }
            }
        } catch(Exception e) {
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

    private void outToAll(String msg) {
        for(ClientHandler c : clients) {
            if(c.id != this.id) {
                c.out.println(msg);
            }
        }
    }
}

