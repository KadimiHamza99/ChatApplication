package ui;

import javafx.application.Application;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GraphicalInterface extends Application {
	 Scene usersConnectionScene, menuScene, listUsersScene, dialogScene, serverScene;

		
		@Override
		public void start(Stage primaryStage) {
			
			try { 
		//////// Users Connection/////////// 
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
		         GridPane.setHalignment(pictureUsersConnection,HPos.CENTER );
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
					        	 System.out.println( userTextField.getText() +" Connected!");
					             primaryStage.setScene(menuScene);		
					             }
					         });
		         
		         
		//////// Menu///////////   
		         
		         GridPane grid1 = new GridPane();
		         grid1.setAlignment(Pos.CENTER);
		         grid1.setVgap(6);
		         grid1.setPadding(new Insets(25, 25, 25, 25));

		         
		         menuScene = new Scene(grid1, 400, 400);
		         menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		         
//		         Label menuScenetitle = new Label("Menu");
//		         GridPane.setHalignment(menuScenetitle,HPos.CENTER );
//		         GridPane.setValignment(menuScenetitle,VPos.TOP );
//		         menuScenetitle.setFont(Font.font("Playfair Display", FontWeight.BOLD, 40));
//		         grid1.add(menuScenetitle, 1, 0, 2, 1);
		         
		         
		         Image imageMenu = new Image("https://cdn-icons-png.flaticon.com/512/7367/7367754.png");
		         ImageView pictureMenu = new ImageView();
		         pictureMenu.setFitWidth(80);
		         pictureMenu.setFitHeight(80);
		         pictureMenu.setImage(imageMenu);
		         GridPane.setHalignment(pictureMenu,HPos.CENTER );
		         grid1.add(pictureMenu, 0, 1, 2, 1);
		        
//		         Label Name = new Label();
//		         String n = userTextField.getText();
//		         Name.setText("Welcome"+ n );
//		         Name.setFont(Font.font("Playfair Display", FontWeight.NORMAL, 30));
//		         grid1.add(Name,0, 1, 2, 1);
		         
		         
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
		         
		         
	//////// List Users///////////   
		         
		         GridPane grid2 = new GridPane();
		         grid2.setAlignment(Pos.CENTER);
		         grid2.setVgap(5);
		         grid2.setPadding(new Insets(25, 25, 25, 25));

		         
		         listUsersScene = new Scene(grid2, 400, 400);
	         	 listUsersScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		         Label listUsersScenetitle = new Label("Users List");
		         GridPane.setHalignment(listUsersScenetitle,HPos.CENTER );
		         listUsersScenetitle.setFont(Font.font("Playfair Display", FontWeight.BOLD, 40));
		         grid2.add(listUsersScenetitle,0,1);
		         
		         
		         Image imageReturnBack = new Image("https://cdn-icons-png.flaticon.com/512/7229/7229235.png");
		         ImageView pictureReturnBack= new ImageView();
		         pictureReturnBack.setFitWidth(30);
		         pictureReturnBack.setFitHeight(30);
		         pictureReturnBack.setImage(imageReturnBack );
		         grid2.add(pictureReturnBack,  0, 1);
		         pictureReturnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {

		             @Override
		             public void handle(MouseEvent event) {
		                 primaryStage.setScene(menuScene);
		             	}
		         	 }); 
		         
		         
		         Image imageListUsers = new Image("https://cdn-icons-png.flaticon.com/512/3590/3590544.png");
		         ImageView pictureListUsers = new ImageView();
		         pictureListUsers.setFitWidth(100);
		         pictureListUsers.setFitHeight(100);
		         pictureListUsers.setImage(imageListUsers);
		         GridPane.setHalignment(pictureListUsers,HPos.CENTER );
		         grid2.add(pictureListUsers, 0, 2);
		        
		         
		         ListView<String> listUsers = new ListView<String>();
		         ObservableList<String> items =FXCollections.observableArrayList (
		             "Kawtar", "Hamza", "Moulahid", "Kadimi");
		         listUsers.setItems(items);
		         listUsers.setPrefWidth(400);
		         listUsers.setPrefHeight(100);
		         listUsers.setStyle("-fx-background-color: #54439C; -fx-text-fill: white;");
		         listUsers.setOnMouseClicked(new EventHandler<MouseEvent>() {

		             @Override
		             public void handle(MouseEvent event) {
		                 System.out.println("clicked on " + listUsers.getSelectionModel().getSelectedItem());
		                 primaryStage.setScene(dialogScene);
		             	}
		         	});
		         grid2.add(listUsers, 0, 5);
		         
		         
		         btnLogOut = new Button("Log Out");
		         btnLogOut.setOnAction(e -> primaryStage.setScene(usersConnectionScene));   
		         btnLogOut.setStyle("-fx-background-color: #FE4EB8; -fx-text-fill: white; ");
		         btnLogOut.setPrefWidth(500);
		         btnLogOut.setPrefHeight(15);
		         grid2.add(btnLogOut, 0, 20);
		         
