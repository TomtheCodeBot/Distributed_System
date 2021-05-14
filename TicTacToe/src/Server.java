import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;


public class Server implements ServerInterface {
	public ArrayList<ClientInterface> Clients;
	public static int clientNum=0;
	public static int playerNum=1;
	public static char board[][]= new char[3][3];
	@Override
	public int Register(ClientInterface Client) throws RemoteException {
		Clients.add(Client);
		clientNum++;
		return 0;
	}
	public Server() throws RemoteException{
		Clients = new ArrayList<ClientInterface>();
	}
	@Override
	public void checkStart() throws RemoteException {
		if (Clients.size() == 2){
            Clients.get(1).drawBoard(board);
        }
	}
	@Override
	public void initializeBoard() throws RemoteException {
        for(int y=0;y<3;y++) {
			for(int x=0;x<3;x++) {
				board[y][x] = ' ';;
			}
		}
	}
	public int checkEnd() throws RemoteException {
		for(int i=0;i<3;i++) {
			if(board[i][0]==board[i][1] && board[i][1]==board[i][2]&&!Character.isWhitespace(board[i][1])) {
				if (playerNum == 1){
                    Clients.get(0).end(1);
                    Clients.get(1).end(1);         
                }
                else {
                    Clients.get(0).end(2);
                    Clients.get(1).end(2);  
                }
                return 1;
			}
		}
		for(int i=0;i<3;i++) {
			if(board[0][i]==board[1][i] && board[1][i]==board[2][i]&&!Character.isWhitespace(board[0][i]) ) {
				if (playerNum == 1){
                    Clients.get(0).end(1);
                    Clients.get(1).end(1);         
                }
                else {
                    Clients.get(0).end(2);
                    Clients.get(1).end(2);  
                }
                return 1;
			}
		}
		if((board[0][0]==board[1][1]&&board[1][1]==board[2][2]&&!Character.isWhitespace(board[0][0]))||(board[0][2]==board[1][1]&&board[1][1]==board[2][0])&&!Character.isWhitespace(board[0][2])) {
			if (playerNum == 1){
                Clients.get(0).end(1);
                Clients.get(1).end(1);         
            }
            else {
                Clients.get(0).end(2);
                Clients.get(1).end(2);  
            }
            return 1;
		}
		int full=0;
		for (int y=0;y<3;y++){
            for (int x=0;x<3;x++){
                if (board[y][x] != ' '){
                    full++;
                }
            }
        }
		if(full==9) {
			Clients.get(0).end(3);
            Clients.get(1).end(3); 
            return 1;
		}
		return 0;
	}
	@Override
	public void startGame() throws RemoteException {
		int gameEnd=0;
		if (Clients.size() == 2){
            while(gameEnd==0) {
            	 if (playerNum == 1){
                     board = Clients.get(0).makeMove(board, playerNum);
                 }
                 else{
                     board = Clients.get(1).makeMove(board, playerNum);
                 }
                  
            	 gameEnd = checkEnd();
                 if (playerNum == 1){
                     playerNum = 2;
                 }
                 else{
                     playerNum = 1;
                 }
            }
        }
	}
	public static void main(String args[]) {
		
		try {
		    Server obj = new Server();
		    ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(obj, 0);

		    // Bind the remote object's stub in the registry
	            
		    Registry registry = LocateRegistry.createRegistry(1);
		    registry.bind("Hello", stub);

		    System.err.println("Server ready");
			
		} catch (Exception e) {
		    System.err.println("Server exception: " + e.toString());
		    e.printStackTrace();
		}
	    }
	@Override
	public void createAccount(String login, String password) throws RemoteException {
		try {
			int result = 0;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
			DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe", "newuser", "password");

			Statement stmt = con.createStatement();
			stmt.executeUpdate("insert into login values(\""+login+"\",\""+password+"\")");
			
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}   
}
