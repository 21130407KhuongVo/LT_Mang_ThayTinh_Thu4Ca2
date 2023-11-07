package SocketBasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Server {

    public static void serve() {
        try {
            ServerSocket server = new ServerSocket(1025);
            System.out.println("server is ready...");
            Socket socket = server.accept();
// getting from client
            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request = bf.readLine();
            if (request != null) {
                System.out.println("Received from a client: " + request);
//sending to client
                PrintStream ps = new PrintStream(socket.getOutputStream());
                ps.println(request.toUpperCase());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        serve();
    }
}
