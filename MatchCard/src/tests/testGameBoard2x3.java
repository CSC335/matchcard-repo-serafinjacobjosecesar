package tests;

import javafx.scene.layout.BorderPane;
import java.time.LocalDate;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AbstractCardCollection;
import model.Account;
import model.AnimalCollection;
import model.GameBoard;

public class testGameBoard2x3 extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	private GameBoard gameboard;
	private BorderPane everything;
	private AbstractCardCollection deck;

	@Override
	public void start(Stage primaryStage) throws Exception {

		LayoutGUI();
		Scene scene = new Scene(everything, 1000, 1000);
		primaryStage.setScene(scene);

		primaryStage.setResizable(false);
		primaryStage.show();

	}

	private void LayoutGUI() {
		makeABoard();
		everything = new BorderPane();
//		everything.setCenter(gameboard);

	}
	
	private void makeABoard() {
//		int col = 3;
//		int row = 2;
//		deck = new AnimalCollection(col);
//		
//		int scale = 950 /row;
//		deck = deck.getNewDeck(col);
////		deck.shuffle();
//		Account player = new Account("1","1");
//		gameboard = new GameBoard(player,deck,col,row);
		
//		numberOfPairs = deck.cols;
	}

}

