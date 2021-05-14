
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements ClientInterface {
	
	public Client(ServerInterface server) throws RemoteException{
		
	}

	@Override
	public void drawBoard(char[][] board) throws RemoteException {
		for(int y=0;y<3;y ++) {
			for(int x=0;x<3;x++) {
				System.out.print("|"+board[y][x]+"|");
			}
			System.out.print("\n-----------\n");
		}
	}

	@Override
	public char[][] makeMove(char[][] board, int playerNum) throws RemoteException {
		Scanner in = new Scanner(System.in);
        drawBoard(board);
        System.out.print("Enter coordinates (row [1-3]) (column [1-3]): ");
            
        int row = in.nextInt() - 1;  
        int col = in.nextInt() - 1;
        while ( row > 2 || col > 2 || row < 0 || col < 0 || board[row][col] != ' ' ){
            System.out.println("Invalid input. Try again...");
            row = in.nextInt() - 1;  
            col = in.nextInt() - 1;
        }
            
        if (playerNum == 1){
            board[row][col] = 'X';
        }
        else{
            board[row][col] = 'O';
        }
        drawBoard(board);
		return board;
	}

	@Override
	public void end(int event) throws RemoteException {
		switch(event) {
			case(1):
				System.out.println("Player X wins! Game is over now!");
				break;
			case(2):
				System.out.println("Player O wins! Game is over now!");
				break;
			default:
				System.out.println("Game is a draw! Game is over now!");
				break;
		}
		
	}
	public static void main(String[] args) {

        
		Scanner in = new Scanner(System.in);
		
		try {
		    Registry registry = LocateRegistry.getRegistry(1);
		    ServerInterface stub = (ServerInterface) registry.lookup("Hello");
		    String login;
    		String password;
		    Client client;  
            client = new Client(stub);
            System.out.println("Welcome to tic tac toe! Select an option below:\n1. Create a new account.\n2. Start the game.");
            int choice = in.nextInt();
            switch(choice) {
            	case(1):
	        		System.out.println("Enter your new username:");
	        		login = in.next();
	        		System.out.println("Enter your new password:");
	        		password = in.next();
	        		stub.createAccount(login, password);
            		break;
            	case(2):
	        		System.out.println("Enter your username:");
	        		login = in.next();
	        		System.out.println("Enter your password:");
	        		password = in.next();
	        		int Check=0;
	        		try {
	        			int result = 0;
	        			Class.forName("com.mysql.cj.jdbc.Driver");
	        			Connection con = 
	        			DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe", "newuser", "password");

	        			Statement stmt = con.createStatement();
	        			ResultSet rs=stmt.executeQuery("select count(*) from login where login=\""+login+"\" AND password=\""+password+"\"");
	        			if(rs.next()) {
	        				Check=rs.getInt(1);
	        			}
	        			con.close();
	        			
	        		} catch (Exception e) {
	        			System.out.println(e);
	        		}
	        		if(Check==1) {
	        			System.out.println("login successful: Starting game...");
	        			stub.Register(client);
	    	            
	    	            stub.initializeBoard();
	    	            stub.checkStart();
	    	            stub.startGame();
	        		}
	        		else {
	        			System.out.println("login failed");
	        		}
	        		break;
	        	default:
	        		System.out.println("No option like this exists.");
	            }
	            		    
		} catch (Exception e) {
		    System.err.println("Client exception: " + e.toString());
		    e.printStackTrace();
		}
	}     
}
