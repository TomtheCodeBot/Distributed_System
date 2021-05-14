package csv_transfer;

import java.net.*;
import java.io.*;

public class MyClient {
	public static void main(String args[]) {
		try {
			String serverName = "localhost";
			int serverPort = 1999;
			String File="C:\\Users\\Dell\\Desktop\\ds2 (1)\\advertising.csv";
			File myFile = new File (File);
			System.out.println("Establishing connection. Please wait ...");
			Socket socket = new Socket(serverName, serverPort);
			System.out.println("Connected: " + socket);
			FileInputStream fis = null;
			byte [] mybytearray  = new byte [(int)myFile.length()];
		    BufferedInputStream bis = null;
			fis = new FileInputStream(myFile);
	        bis = new BufferedInputStream(fis);
	        bis.read(mybytearray,0,mybytearray.length);
	        OutputStream os = socket.getOutputStream();
	        os.write(mybytearray,0,mybytearray.length);
	        os.flush();
			socket.close();
		}catch (UnknownHostException uhe) {
			System.out.println("Host unknown: " + uhe.getMessage());
		} catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
	}
}
	