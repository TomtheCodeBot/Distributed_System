
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface service extends Remote {
	void addBook(int id, String name) throws RemoteException;
	void addNews(int id, String name) throws RemoteException;
	int bookNum()throws RemoteException;
	int newsNum()throws RemoteException;
}
