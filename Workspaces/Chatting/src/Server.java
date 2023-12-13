import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1025);
            System.out.println("Server is ready...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected from: " + socket.getInetAddress().getHostAddress());

                Thread thread = new Thread(new ClientSimple(socket));
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

class ClientSimple implements Runnable {
    private Socket socket;

    public ClientSimple(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // get from Client
            BufferedReader inServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            while (true) {
                if ((line = inServer.readLine()) != null) {
                    System.out.println("From client: " + line);
                    if (line.equals("EXIT")) {
                        break;
                    }
                }
            }

            // Đóng tài nguyên
            inServer.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}