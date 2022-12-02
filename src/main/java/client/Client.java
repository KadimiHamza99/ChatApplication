package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import server.Communication;
import server.CommunicationImpl;
import ui.ClientUi;

public class Client 
 extends Application
{

	private static ClientUi clientUi;
	private static Communication server,client;
	public static void main(String[] args) {

		try {
			
			launch(args);
			
			// Get the server instance (STUB)
			server = (Communication) Naming.lookup("rmi://localhost:8080/CHAT");

			// Check if there is already another client with the same name
			Scanner s = new Scanner(System.in);
			System.out.println("Entrez votre nom et appuyez sur Entrée:");
			String name = s.nextLine().trim();
			for (Communication c : server.getUsersList()) {
				while (name.equals(c.getName())) {
					System.out.println("Entrez votre nom et appuyez sur Entrée:");
					name = s.nextLine().trim();
				}
			}

			// create the client
			client = new CommunicationImpl(name);

			// Listen to messages & send it to the client
			String msg = "[" + client.getName() + "] s'est connecté";
			server.sendLogToServer(msg);
			server.sendUserToServer(name);
			server.setClient(client);

			Menu(server, client);

		} catch (Exception e) {
			e.printStackTrace();
		}	

	}

	public static void Menu(Communication server, Communication client) {
		System.out.println("0 : DISCONNECT");
		System.out.println("1 : LIST ALL CONNECTED USERS");
		while (true) {
			Scanner s = new Scanner(System.in);
			String choice = s.nextLine().trim();
			switch (choice) {
				case "0":
					try {
						String msg = "[" + client.getName() + "] s'est déconnecté";
						server.sendLogToServer(msg);
						server.sendLogoutToServer(client.getName());
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					System.exit(0);
					break;
				case "1":
					try {
						if (server.getUsersList().size() == 1) {
							System.out.println("NO CLIENT CONNECTED !");
						} else {
							for (Communication c : server.getUsersList()) {
								if (!client.getName().equals(c.getName())) {
									System.out.println(c.getName());
								}
							}

							System.out.print("Send message to : ");

							String name = s.nextLine().trim();
							boolean flag = true;
							List<String> names = new ArrayList<String>();
							for (int i = 0; i < server.getUsersList().size(); i++) {
								names.add(i, server.getUsersList().get(i).getName());
							}
							if (!names.contains(name)) {
								flag = false;
								System.out.println("No user with this name !");
								Menu(server, client);
							}
							if (flag) {
								String conversation = server.getMessages().get(client.getName() + name);
								if (conversation == null)
									conversation = "Start the conversation : ";
								System.out.println(conversation);
								lancerConv(client, server, name, s);
							}
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
			}
		}
	}

	public static void lancerConv(Communication client, Communication server, String to, Scanner s) {
		String msg = "";
		while (true) {
			try {
				msg = s.nextLine().trim();
				if (msg.equals("0")) {
					Menu(server, client);
					break;
				}
				msg = "[" + client.getName() + "] " + msg;
				server.envoyer(client.getName(), to, msg, server);

			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void start(Stage arg0) throws Exception {
		clientUi = new ClientUi(arg0,server,client);
	}

}
