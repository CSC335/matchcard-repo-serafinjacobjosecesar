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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Account;
import model.Game;
import model.GameBoard;
/**
 * Class represents the Main GUI, creates the required stages.
 */
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
	
	/**
	 * Standard Start method
	 * 
	 * @param primaryStage The primary stage viewed by user
	 * 
	 * throws exception if unable to create thread.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		everything = new BorderPane();
		
		//handles the stage closing 
		quit.setOnAction(event -> {
			System.out.println("Stage is closing");
            // Call Platform.exit() to quit the application
			save();
            Platform.exit();
        });
		primaryStage.setOnCloseRequest(event -> {
		    System.out.println("Stage is closing");
		    // Save file
		    save();
		});
		
		// style for Main border pane HERE!!!
		String borderPaneStyle = "-fx-background-color: #7e61ab; ";
		everything.setStyle(borderPaneStyle);
		
		loginPane = new LoginPane();
		LayoutMainMenu();
		eventHandlers();

		Scene loginScene = new Scene(loginPane, 450, 450);
		loginScene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
		Scene mainScene = new Scene(everything,950,800);
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
	/**
	 * LayoutMainMenu is responsible for creating design structures and placing them in stage
	 */
	public void LayoutMainMenu() {
		/*
		 * this is for the main menu after logging in 
		 */
		
		VBox menuPane = new VBox();
		menuPane.setAlignment(Pos.CENTER);
		
		//Styles for MainMenu HERE !!!!!!
		String buttonStyles = "-fx-background-color: #424549; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 30px;";
		String labelStyles = "-fx-text-fill: white; " + "-fx-font-size: 30px;";
		
		mainMenu.setStyle(labelStyles);
		playgame.setStyle(buttonStyles);
		profile.setStyle(buttonStyles);
		quit.setStyle(buttonStyles);
		
		menuPane.setSpacing(20);
		menuPane.getChildren().addAll(mainMenu,playgame,profile,quit);
		everything.setCenter(menuPane);
	}
	
	/**
	 * eventHandlers for main GUI
	 */
	private void eventHandlers() {
		
		playgame.setOnAction(event->{
			
			game = new Game(loginPane.currentAcc);
			GameBoard gameBoard = game.getGameBoardObj();
			everything.setCenter(game.getGameBoardObj());
			
			gameBoard.returnMainMenu.addEventFilter(ActionEvent.ACTION, event2 -> {
				LayoutMainMenu();
			});
			
//			gameBoard.newGame.addEventFilter(ActionEvent.ACTION, event2 -> {
//				game = new Game(loginPane.currentAcc);
//				everything.setCenter(game.getGameBoardObj());
//			});
		});
		
		profile.setOnAction(event->{
			final Label passwordChangePrompt = new Label();
			Account currAccount = loginPane.currentAcc;
			Label user = new Label(currAccount.getUsername());
			Label longestStreak = new Label("Longest Streak: " + String.valueOf(currAccount.getLongestStreak()));
			Label highscore = new Label("HighScore: " + String.valueOf(currAccount.getHighScore()));
			Button profileMainMenu = new Button("Main Menu");
			final TextField newPasswordField =  new TextField();
			Button changePasswordButton = new Button("Change Password");
			
			// Styling for stats page HERE !!!!
			newPasswordField.setMaxWidth(100);
			newPasswordField.setMaxHeight(50);
			
			String buttonStyles = "-fx-background-color: #424549; " +
		                "-fx-text-fill: white; " +
		                "-fx-font-size: 30px;";
			
			String labelStyles = "-fx-text-fill: white; " + "-fx-font-size: 30px;";
			
			 
			profileMainMenu.setStyle(buttonStyles);
			changePasswordButton.setStyle(buttonStyles);
			
			user.setStyle(labelStyles);
			longestStreak.setStyle(labelStyles);
			highscore.setStyle(labelStyles);
			
			
			profileMainMenu.setOnAction(event2->{
				LayoutMainMenu();
			});
			
			changePasswordButton.setOnAction(event2->{
				Account currAcc = loginPane.currentAcc;
				String newPass = newPasswordField.getText();
				
				String InvalidPassPromptStyle = "-fx-text-fill: #900D09; "
						+ "-fx-font-size: 30px;";

				if(newPass.equals("")) {
					passwordChangePrompt.setStyle(InvalidPassPromptStyle);
					passwordChangePrompt.setText("Invalid Password!");
				}
				else if(newPass.equals(currAcc.getPassWord())) {
					passwordChangePrompt.setStyle(InvalidPassPromptStyle);
					passwordChangePrompt.setText("Password Already in Use!");
					newPasswordField.clear();
				}
				else {
					currAcc.setPassword(newPass);
					String passPromptStyle = "-fx-text-fill: white; "
							+ "-fx-font-size: 30px;";
					passwordChangePrompt.setStyle(passPromptStyle);
					passwordChangePrompt.setText("Password Change Succesful!");
					newPasswordField.clear();
				}
			});
			
			VBox statsPane = new VBox();
			statsPane.setAlignment(Pos.CENTER);
			statsPane.setSpacing(20);
			statsPane.getChildren().addAll(passwordChangePrompt,user,longestStreak,highscore,
					profileMainMenu,newPasswordField,changePasswordButton);
			everything.setCenter(statsPane);
		});
		
	}
	
	/**
	 * Save writes to ser file to save account information
	 */
	private void save() {
		File accFile = new File("accounts.ser");
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(accFile))) {
			out.writeObject(loginPane.accountCollections);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
