import java.rmi.Remote;
import java.rmi.RemoteException;
public interface ClientInterface  extends Remote{
	void drawBoard(char[][] board) throws RemoteException;
	char[][] makeMove(char[][] board, int playerNum)throws RemoteException;;
	void end(int event)throws RemoteException;;
}
