package bookconverterjson;
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
			String publisher = br.readLine();
			String author = br.readLine();
			int age =Integer.valueOf(br.readLine());
			Book newbook = new Book(name, publisher, new Author(author,age));
			InetAddress serverHost = InetAddress.getByName("localhost");
			System.out.println("Connecting to server on port " + serverPort);
			socket = new Socket(serverHost, serverPort);
			System.out.println("Just connected to " + socket.getRemoteSocketAddress());
			toServer = new ObjectOutputStream(socket.getOutputStream());
			toServer.writeObject(newbook);
			fromServer = new ObjectInputStream(socket.getInputStream());
			
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