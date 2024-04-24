package controller_view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Account;
import model.AccountCollections;
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
		
		// style for Main border pane HERE!!!
		String borderPaneStyle = "-fx-background-color: #7e61ab; ";
		everything.setStyle(borderPaneStyle);
		
		loginPane = new LoginPane();
		
		// pull saved accounts
		getSavedAccounts();
		// handles the stage closing 
		//TODO enable this feature
		/*
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
		*/
		
		LayoutMainMenu();
		eventHandlers();

		Scene loginScene = new Scene(loginPane, 450, 450);
		loginScene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
		Scene mainScene = new Scene(everything,950,800);
		primaryStage.setScene(loginScene);
		primaryStage.setResizable(false);
		primaryStage.show();

		
		loginPane.loginB.addEventFilter(ActionEvent.ACTION, event -> {
				new Thread(() -> {
				    try {
				        Thread.sleep(1000);
						if (loginPane.currentAcc != null) {
							Platform.runLater(() -> primaryStage.setScene(mainScene));
						}
				    } catch (InterruptedException e) {
				        e.printStackTrace();
				    }
				}).start();
		});
	}


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
			
			Label Gamemode_label = new Label("Game Modes");
			Button Gamemode1 = new Button("2 x 4");
			Button Gamemode2 = new Button("3 rounds");
			
			String buttonStyles = "-fx-background-color: #424549; " +
	                "-fx-text-fill: white; " +
	                "-fx-font-size: 30px;";
		
			String labelStyles = "-fx-text-fill: white; " + "-fx-font-size: 30px;";
			
			Gamemode_label.setStyle(labelStyles);
			Gamemode1.setStyle(buttonStyles);
			Gamemode2.setStyle(buttonStyles);
			
			VBox containerGamemodes = new VBox();
			containerGamemodes.setAlignment(Pos.CENTER);
			containerGamemodes.setSpacing(10);
			
			containerGamemodes.getChildren().addAll(Gamemode_label,Gamemode1,Gamemode2);
			everything.setCenter(containerGamemodes);
			
			// 2 by 4
			Gamemode1.setOnAction(event2 ->{
				GameMode2 game2 = new GameMode2(loginPane.currentAcc);
				everything.setCenter(game2);
				game2.returnMainMenu.addEventFilter(ActionEvent.ACTION, event3 -> {
					LayoutMainMenu();
				});
			});
			
			// 3 rounds
			Gamemode2.setOnAction(event2 ->{
				GameMode1 game1 = new GameMode1(loginPane.currentAcc);
				everything.setCenter(game1);
				game1.returnMainMenu.addEventFilter(ActionEvent.ACTION, event3 -> {
					LayoutMainMenu();
				});
			});
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
			Label bestTimeG1 = new Label("Game mode 1 Best Time");
			Label bestTimeG2 = new Label("Game mode 2 Best Time");
			
			Label G1BestTime;
			if(currAccount.getG1Time()==0) {
				G1BestTime = new Label("");
			}
			else {
				int time = currAccount.getG1Time();
				G1BestTime = new Label(String.format("%02d : %02d", time/60,time%60));
			}
			Label G2BestTime;
			if(currAccount.getG2Time()==0) {
				G2BestTime = new Label("");
			}
			else {
				int time = currAccount.getG2Time();
				G2BestTime = new Label(String.format("%02d : %02d", time/60,time%60));
			}
			
			
			
			// Styling for stats page HERE !!!!
			newPasswordField.setMaxWidth(100);
			newPasswordField.setMaxHeight(50);
			
			String buttonStyles = "-fx-background-color: #424549; " +
		                "-fx-text-fill: white; " +
		                "-fx-font-size: 30px;";
			
			String labelStyles = "-fx-text-fill: white; " + "-fx-font-size: 30px;";
			
			
			profileMainMenu.setStyle(buttonStyles);
			changePasswordButton.setStyle(buttonStyles);
			
			bestTimeG1.setStyle(labelStyles);
			bestTimeG2.setStyle(labelStyles);
			G1BestTime.setStyle(labelStyles);
			G2BestTime.setStyle(labelStyles);
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
			statsPane.getChildren().addAll(passwordChangePrompt,user,bestTimeG1,G1BestTime,bestTimeG2,G2BestTime,longestStreak,highscore,
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
	
	private void getSavedAccounts() {
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("accounts.ser");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		try (ObjectInputStream in = new ObjectInputStream(fileIn)) {
			AccountCollections readAccounts = (AccountCollections) in.readObject();
			loginPane.accountCollections = readAccounts;

		} catch (Exception e) {
			e.printStackTrace();
		}
		// set accountCollections internal arrayList to ArrayList read in
	}
	
}
