import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote{
	int Register(ClientInterface Client) throws RemoteException;
	void checkStart() throws RemoteException;
	void initializeBoard() throws RemoteException;
	void startGame() throws RemoteException;
	void createAccount(String login, String password) throws RemoteException;
}
