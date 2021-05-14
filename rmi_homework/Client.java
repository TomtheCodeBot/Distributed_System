import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	private Client() {}

    public static void main(String[] args) {
        String host = "localhost";
        try {
			// get data from users
			
            Registry registry = LocateRegistry.getRegistry(1);
            service stub = (service) registry.lookup("service");
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String choice;
            int id;
            String name;
            System.out.println("input the table you want to input");
            choice = console.readLine();
            switch (choice) {
            	case "book":
            		System.out.println("input the id and name of the book");
                    id = Integer.parseInt(console.readLine());
                    name = console.readLine();
                    stub.addBook(id,name);
                    break;
            	case "news_paper":
            		System.out.println("input the id and name of the newspaper");
            		id = Integer.parseInt(console.readLine());
                    name = console.readLine();
                    stub.addNews(id,name);
                    break;
                default:
                	System.out.println("not exist that table.");
            }
            System.out.println("Number of books:"+String.valueOf(stub.bookNum()));
            System.out.println("Number of news:"+String.valueOf(stub.newsNum()));
        } catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}		
		catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
