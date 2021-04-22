package step3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Server {
	public static void main(String[] args) throws SQLException {
		int serverPort = 2001;
		ServerSocket serverSocket = null;
		ObjectOutputStream toClient = null;
		ObjectInputStream fromClient = null;
		try {
			serverSocket = new ServerSocket(serverPort);
			System.out.println("connected");
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Just connected to " + socket.getRemoteSocketAddress());
				
				toClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				fromClient = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				Message msgRequest = (Message) fromClient.readObject();
				
				Connection conn = Database.getConnection();
				PreparedStatement st = conn.prepareStatement("Insert into student(name,id,gender,year) value(?,?,?,?)");
				st.setString(1, msgRequest.getName());
				st.setString(2, msgRequest.getID());
				st.setString(3, msgRequest.getGender());
				st.setString(4, msgRequest.getYear());
				st.execute();
				toClient.writeObject("Data inserted");
				toClient.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}