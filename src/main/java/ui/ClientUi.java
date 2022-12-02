package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import server.Communication;

public class ClientUi {
	Scene usersConnectionScene, menuScene, listUsersScene, dialogScene;

	public ClientUi(Stage primaryStage,Communication server,Communication client) {
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
					System.out.println(userTextField.getText() + " Connected!");
					
					primaryStage.setScene(menuScene);
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

			Button btnLogOut = new Button("Log Out");
			btnLogOut.setOnAction(e -> primaryStage.setScene(usersConnectionScene));
			btnLogOut.setStyle("-fx-background-color: #FE4EB8; -fx-text-fill: white; ");
			btnLogOut.setPrefWidth(500);
			btnLogOut.setPrefHeight(15);
			grid1.add(btnLogOut, 1, 30);

			primaryStage.setTitle("Chat Application");
			primaryStage.setScene(usersConnectionScene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
