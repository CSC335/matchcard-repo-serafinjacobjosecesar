package controller_view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Optional;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Account;
import model.Game;

public class MainGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private LoginPane loginPane;
	private BorderPane everything;
	//private Button playGame;
	private Label mainMenu = new Label("Main Menu");
	private Button playgame = new Button("New Game");
	private Button profile = new Button("Profile");
	private Button quit = new Button("Quit");
	private Game game;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		everything = new BorderPane();
		loginPane = new LoginPane();
		LayoutMainMenu();
		eventHandlers();

		Scene loginScene = new Scene(loginPane, 400, 500);
		loginScene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
		Scene mainScene = new Scene(everything,1000,1000);
		primaryStage.setScene(loginScene);
		primaryStage.setResizable(false);
		primaryStage.show();

		
		loginPane.loginB.addEventFilter(ActionEvent.ACTION, event -> {
			if (loginPane.currentAcc != null) {
				new Thread(() -> {
				    try {
				        Thread.sleep(1000);
		                Platform.runLater(() -> primaryStage.setScene(mainScene));
				    } catch (InterruptedException e) {
				        e.printStackTrace();
				    }
				}).start();
			}
		});
	}

//	private void LayoutGUI() {
//		// login pane
//		loginPane.setPadding(new Insets(10));
//		playGame = new Button("Play Game");
//		everything.setCenter(playGame);
//		
//	}
	
	private void LayoutMainMenu() {
		/*
		 * this is for the main menu after loggin in 
		 */
		
		VBox menuPane = new VBox();
		menuPane.setAlignment(Pos.CENTER);
		menuPane.getChildren().addAll(mainMenu,playgame,profile,quit);
		everything.setCenter(menuPane);
	}
	
	private void eventHandlers() {
		
		playgame.setOnAction(event->{
			
			game = new Game(loginPane.currentAcc);
			everything.setCenter(game.getGameBoardObj());
			
		});
		
		profile.setOnAction(event->{
			Account currAccount = loginPane.currentAcc;
			Label user = new Label(currAccount.getUsername());
			Label longestStreak = new Label("Longest Streak: " + "0");
			
		});
		quit.setOnAction(event->{
			
		});
		
	}

	private void save(Stage stage) {
		File accFile = new File("accounts.ser");
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(accFile))) {
			out.writeObject(loginPane.accountCollections);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
