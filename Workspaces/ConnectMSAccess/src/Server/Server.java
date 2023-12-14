package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import DAO.DAO_Student;
import DAO.DAO_User;
import Model.Student;

public class Server {
	public static void server() {
		try {
			ServerSocket serverSocket = new ServerSocket(54321);
			while (true) {
				Socket socket = serverSocket.accept();
				ClientHandler handler = new ClientHandler(socket);
				handler.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void printList(PrintWriter printWriter, ArrayList<Student> list) {
		printWriter.println("===" + list.size() + " results===");
		for (Student s : list) {
			printWriter.println("-" + s.toString());
		}
		printWriter.println("=========");
	}

	private static ArrayList<String> getCommand(String line) {
		ArrayList<String> commands = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(line = line.trim());
		commands.add(tokenizer.nextToken());

		while (tokenizer.hasMoreTokens()) {
			commands.add(tokenizer.nextToken());
		}

		return commands;
	}

	public static void main(String[] args) {
		server();
	}
}