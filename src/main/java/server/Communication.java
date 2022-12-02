package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface Communication extends Remote {

	public List<Communication> getUsersList() throws RemoteException;
	
	public String getName() throws RemoteException;
	
	public void sendLogToServer(String msg) throws RemoteException;
	
	public void sendUserToServer(String name) throws RemoteException;
	
	public void sendLogoutToServer(String name) throws RemoteException;
	
	public void send(String from,String to,String msg) throws RemoteException;

	public void setClient(Communication c) throws RemoteException;

	public Communication getClient() throws RemoteException;

	public void envoyer(String from,String to, String msg,Communication server) throws RemoteException;
	
	public Map<String,String> getMessages() throws RemoteException;
	
}
