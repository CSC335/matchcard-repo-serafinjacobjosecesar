package controller_view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Account;

public class Shop extends BorderPane{
	private BorderPane shopPane = new BorderPane();
	private Label cardSkins = new Label("Card Skins");
	private Button[][] cardBackButtons = new Button[2][3];
	private Button[][] backgroundButtons = new Button[2][3];
	private Button mainMenu = new Button("Main Menu");
	private Button moveCardLeft = new Button();
	private Button moveCardRight = new Button();
	private Button backgroundLeft = new Button();
	private Button backgroundRight = new Button();
	private Button cardPreview = new Button();
	private Label pointsAvaliable;
	private Account currAccount;
	private int priceTierOne = 5;
	private int priceTierTwo = 10;
	private int priceTierThree = 15;
	
	public Shop(Account account) {
		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 3; col++) {
				cardBackButtons[row][col] = new Button();
				backgroundButtons[row][col] = new Button();
			}
		}
		currAccount = account;
		pointsAvaliable = new Label("Points: " + currAccount.getPoints());
		cardPreview.setDisable(true);
		configLayout();
		buttonHandlers();
		mainMenu.setOnAction(event -> {});
	}

	private void configLayout() {
		HBox firstRow = new HBox();
		HBox secondRow = new HBox();
		VBox shopContainer = new VBox();
		
		firstRow.getChildren().addAll(moveCardLeft, cardBackButtons[0][0], cardBackButtons[0][1], cardBackButtons[0][2], moveCardRight);
		secondRow.getChildren().addAll(backgroundLeft, backgroundButtons[0][0], backgroundButtons[0][1], backgroundButtons[0][2], backgroundRight);
		shopContainer.getChildren().addAll(pointsAvaliable, cardPreview,firstRow, secondRow, mainMenu);
		shopPane.setCenter(shopContainer);
	}
	
	private void buttonHandlers() {
		moveCardLeft.setOnAction(event -> {
			
		});
		
		moveCardRight.setOnAction(event -> {
			
		});
		
		backgroundLeft.setOnAction(event -> {
			
		});
		
		backgroundRight.setOnAction(event -> {
			
		});
		
		for (Button[] currButtons : cardBackButtons) {
			for (Button button : currButtons) {
				button.setOnAction(event -> {
					
				});
			}
		}
	}

}
