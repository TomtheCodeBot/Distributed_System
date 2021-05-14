import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote{
	void Register(ClientInterface Client) throws RemoteException;
	void checkStart() throws RemoteException;
	void initializeBoard() throws RemoteException;
	void startGame() throws RemoteException;
}
