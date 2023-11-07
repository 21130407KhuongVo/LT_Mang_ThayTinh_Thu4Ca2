import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1025);
            System.out.println("Connected to server...");

            // send
            PrintWriter outClient = new PrintWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            String input;
            while (true) {
                System.out.print("Enter your message: ");
                outClient.println(input = scanner.nextLine());
                outClient.flush();
                if (input.equals("EXIT")) {
                    break;
                }
            }
            outClient.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
