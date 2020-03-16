package client;

import gui.UITools;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import main.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {

    public static int PORT = 5002;
    public static String IP = "192.168.0.21";

    public BufferedReader in;
    public PrintWriter out;
    private Socket server;
    private UITools tools = new UITools();

    public static String serverResponse;

    public Client() throws IOException {
            server = new Socket(IP, PORT);
            out = new PrintWriter(server.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    @Override
    public void run() {

        try {
            while(true) {
                serverResponse = in.readLine();
                System.out.println(serverResponse);
                Platform.runLater( () -> Main.app.messageArea.addMessage(serverResponse, Color.RED));

                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
