import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {

        try {
            Socket clientSoc = new Socket("localhost", 1025);
            // tao inputstream noi voi socket
            BufferedReader inClient = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));

            String line;
            while((line= inClient.readLine())!=null){
                System.out.println("From server: "+ line);
            }
            inClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
