package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import DAO.DAO_Student;
import DAO.DAO_User;
import Model.Student;

public class ClientHandler extends Thread {
	private Socket clientSocket;

	public ClientHandler(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try (PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));) {
			System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

			// process for a client
			boolean login = false;
			String lastUser = "", req = "", param = "";

			while (!login) {
				ArrayList<String> commands = getCommands(bufferedReader.readLine());
				if (commands.size() != 0 || !commands.isEmpty()) {
					req = commands.get(0);
					if (commands.size() >= 2) {
						param = commands.get(1);
					}
					switch (req) {
					case "QUIT":
						printWriter.print("Disconnecting");
						for (int i = 0; i < 10; i++) {
							printWriter.print(".");
							Thread.sleep(100);
						}
						printWriter.print("[OK]");

						// disconnect
						bufferedReader.close();
						printWriter.close();
						clientSocket.close();
						break;
					case "USERNAME":
						System.out.println("da vao day");
						if (DAO_User.selectByUsername(param)) {
							lastUser = param;
							printWriter.println("Hello: " + lastUser);
						} else {
							printWriter.println("Username " + param + " not found in the system");
						}
						break;
					case "PASSWORD":
						if (lastUser.isEmpty() || lastUser.equals("")) {
							printWriter.println("Please enter username first");
						} else if (DAO_User.select(lastUser, param)) {
							login = true;
							printWriter.println("---Welcome back " + lastUser + "---");
						} else
							printWriter.println("Incorrect password");
						break;
					default:
						printWriter.println("'" + req + "' command not found.");
						break;
					}
				}
			}
			while (login) {
				ArrayList<Student> students = new ArrayList<>();
//				printWriter.println("hello");
				ArrayList<String> commands = getCommands(bufferedReader.readLine());
				if (commands.size() != 0 || !commands.isEmpty()) {
					req = commands.get(0);
					if (commands.size() >= 2) {
						param = commands.get(1);
					}
					switch (req) {
					case "QUIT":
						printWriter.print("EXIT");
						for (int i = 0; i < 10; i++) {
							printWriter.print(".");
							Thread.sleep(100);
						}
						printWriter.print("[OK]");
						login = false;
						break;
					case "FINDBYNAME":
						if ((students = DAO_Student.findByName(param)).size() != 0) {
							printInfo(students, printWriter);
						} else {
							printWriter.println("Student with the name " + param + " does not exist in the system.");
						}
						break;
					case "FINDBYID":
						Student student = new Student();
						if ((student = DAO_Student.findById(param)) != null) {
							students.add(student);
							printInfo(students, printWriter);
						} else {
							printWriter.println("Student with the id " + param + " does not exist in the system.");
						}
						break;
					case "FINDBYSCORE":
						if ((students = DAO_Student.findByScore(Double.parseDouble(param))).size() != 0) {
							printInfo(students, printWriter);
						} else {
							printWriter.println("Student with the score " + param + " does not exist in the system.");
						}
						break;
					case "FINDBYAGE":
						if ((students = DAO_Student.findByAge(Integer.parseInt(param))).size() != 0) {
							printInfo(students, printWriter);
						} else {
							printWriter.println("Student with the age " + param + " does not exist in the system.");
						}
						break;
					default:
						printWriter.println("'" + req + "' command not found.");
						break;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * chuyển lệnh nhận được từ phía client vào và phân tích câu lệnh đó.
	 * 
	 * @param line  dòng lệnh được client gửi tới server
	 * @param merge quyết định có xác nhập các param sau câu lệnh hay không
	 * @return
	 */
	private ArrayList<String> getCommands(String line, boolean merge) {
		ArrayList<String> commands = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(line = line.trim());
		commands.add(tokenizer.nextToken().toUpperCase());
		if (merge) {
			String param = "";
			while (tokenizer.hasMoreTokens()) {
				param += tokenizer.nextToken() + " ";
			}
			commands.add(param = param.trim());
		} else {
			while (tokenizer.hasMoreTokens()) {
				commands.add(tokenizer.nextToken());
			}
		}
		System.out.println("Line: \"" + line + "\"");
		System.out.println("\tCommands: " + commands.toString());
		return commands;
	}

	private ArrayList<String> getCommands(String line) {
		return getCommands(line, true);
	}

	private static void printInfo(ArrayList<Student> students, PrintWriter printWriter) {
		printWriter.println("============= Result: " + students.size() + " =============");
		for (Student student : students) {
			printWriter.println("=\t" + student.toString());
		}
	}
}
