package UploadFile;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            ServerSocket serverSocket = new ServerSocket(1025);
            System.out.println("Server is ready...");
            Socket socket = serverSocket.accept();

            dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(new FileOutputStream(dis.readUTF()));

                byte[]bff = new byte[10240];
                int data;
                while ((data = dis.read(bff))!=-1){
                    dos.write(bff,0,data);
                }
                dis.close();
                dos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
