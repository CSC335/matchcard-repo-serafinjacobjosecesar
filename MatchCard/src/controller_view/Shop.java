package controller_view;

import javafx.geometry.Pos;
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
		HBox cardFirstRow = new HBox();
		HBox cardSecondRow = new HBox();
		HBox backgroundFirstRow = new HBox();
		HBox backgroundSecondRow = new HBox();		
		VBox shopContainer = new VBox();
		
		cardFirstRow.getChildren().addAll(cardBackButtons[0][0], cardBackButtons[0][1], cardBackButtons[0][2]);
		cardSecondRow.getChildren().addAll(cardBackButtons[1][0], cardBackButtons[1][1], cardBackButtons[1][2]);
		backgroundFirstRow.getChildren().addAll(backgroundButtons[0][0], backgroundButtons[0][1], backgroundButtons[0][2]);
		backgroundSecondRow.getChildren().addAll(backgroundButtons[1][0], backgroundButtons[1][1], backgroundButtons[1][2]);
		shopContainer.getChildren().addAll(pointsAvaliable, cardPreview,cardFirstRow, cardSecondRow, backgroundFirstRow, backgroundSecondRow, mainMenu);
		cardFirstRow.setAlignment(Pos.CENTER);
		cardSecondRow.setAlignment(Pos.CENTER);
		backgroundFirstRow.setAlignment(Pos.CENTER);
		backgroundSecondRow.setAlignment(Pos.CENTER);
		shopContainer.setAlignment(Pos.CENTER);
		
		
		shopPane.setCenter(shopContainer);
		this.setCenter(shopPane);
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
