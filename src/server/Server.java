package server;

import database.DataBase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int PORT = 5002;

    public static int USER_COUNT = 0;

    public int id;

    private final int THREADCOUNT = 5;

    private ExecutorService pool = Executors.newFixedThreadPool(THREADCOUNT);

    private ArrayList<ClientHandler> clients = new ArrayList<>();

    public static DataBase dataBase;

    /*
        creates a new text file that will store all the user information
        waits for client to connect to the server
        creates a new client handler for each client
        runs the client handler
     */
    public Server() throws IOException {

        ServerSocket server = new ServerSocket(PORT);

        dataBase = new DataBase();

        while (true) {
            Socket client = server.accept();
            USER_COUNT++;

            id = USER_COUNT;
            ClientHandler clientThread = new ClientHandler(client, clients, id);

            clients.add(clientThread);
            pool.execute(clientThread);

            // if(clients.size() > 0) {
            //  dataBase.getRecord();
            //}
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}
