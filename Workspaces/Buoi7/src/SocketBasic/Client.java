package SocketBasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void connect() {
        try {
            Socket socket = new Socket("localhost", 1025);
//            Sending to server
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println("Study and share");

            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = bf.readLine();
            if (response != null) {
                System.out.println("SocketBasic.Server answered: " + response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        connect();
    }
}
