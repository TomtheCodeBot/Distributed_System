package csv_transfer;
import java.net.*;
import java.io.*;

public class MyServer {
	public static void main(String args[]) {
		try {
			int port = 1999;
			String FILE_TO_RECEIVED = "C:\\Users\\Dell\\Desktop\\ds2 (1)\\output.csv"; 
			int FILE_SIZE = 6022386;
			ServerSocket server = new ServerSocket(port);
			System.out.println("Server started: " + server);
			System.out.println("Waiting for a client ...");

			Socket socket = server.accept();
			System.out.println("Client accepted: " + socket);
			int bytesRead;
		    int current = 0;
		    FileOutputStream fos = null;
		    BufferedOutputStream bos = null;
		    Socket sock = null;
		      System.out.println("Connecting...");

		      // receive file
		    byte [] mybytearray  = new byte [FILE_SIZE];
		    InputStream is = socket.getInputStream();
		    fos = new FileOutputStream(FILE_TO_RECEIVED);
		      bos = new BufferedOutputStream(fos);
		      bytesRead = is.read(mybytearray,0,mybytearray.length);
		      current = bytesRead;

		      do {
		         bytesRead =
		            is.read(mybytearray, current, (mybytearray.length-current));
		         if(bytesRead >= 0) current += bytesRead;
		      } while(bytesRead > -1);
		      bos.write(mybytearray, 0 , current);
		      bos.flush();
			socket.close();
			server.close();
		}catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
}