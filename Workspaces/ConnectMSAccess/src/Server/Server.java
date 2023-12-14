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
		try (ServerSocket server = new ServerSocket(1025);
				Socket socket = server.accept();
				PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

			System.out.println("Server is ready...");

			String request, param = "";
			boolean login = false, loop = true;
			String lastUser = "";
			String response = "";

			while (loop) {
				while (!login) {
					ArrayList<String> commands = getCommand(bufferedReader.readLine());
					request = commands.get(0).toUpperCase();

					if (commands.size() == 1) {
						if (request.equals("QUIT")) {
							response = "Disconnecting......[OK]";
							loop = false;
						} else {
							response = "ERROR: command \'" + request + "\' not found.";
						}
					} else {
						param = commands.get(1).trim();
						switch (request) {
						case "USERNAME":
							if (DAO_User.selectByUsername(param)) {
								response = "Hello " + param;
								lastUser = param;
							} else {
								response = "ERROR: " + param + " was not found!";
							}
							break;
						case "PASSWORD":
							if (lastUser.equals("")) {
								response = "ERROR: Please enter your username first!";
							} else {
								if (DAO_User.select(lastUser, param)) {
									response = "\nWelcome back " + lastUser + System.lineSeparator();
									response += "=".repeat(15); // Java 11+
									login = true;
									System.out.println(response);
									break;
								} else {
									response = "ERROR: Password is incorrect!";
								}
							}
							break;
						default:
							response = "ERROR: command \'" + request + "\' not found.";
							break;
						}
						printWriter.println(response);
					}
				}

				while (login) {
					ArrayList<String> commands = getCommand(bufferedReader.readLine());
					request = commands.get(0).toUpperCase();
					param = commands.get(1).trim();

					if (commands.size() == 1) {
						if (request.equals("QUIT")) {
							response = "Exit......[OK]";
							login = false;
						} else {
							response = "ERROR: command \'" + request + "\' not found.";
						}
					} else {
						switch (request) {
						case "FINDBYNAME":
							printList(printWriter, DAO_Student.findByName(param));
							break;
						case "FINDBYID":
							ArrayList<Student> list = new ArrayList<>();
							Student student = DAO_Student.findById(param);
							if (student != null) {
								list.add(student);
								printList(printWriter, list);
							}
							break;
						default:
							response = "ERROR: command \'" + request + "\' not found.";
							break;
						}
						printWriter.println(response);
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
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