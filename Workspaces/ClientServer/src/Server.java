import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // tao socket server tai port 80
            ServerSocket socServer = new ServerSocket(1025);

            while (true) {
                // Cho yeu cau tu client
                Socket serverSoc = socServer.accept();

                // Tao inputstream
                BufferedReader inServer = new BufferedReader(new InputStreamReader(serverSoc.getInputStream()));

                // Tao outputstream
                PrintWriter outServer = new PrintWriter(serverSoc.getOutputStream(), true);

                String line = inServer.readLine();
                while (line != null) {
                    outServer.write(line);
                    line = inServer.readLine();
                }
                inServer.close();
                outServer.close();
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
