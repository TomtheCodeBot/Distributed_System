
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
		
		String host = (args.length < 1) ? null : args[0];
		try {
		    Registry registry = LocateRegistry.getRegistry(1);
		    ServerInterface stub = (ServerInterface) registry.lookup("Hello");
	            
	            Client client;  
	            client = new Client(stub);
	            stub.Register(client);

	            stub.initializeBoard();
	            stub.checkStart();
	            stub.startGame();
	            
		    
		} catch (Exception e) {
		    System.err.println("Client exception: " + e.toString());
		    e.printStackTrace();
		}
	}     
}
