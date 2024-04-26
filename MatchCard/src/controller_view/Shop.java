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
	private Account currAccount;
	
	public Shop(Account account) {
		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 3; col++) {
				cardBackButtons[row][col] = new Button();
				backgroundButtons[row][col] = new Button();
			}
		}
		currAccount = account;
		configLayout();
		mainMenu.setOnAction(event -> {});
	}

	private void configLayout() {
		HBox firstRow = new HBox();
		HBox secondRow = new HBox();
		VBox shopContainer = new VBox();
		
		firstRow.getChildren().addAll(cardBackButtons[0][0], cardBackButtons[0][1], cardBackButtons[0][2]);
		secondRow.getChildren().addAll(backgroundButtons[0][0], backgroundButtons[0][1], backgroundButtons[0][2]);
		shopContainer.getChildren().addAll(firstRow, secondRow, mainMenu);
		shopPane.setCenter(shopContainer);
	}
	
	

}
