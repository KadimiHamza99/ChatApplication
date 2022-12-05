package ui;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import server.Communication;
import server.CommunicationImpl;

public class ClientUi {
	Scene usersConnectionScene, menuScene, listUsersScene, dialogScene;
	Communication client, server;
	String selected;
	public static ObservableList<String> messages = FXCollections.observableArrayList();

	public ClientUi(Stage primaryStage) {
		try {
			/*
			 * Connection Page
			 */
			GridPane grid0 = new GridPane();
			grid0.setAlignment(Pos.CENTER);
			grid0.setVgap(5);
			grid0.setPadding(new Insets(25, 25, 25, 25));

			usersConnectionScene = new Scene(grid0, 400, 400);
			usersConnectionScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Label usersConnectionSceneTitle = new Label("Rmi Chat");
			GridPane.setHalignment(usersConnectionSceneTitle, HPos.CENTER);
			usersConnectionSceneTitle.setFont(Font.font("Playfair Display", FontWeight.BOLD, 40));
			usersConnectionSceneTitle.setStyle("-fx-font-family: San Francisco  ");
			grid0.add(usersConnectionSceneTitle, 1, 0, 2, 1);

			Image imageUsersConnection = new Image("https://cdn-icons-png.flaticon.com/512/5247/5247181.png");
			ImageView pictureUsersConnection = new ImageView();
			pictureUsersConnection.setFitWidth(80);
			pictureUsersConnection.setFitHeight(80);
			pictureUsersConnection.setImage(imageUsersConnection);
			GridPane.setHalignment(pictureUsersConnection, HPos.CENTER);
			grid0.add(pictureUsersConnection, 0, 1, 2, 1);

			Label UserName = new Label("Enter User's name");
			UserName.setFont(Font.font("Playfair Display", FontWeight.NORMAL, 20));
			grid0.add(UserName, 0, 2, 2, 1);

			TextField userTextField = new TextField();
			userTextField.setPrefWidth(500);
			userTextField.setPrefHeight(15);
			grid0.add(userTextField, 0, 3, 2, 1);

			Button btnConnection = new Button("Connection");
			btnConnection.setStyle("-fx-background-color: #54439C; -fx-text-fill: white;");
			btnConnection.setPrefWidth(500);
			btnConnection.setPrefHeight(15);
			grid0.add(btnConnection, 1, 4);
			btnConnection.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String name = userTextField.getText();
					// Get the server instance (STUB)
					try {
						server = (Communication) Naming.lookup("rmi://localhost:8080/CHAT");
						// Check if there is already another client with the same name
						boolean flag = true;
						for (Communication c : server.getUsersList()) {
							while (name.equals(c.getName())) {
								userTextField.clear();
								flag = false;
								break;
							}
						}
						if (flag) {
							// create the client
							client = new CommunicationImpl(name);
							String msg = "[" + client.getName() + "] s'est connecté";
							server.sendLogToServer(msg);
							server.sendUserToServer(name);
							server.setClient(client);
							primaryStage.setScene(menuScene);
						}
					} catch (MalformedURLException | RemoteException | NotBoundException e) {
						e.printStackTrace();
					}
				}
			});

			/*
			 * Menu Page
			 */

			GridPane grid1 = new GridPane();
			grid1.setAlignment(Pos.CENTER);
			grid1.setVgap(6);
			grid1.setPadding(new Insets(25, 25, 25, 25));

			menuScene = new Scene(grid1, 400, 400);
			menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Image imageMenu = new Image("https://cdn-icons-png.flaticon.com/512/7367/7367754.png");
			ImageView pictureMenu = new ImageView();
			pictureMenu.setFitWidth(80);
			pictureMenu.setFitHeight(80);
			pictureMenu.setImage(imageMenu);
			GridPane.setHalignment(pictureMenu, HPos.CENTER);
			grid1.add(pictureMenu, 0, 1, 2, 1);

			Button btnMenu = new Button("Connected Users");
			btnMenu.setOnAction(e -> primaryStage.setScene(listUsersScene));
			btnMenu.setStyle("-fx-background-color: #54439C; -fx-text-fill: white; ");
			btnMenu.setPrefWidth(500);
			btnMenu.setPrefHeight(15);
			grid1.add(btnMenu, 1, 5);
			btnMenu.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					try {
						if (server.getUsersList().size() == 1) {
							System.out.println("NO CLIENT CONNECTED !");
						} else {
							primaryStage.setScene(listUsersScene);
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			});

			Button btnLogOut = new Button("Log Out");
			btnLogOut.setStyle("-fx-background-color: #FE4EB8; -fx-text-fill: white; ");
			btnLogOut.setPrefWidth(500);
			btnLogOut.setPrefHeight(15);
			grid1.add(btnLogOut, 1, 30);
			btnLogOut.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					try {
						String msg = "[" + client.getName() + "] s'est déconnecté";
						server.sendLogToServer(msg);
						server.sendLogoutToServer(client.getName());
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					System.exit(0);
				}
			});

			/*
			 * List Users
			 */

			GridPane grid2 = new GridPane();
			grid2.setAlignment(Pos.CENTER);
			grid2.setVgap(5);
			grid2.setPadding(new Insets(15, 15, 15, 15));

			Button refresh = new Button("REFRESH");
			refresh.setStyle("-fx-background-color: #FE4EB8; -fx-text-fill: white; ");
			refresh.setPrefWidth(100);
			refresh.setPrefHeight(5);
			grid2.add(refresh, 0, 6);
			refresh.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					ListView<String> connectedUsers = new ListView<String>();
					ObservableList<String> names = FXCollections.observableArrayList();
					try {
						ObservableList<Communication> items = FXCollections.observableArrayList(server.getUsersList());
						ArrayList<String> namesList = new ArrayList<String>(Arrays.asList());
						for (Communication item : items) {
							namesList.add(item.getName());
						}
						names = FXCollections.observableArrayList(namesList);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					connectedUsers.setItems(names);
					connectedUsers.setPrefWidth(150);
					connectedUsers.setPrefHeight(200);
					connectedUsers.setStyle("-fx-background-color: #54439C; -fx-text-fill: white;");
					grid2.add(connectedUsers, 0, 5);
					connectedUsers.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							System.out.println("clicked on " + connectedUsers.getSelectionModel().getSelectedItem());
							selected = connectedUsers.getSelectionModel().getSelectedItem();
							try {
								ClientUi.messages.clear();
								System.out.println("messages : "+server.getMessages().get(client.getName()+selected));
								ClientUi.messages.addAll(server.getMessages().get(client.getName()+selected).split(","));
								System.out.println("Observable Size= "+ClientUi.messages.size());
							} catch (RemoteException e) {
								e.printStackTrace();
							}
							
							primaryStage.setScene(dialogScene);
						}
					});
				}
			});

			listUsersScene = new Scene(grid2, 400, 400);
			listUsersScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Label listUsersScenetitle = new Label("Users List");
			GridPane.setHalignment(listUsersScenetitle, HPos.CENTER);
			listUsersScenetitle.setFont(Font.font("Playfair Display", FontWeight.BOLD, 40));
			grid2.add(listUsersScenetitle, 0, 1);

			Image imageReturnBack = new Image("https://cdn-icons-png.flaticon.com/512/7229/7229235.png");
			ImageView pictureReturnBack = new ImageView();
			pictureReturnBack.setFitWidth(30);
			pictureReturnBack.setFitHeight(30);
			pictureReturnBack.setImage(imageReturnBack);
			grid2.add(pictureReturnBack, 0, 1);
			pictureReturnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					ClientUi.messages.clear();
					primaryStage.setScene(menuScene);
				}
			});

			Image imageListUsers = new Image("https://cdn-icons-png.flaticon.com/512/3590/3590544.png");
			ImageView pictureListUsers = new ImageView();
			pictureListUsers.setFitWidth(100);
			pictureListUsers.setFitHeight(100);
			pictureListUsers.setImage(imageListUsers);
			GridPane.setHalignment(pictureListUsers, HPos.CENTER);
			grid2.add(pictureListUsers, 0, 2);

			btnLogOut = new Button("Log Out");
			btnLogOut.setStyle("-fx-background-color: #FE4EB8; -fx-text-fill: white; ");
			btnLogOut.setPrefWidth(500);
			btnLogOut.setPrefHeight(15);
			grid2.add(btnLogOut, 0, 20);
			btnLogOut.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					try {
						String msg = "[" + client.getName() + "] s'est déconnecté";
						server.sendLogToServer(msg);
						server.sendLogoutToServer(client.getName());
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					System.exit(0);
				}
			});

			/*
			 * Dialog page
			 */
			GridPane grid3 = new GridPane();
			grid3.setAlignment(Pos.CENTER);
	        grid3.setVgap(5);
	        grid3.setHgap(3);

			grid3.setPadding(new Insets(25, 25, 25, 25));

			dialogScene = new Scene(grid3, 400, 400);
			dialogScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Image imageReturn = new Image("https://cdn-icons-png.flaticon.com/512/7229/7229235.png");
			ImageView pictureReturn = new ImageView();
			pictureReturn.setFitWidth(30);
			pictureReturn.setFitHeight(30);
			pictureReturn.setImage(imageReturn);
			grid3.add(pictureReturn, 0, 1);
			pictureReturn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					primaryStage.setScene(menuScene);
				}
			});

			ListView<String> messagesTable = new ListView<String>();
			messagesTable.setItems(ClientUi.messages);
			messagesTable.setPrefWidth(150);
			messagesTable.setPrefHeight(200);
			messagesTable.setStyle("-fx-background-color: #54439C; -fx-text-fill: white;");
			

			Image imageReturnBack1 = new Image("https://cdn-icons-png.flaticon.com/512/7229/7229235.png");
			ImageView pictureReturnBack1 = new ImageView();
			pictureReturnBack1.setFitWidth(30);
			pictureReturnBack1.setFitHeight(30);
			pictureReturnBack1.setImage(imageReturnBack1);
			grid3.add(pictureReturnBack1, 0, 1);
			pictureReturnBack1.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					primaryStage.setScene(listUsersScene);
				}
			});
			TextField sendMessage = new TextField();
			sendMessage.setPrefWidth(300);
			sendMessage.setPrefHeight(15);
			grid3.add(sendMessage, 0, 4);

			Button btnSendMessage = new Button("Send");
			btnSendMessage.setStyle("-fx-background-color: #54439C; -fx-text-fill: white;");
			btnSendMessage.setPrefWidth(50);
			btnSendMessage.setPrefHeight(15);
			grid3.add(btnSendMessage, 1, 4);
			btnSendMessage.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					try {
						server.envoyer(client.getName(), selected , sendMessage.getText(), server);
						ClientUi.messages.add("["+client.getName()+"] " + sendMessage.getText());
						sendMessage.clear();
						System.out.println(server.getMessages().get(client.getName()+selected));
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}	
			});
			
			grid3.add(messagesTable, 0, 2);
			primaryStage.setTitle("Chat Application");
			primaryStage.setScene(usersConnectionScene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
