package step3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		int serverPort = 2001;
		Socket socket = null;
		ObjectOutputStream toServer = null;
		ObjectInputStream fromServer = null;
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String name = br.readLine();
			String ID = br.readLine();
			String Year = br.readLine();
			String Gender =br.readLine();
			InetAddress serverHost = InetAddress.getByName("localhost");
			System.out.println("Connecting to server on port " + serverPort);
			socket = new Socket(serverHost, serverPort);
			System.out.println("Just connected to " + socket.getRemoteSocketAddress());
			
			toServer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			Message msgToSend = new Message(name,ID,Year,Gender);
			toServer.writeObject(msgToSend);
			toServer.flush();

			// This will block until the corresponding ObjectOutputStream
			// in the server has written an object and flushed the header
			fromServer = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			String msgFromReply = (String) fromServer.readObject();
			System.out.println(msgFromReply);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}