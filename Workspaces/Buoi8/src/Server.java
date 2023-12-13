import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.StringTokenizer;

public class Server {

    public static void server() {
        try {
            ServerSocket server = new ServerSocket(1025);
            System.out.println("Server is ready...");
            Socket socket = server.accept();

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            //getting from client
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request, param = "";
            boolean login = false, loop = true;
            String lastUser = "";
            String response = "";
            DAO dao = new DAO();

            while (!login && loop) {
                request = "";
                param = "";
                StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
                request = tokenizer.nextToken().toUpperCase();
                // get param
                while (tokenizer.hasMoreTokens()) {
                    param += tokenizer.nextToken() + " ";
                }
                param = param.trim();
                System.out.println("=" + param + "=");
                switch (request) {
                    case "USERNAME":
                        if (dao.findUname(param)) {
                            response = "Hello " + param;
                            lastUser = param;
                        } else response = "ERROR: " + param + " was not found!";
                        break;
                    case "PASSWORD":
                        if (lastUser.equals("")) {
                            response = "ERROR: Please enter your username first!";
                        } else {
                            if (dao.login(lastUser, param)) {
                                response = "\nWelcome back " + lastUser + "\n";
                                for (int i = 0; i < 15; i++) {
                                    response += "=";
                                }
                                login = true;
                            } else response = "ERROR: Password is incorrect!";
                        }
                        break;
                    case "QUIT":
                        response = "Disconnecting......[OK]";
                        printWriter.println(response);
                        // close server
                        bufferedReader.close();
                        printWriter.close();
                        socket.close();
                        loop = false;
                        break;
                    default:
                        response = "ERROR: command \'" + request + "\' not found.";
                        break;
                }
                printWriter.println(response);
            }

            while (login) {
                request = "";
                param = "";
                StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
                request = tokenizer.nextToken().toUpperCase();
                // get param
                while (tokenizer.hasMoreTokens()) {
                    param += tokenizer.nextToken() + " ";
                }
                param = param.trim();
                System.out.println("=" + param + "=");
                switch (request) {
                    case "FINDBYNAME":
                        printList(printWriter, dao.findByName(param));
                        break;
                    case "FINDBYID":
                        printList(printWriter, dao.findById(param));
                        break;
                    case "QUIT":
                        response = "Exit......[OK]";
                        printWriter.println(response);
                        // close server
                        bufferedReader.close();
                        printWriter.close();
                        socket.close();
                        loop = true;
                        break;
                    default:
                        response = "ERROR: command \'" + request + "\' not found.";
                        break;
                }
                printWriter.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printList(PrintWriter printWriter, List<Student> list) {
        printWriter.println("===" + list.size() + " results===");
        for (Student s : list) {
            printWriter.println("-" + s.toString());
        }
    }

    public static void main(String[] args) {
        server();
    }
}
