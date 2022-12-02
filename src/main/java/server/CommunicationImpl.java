package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import ui.ServerUi;

public class CommunicationImpl extends UnicastRemoteObject implements Communication {

	private static final long serialVersionUID = 1L;
	public String name;
	public Communication client = null;

	public CommunicationImpl(String n) throws RemoteException {
		super();
		this.name = n;

	}

	public List<Communication> getUsersList() {
		return Server.usersList;
	}

	@Override
	public String getName() throws RemoteException {
		return this.name;
	}

	@Override
	public void send(String from, String to, String msg) throws RemoteException {
		System.out.println(msg);
	}

	@Override
	public void setClient(Communication c) throws RemoteException {
		client = c;
		Server.usersList.add(c);
		for (Communication user : Server.usersList) {
			if (!user.getName().equals(client.getName())) {
				Server.messages.put(user.getName() + client.getName(), "");
				Server.messages.put(client.getName() + user.getName(), "");
			}
		}
	}

	@Override
	public Communication getClient() throws RemoteException {
		return client;
	}

	@Override
	public void envoyer(String from, String to, String msg, Communication server) {
		for (Communication user : Server.usersList) {
			try {
				if (user.getName().equals(to)) {
					user.send(from, to, msg);
					String conversation = Server.messages.get(to + from);
					conversation += msg;
					Server.messages.put(to + from, conversation);
					Server.messages.put(from + to, conversation);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void sendLogToServer(String msg) throws RemoteException {
		Platform.runLater(() -> {
			ServerUi.itemsH.add(msg);
		});
	}

	@Override
	public Map<String, String> getMessages() throws RemoteException {
		return Server.messages;
	}

	@Override
	public void sendUserToServer(String name) throws RemoteException {
		Platform.runLater(() -> {
			ServerUi.itemsU.add(name);
		});
	}

	@Override
	public void sendLogoutToServer(String name) throws RemoteException {
		for(int i=0;i<Server.usersList.size();i++) {
			if(Server.usersList.get(i).getName().equals(name)) {
				Server.usersList.remove(i);
				break;
			}
		}
		Platform.runLater(() -> {
			ServerUi.itemsU.remove(name);
		});
	}

}
