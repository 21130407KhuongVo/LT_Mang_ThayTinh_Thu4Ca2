package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
	private Socket clientSocket;

	public ClientHandler(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try (PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));) {
System.out.println("New client connected: "+ clientSocket.getInetAddress().getHostAddress());
		} catch (IOException e) {
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

}