//		     Dialog ////////   
		         GridPane grid3 = new GridPane();
		         grid3.setAlignment(Pos.CENTER);
//		         grid3.setVgap(5);
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
		         
		     
		         ObservableMap<String, String> map = FXCollections.observableHashMap();
		         ObservableList<String> keys = FXCollections.observableArrayList();
		         map.addListener((MapChangeListener.Change<? extends String, ? extends String> change) -> {
		             boolean removed = change.wasRemoved();
		             if (removed != change.wasAdded()) {
		                 // no put for existing key
		                 if (removed) {
		                     keys.remove(change.getKey());
		                 } else {
		                     keys.add(change.getKey());
		                 }
		             }
		         });

		         
		         map.put("one", "One");
		         map.put("two", "Two");
		         map.put("three", "Three");
		         
		         final TableView<String> table = new TableView<>(keys);
		         TableColumn<String, String> column1 = new TableColumn<>();
		         // display item value (= constant)
		         column1.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue()));
		         TableColumn<String, String> column2 = new TableColumn<>();
		         column2.setCellValueFactory(cd -> Bindings.valueAt(map, cd.getValue()));
		         table.getColumns().setAll(column1, column2);
		         grid3.add(table, 2, 1);
		         
		         /* btnMenu.setOnAction(new EventHandler<ActionEvent>() {
		         	 
		             @Override
		             public void handle(ActionEvent event) {
		                 System.out.println( " Connected!");
		             }
		         });
		        
		         btnLogOut.setOnAction(new EventHandler<ActionEvent>() {
		         	 
		             @Override
		             public void handle(ActionEvent event) {
		                 System.out.println( " Connected!");
		             }
		         });*/
		         
		         
//			     Server ////////   
		         GridPane grid4 = new GridPane();
		         grid4.setAlignment(Pos.CENTER);
		         grid4.setVgap(5);
		         grid4.setPadding(new Insets(25,25,25,25));

		         
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
		         grid4.add(ConnectedUsersList,3, 1 );
		         
		         
		         Label History = new Label("LoggingHistory");
		         GridPane.setHalignment(History,HPos.LEFT );
		         History.setFont(Font.font("Playfair Display", FontWeight.NORMAL, 20));
		         grid4.add(History,0, 1);
		        
		         
		         ListView<String> history = new ListView<String>();
		         ObservableList<String> itemsH =FXCollections.observableArrayList (
		             "Kawtar is connected", "Hamza is connected ", "Kawtar is decconnected", "Kadimi is connected", "Hamza is deconnected ");
		         history.setItems(itemsH);
		         history.setPrefWidth(150);
		         history.setPrefHeight(200);
		         history.setStyle("-fx-background-color: #54439C; -fx-text-fill: white;");
		         grid4.add(history,0,3);
		         
		         
		         ListView<String> connectedUsers = new ListView<String>();
		         ObservableList<String> itemsU =FXCollections.observableArrayList (
		             "Kawtar ", "Hamza  ", "kadimi", "moulahid ");
		         connectedUsers.setItems(itemsU);
		         connectedUsers.setPrefWidth(150);
		         connectedUsers.setPrefHeight(200);
		         connectedUsers.setStyle("-fx-background-color: #54439C; -fx-text-fill: white;");
		         grid4.add(connectedUsers,3,3);
		         
		         
		         
	         
		         Stage server = new Stage();
		         server.setTitle("Server Side");
		         server.setScene(serverScene);
		         server.show();
		         
		 		 primaryStage.setTitle("Chat Application");
		         primaryStage.setScene(usersConnectionScene);
		         primaryStage.show();
	 
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		
		public static void main(String[] args) {
			launch(args);
		}
}
