package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.ServerUi;

public class Server extends Application{

	public static Map<String, String> messages = new HashMap<String, String>();
	public static List<String> logs = new ArrayList<String>();
	public static List<Communication> usersList = new ArrayList<Communication>();
	private static ServerUi serverUi;
	
	public static void main(String[] args) {
		try {

			//Create The Registry
			LocateRegistry.createRegistry(8080);
			
			//Enter Server Name & Create the Client on the server side
			Communication server = new CommunicationImpl("SERVEUR");
			
			//Add the chat server to the registry
			Naming.rebind("rmi://localhost:8080/CHAT", server);
			
			System.out.println("SERVEUR ACTIF !");
			
			launch(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage arg0) throws Exception {
		serverUi = new ServerUi(logs);
	}

}

