package ui;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ServerUi {
	
	private Scene serverScene;
	public static ObservableList<String> itemsU = FXCollections.observableArrayList();
	public static ObservableList<String> itemsH = FXCollections.observableArrayList();
	
	
	public ServerUi(List<String> logs) {
		
		GridPane grid4 = new GridPane();
		grid4.setAlignment(Pos.CENTER);
		grid4.setVgap(5);
		grid4.setPadding(new Insets(25, 25, 25, 25));

		serverScene = new Scene(grid4, 460, 400);
		serverScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		Image imageServer = new Image("https://cdn-icons-png.flaticon.com/512/7067/7067790.png");
		ImageView pictureServer = new ImageView();
		pictureServer.setFitWidth(100);
		pictureServer.setFitHeight(100);
		pictureServer.setImage(imageServer);
		grid4.add(pictureServer, 2, 0);

		Label ConnectedUsersList = new Label("ConnectedUsers");
		ConnectedUsersList.setFont(Font.font("Playfair Display", FontWeight.NORMAL, 20));
		grid4.add(ConnectedUsersList, 3, 1);

		Label History = new Label("LoggingHistory");
		GridPane.setHalignment(History, HPos.LEFT);
		History.setFont(Font.font("Playfair Display", FontWeight.NORMAL, 20));
		grid4.add(History, 0, 1);

		ListView<String> history = new ListView<String>();
		history.setItems(itemsH);
		history.setPrefWidth(150);
		history.setPrefHeight(200);
		history.setStyle("-fx-background-color: #54439C; -fx-text-fill: white;");
		grid4.add(history, 0, 3);

		ListView<String> connectedUsers = new ListView<String>();
		connectedUsers.setItems(itemsU);
		connectedUsers.setPrefWidth(150);
		connectedUsers.setPrefHeight(200);
		connectedUsers.setStyle("-fx-background-color: #54439C; -fx-text-fill: white;");
		grid4.add(connectedUsers, 3, 3);

		Stage server = new Stage();
		server.setTitle("CHAT SERVER");
		server.setScene(serverScene);
		server.show();

	}
}
