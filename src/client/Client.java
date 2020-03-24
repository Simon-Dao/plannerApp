package client;

import gui.UITools;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import main.Main;
import server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {

    public static int PORT = 5002;
    public static String IP = "192.168.0.21";

    public static BufferedReader in;
    public PrintWriter out;
    private Socket server;
    private UITools tools = new UITools();

    public static String serverResponse = "!nametaken!false";

    public Client() throws IOException {

        server = new Socket(IP, PORT);
        out = new PrintWriter(server.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    private Color stringToColor(String str) {

        Color userColor = null;

        switch(str) {
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

    @Override
    public void run() {

        try {
            while (true) {

                /*
                    takes in a response from the server and prints it out to the user
                 */
                serverResponse = in.readLine();
                System.out.println("[CLIENT] "+serverResponse);
                if (serverResponse.startsWith("!name!")) {
                    int[] div = {serverResponse.indexOf(" "), serverResponse.substring(serverResponse.indexOf(" ") + 1).indexOf(" ") + serverResponse.indexOf(" ")};
                    String name = serverResponse.substring(6, serverResponse.indexOf(" "));
                    String color = serverResponse.substring(div[0] + 8, div[1] + 1);
                    String message = serverResponse.substring(div[1] + 11);
                    Platform.runLater(() -> Main.app.messageArea.addMessage(name,message, stringToColor(color)));
                }

                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
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
