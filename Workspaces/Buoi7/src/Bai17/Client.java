package Bai17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void connect() {
        try {
            Socket socket = new Socket("localhost", 1025);
            // get from server
            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String input;
            System.out.println("From server----");
            while ((input = bf.readLine())!= null) {
                System.out.println("\t" + input);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        connect();
    }
}
