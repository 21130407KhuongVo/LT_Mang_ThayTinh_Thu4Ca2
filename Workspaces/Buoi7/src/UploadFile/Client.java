package UploadFile;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {

    private String req, source, des;

    private void scanner() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your command: ");
        String command = scanner.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(command);
        tokenizer.hasMoreTokens();
        this.req = tokenizer.nextToken().toLowerCase();
        this.source = tokenizer.nextToken();
        this.des = tokenizer.nextToken();
    }

    private void controller(){
        this.scanner();
        switch (req){
            case "upload":
                upload();
                break;
            case "download":
//                dow
                break;
//            case "upload":
//
//                break;
        }

    }

    private void upload() {
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            Socket socket = new Socket("localhost", 1025);
            dis = new DataInputStream(new FileInputStream(source));
            dos = new DataOutputStream(socket.getOutputStream());

            dos.writeUTF(this.des);
            dos.flush();

            byte[] bff = new byte[10240];
            int data;
            while ((data = dis.read(bff))!=-1){
                dos.write(bff,0,data);
            }
            dis.close();
            dos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.controller();
    }
}
