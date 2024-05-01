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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AbstractCardCollection;
import model.Account;
import model.AccountCollections;
import model.AnimalCollection;
import model.CarCollection;
import model.FoodCollection;
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
	private Button shopButton = new Button("Shop");
	private Button playgame = new Button("New Game");
	private Button profile = new Button("Profile");
	private Button quit = new Button("Quit");
	private Game game;
	private AbstractCardCollection currDeck;
	
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
		loginPane = new LoginPane();
		// style for Main border pane HERE!!!
		System.out.println("(MainGUI 70) loginPane.currAccount: " + loginPane.currentAcc);
		if (loginPane.currentAcc != null) {
			String borderPaneStyle = loginPane.currentAcc.getCurrBackground();
			System.out.println("(MainGUI 72)borderPaneStyle: " + borderPaneStyle);
			everything.setStyle(borderPaneStyle);
		} else {
			String borderPaneStyle = "-fx-background-color: #7e61ab;";
			everything.setStyle(borderPaneStyle);
		}
		
		
		
		
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
		shopButton.setStyle(buttonStyles);
		
		menuPane.setSpacing(20);
		menuPane.getChildren().addAll(mainMenu,playgame,shopButton,profile,quit);
		everything.setCenter(menuPane);
		
		if (loginPane.currentAcc != null) {
			String borderPaneStyle = loginPane.currentAcc.getCurrBackground();
			everything.setStyle(borderPaneStyle);
		}
    	 else {
    		String borderPaneStyle = "-fx-background-color: #7e61ab;";
			everything.setStyle(borderPaneStyle);
    	}
}
	
	
	/**
	 * eventHandlers for main GUI
	 */
	private void eventHandlers() {
		
		playgame.setOnAction(event->{
			
			Label Collection_label = new Label("Card Decks");
			Button carCollection = new Button();
			Button animalCollection = new Button();
			Button foodCollection = new Button();
			
			double size = 150.0;
			int picWidth = 250;
			int picHeight = 350;
			
			
			carCollection.setPrefSize(size,size);
			animalCollection.setPrefSize(size, size);
			foodCollection.setPrefSize(size, size);
			
			ImageView carDeckPic = new ImageView(getImage("carButton", picWidth, picHeight));
			carCollection.setGraphic(carDeckPic);
			ImageView aniDeckPic = new ImageView(getImage("animalButton",picWidth, picHeight));
			animalCollection.setGraphic(aniDeckPic);
			ImageView foodDeckPic = new ImageView(getImage("foodButton",picWidth, picHeight));
			foodCollection.setGraphic(foodDeckPic);
			
			
			Label Gamemode_label = new Label("Game Modes");
			Button Gamemode1 = new Button("2 x 4");
			Button Gamemode2 = new Button("3 rounds");
			Gamemode1.setMinSize(size,size);
			Gamemode2.setMinSize(size,size);
			
			
			String buttonStyles = "-fx-background-color: #424549; " +
	                "-fx-text-fill: white; " +
	                "-fx-font-size: 30px;";
		
			String labelStyles = "-fx-text-fill: white; " + "-fx-font-size: 30px;";
			
			Gamemode_label.setStyle(labelStyles);
			Gamemode1.setStyle(buttonStyles);
			Gamemode2.setStyle(buttonStyles);
			
			Collection_label.setStyle(labelStyles);
//			carCollection.setStyle(buttonStyles);
//			foodCollection.setStyle(buttonStyles);
//			animalCollection.setStyle(buttonStyles);
			
			
			VBox containerGamemodes = new VBox();
			containerGamemodes.setAlignment(Pos.CENTER);
			containerGamemodes.setSpacing(10);
			
			VBox catagoriesContainer = new VBox();
			HBox containerDeck = new HBox();
			
			VBox carsContainer = new VBox();
			VBox foodContainer = new VBox();
			VBox animalsContainer = new VBox();
			
			containerDeck.setAlignment(Pos.CENTER);
			containerDeck.setSpacing(10);
			
			Label carLabel = new Label("Cars");
			Label foodLabel = new Label("Food");
			Label animalsLabel = new Label("Animals");
			
			carLabel.setStyle(labelStyles);
			foodLabel.setStyle(labelStyles);
			animalsLabel.setStyle(labelStyles);
			
			carsContainer.getChildren().addAll(carLabel, carCollection);
			carsContainer.setAlignment(Pos.CENTER);
			carsContainer.setSpacing(5);
			foodContainer.getChildren().addAll(foodLabel, foodCollection);
			foodContainer.setAlignment(Pos.CENTER);
			foodContainer.setSpacing(5);
			animalsContainer.getChildren().addAll(animalsLabel, animalCollection);
			animalsContainer.setAlignment(Pos.CENTER);
			animalsContainer.setSpacing(5);
			
			containerDeck.getChildren().addAll(carsContainer, foodContainer,animalsContainer);
			containerGamemodes.getChildren().addAll(Gamemode_label,Gamemode1,Gamemode2);
			
			VBox containerUni = new VBox();
			containerUni.setAlignment(Pos.CENTER);
			containerUni.setSpacing(10);
			
			containerUni.getChildren().addAll(Collection_label,containerDeck);
			
			everything.setCenter(containerUni);
			
			currDeck = null;
			carCollection.setOnMouseClicked( event1 ->{
				currDeck = new CarCollection(10,loginPane.currentAcc.getCurrCardBack() );
				chooseGameMode(Gamemode1, Gamemode2, containerGamemodes, containerUni);
				
			});
			
			foodCollection.setOnMouseClicked( event2 ->{
				currDeck = new FoodCollection(10,loginPane.currentAcc.getCurrCardBack() );
				chooseGameMode(Gamemode1, Gamemode2, containerGamemodes, containerUni);
			});
			
			animalCollection.setOnMouseClicked( event2 ->{
				currDeck = new AnimalCollection(10,loginPane.currentAcc.getCurrCardBack() );
				chooseGameMode(Gamemode1, Gamemode2, containerGamemodes, containerUni);
			});	
			
		});
		
		
		shopButton.setOnAction(event -> {
			Account currAccount = loginPane.currentAcc;
			Shop shopDisplay = new Shop(currAccount);
			everything.setCenter(shopDisplay);
			
			shopDisplay.returnMainMenu.addEventFilter(ActionEvent.ACTION, event1 ->{
				LayoutMainMenu();
			});
		});
		
		
		profile.setOnAction(event->{
		    System.out.println("RETURN MAIN HERE202");
		    Account currAccount = loginPane.currentAcc;
		    Profile prof = new Profile(currAccount);
		    everything.setCenter(prof);
		    prof.profileMainMenu.setOnAction(event2 -> {
		        LayoutMainMenu();
		    });
		});
		
		
	}


	private void chooseGameMode(Button Gamemode1, Button Gamemode2, VBox containerGamemodes, VBox containerUni) {
		containerUni.getChildren().clear();
		containerUni.getChildren().add(containerGamemodes);
		// 2 by 4
		Gamemode1.setOnAction(event2 ->{
			NormalMode game2 = new NormalMode(loginPane.currentAcc, currDeck);
			everything.setCenter(game2);
			game2.returnMainMenu.addEventFilter(ActionEvent.ACTION, event3 -> {
				LayoutMainMenu();
			});
		});
		
		// 3 rounds
		Gamemode2.setOnAction(event2 ->{
			RoundMode game1 = new RoundMode(loginPane.currentAcc, currDeck);
			everything.setCenter(game1);
			game1.returnMainMenu.addEventFilter(ActionEvent.ACTION, event3 -> {
				LayoutMainMenu();
			});
		});
	}
	
	private Image getImage(String file, int picWidth, int picHeight) {
		String userDir = System.getProperty("user.dir");
		String fileName = "";
		
		if (userDir.substring(0, 1).equals("/")) {
		    fileName = "file:" + userDir + "/Card Images/DeckImages/";
		} 
		else {
			userDir = userDir.replace('\\', '/');
			fileName = "file:/" + userDir + "/Card Images/DeckImages/";
		}
		Image image1 = new Image(fileName+file+".png",picWidth, picHeight,false,false);
		return image1;
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
