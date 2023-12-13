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
			ServerSocket server = new ServerSocket(1025);
			System.out.println("Server is ready...");
			Socket socket = server.accept();

			// push to client
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
			// thể hiện cho người dùng đã kết nối thành công với server
			printWriter.println("Connected");

			// pull from client
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String request, param = "";
			boolean login = false, loop = true;
			String lastUser = "";
			String response = "";

			while (loop) {
				while (!login) {
					ArrayList<String> commands = getCommand(bufferedReader.readLine());
					request = commands.get(0).toUpperCase();

					// kiểm tra xem câu lệnh còn giá trị nào khác không?
					if (commands.size() == 1) {
						if (request.equals("QUIT")) {
							response = "Disconnecting......[OK]";
							// đóng kết nối với server
							loop = false;
						} else
							response = "ERROR: command \'" + request + "\' not found.";
					} else {
						// get param
						param = commands.get(1);
						// phân tích câu lệnh được client nhập vào
						switch (request) {
						case "USERNAME":
							if (DAO_User.selectByUsername(param)) {
								response = "Hello " + param;
								lastUser = param;
							} else
								response = "ERROR: " + param + " was not found!";
							break;
						case "PASSWORD":
							if (lastUser.equals("")) {
								response = "ERROR: Please enter your username first!";
							} else {
								if (DAO_User.select(lastUser, param)) {
									response = "\nWelcome back " + lastUser + System.lineSeparator();
									for (int i = 0; i < 15; i++) {
										response += "=";
									}
									login = true;
									System.out.println(response);
									break;
								} else
									response = "ERROR: Password is incorrect!";
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
					param = commands.get(1);
					System.out.println(commands.toString());
					if (commands.size() == 1) {
						if (request.equals("QUIT")) {
							response = "Exit......[OK]";
							login = false;
						} else
							response = "ERROR: command \'" + request + "\' not found.";
					} else {
						switch (request) {
						case "FINDBYNAME":
							printList(printWriter, DAO_Student.findByName(param));
							break;
						case "FINDBYID":
							Student student = DAO_Student.findById(param);
							ArrayList<Student> list = new ArrayList<>();
							list.add(student);
							printList(printWriter, list);
							break;
						default:
							response = "ERROR: command \'" + request + "\' not found.";
							break;
						}
					}
					printWriter.println(response);
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
		return getCommand(line, true);
	}

	private static ArrayList<String> getCommand(String line, boolean merge) {
		ArrayList<String> commands = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(line = line.trim());
		commands.add(tokenizer.nextToken());
		if (merge) {
			String param = "";
			while (tokenizer.hasMoreElements()) {
				param += tokenizer.nextToken() + " ";
			}
			param = param.trim();
			commands.add(param);
		} else {
			while (tokenizer.hasMoreTokens()) {
				commands.add(tokenizer.nextToken());
			}
		}

		return commands;
	}

	public static void main(String[] args) {
		server();
	}
}
