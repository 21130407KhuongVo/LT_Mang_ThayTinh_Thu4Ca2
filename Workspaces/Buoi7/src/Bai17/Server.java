package Bai17;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void load(String path) {
        try {
            ServerSocket serverSocket = new ServerSocket(1025);
            System.out.println("Server is ready...");
            Socket socket = serverSocket.accept();

            BufferedReader bf = new BufferedReader(new FileReader(path));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            String line;
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
                pw.println(line);
                pw.flush();
            }
            bf.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        String path = "/home/khuongvo/Documents/Git/LT_Mang_ThayTinh_Thu4Ca2/sv.txt";
        server.load(path);
    }
}
