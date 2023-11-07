import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1025);
            System.out.println("Server is ready...");
            Socket socket = serverSocket.accept();
            // get from Client
            BufferedReader inServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            while (true) {
                if ((line = inServer.readLine()) != null) System.out.println("From client: " + line);
                if (line.equals("EXIT")) break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
