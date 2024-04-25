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
	private Button[][] shopButtons = new Button[2][3];
	private Button mainMenu = new Button("Main Menu");
	private Account currAccount;
	
	public Shop(Account account) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				shopButtons[i][j] = new Button();
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
		
		firstRow.getChildren().addAll(shopButtons[0][0], shopButtons[0][1], shopButtons[0][2]);
		secondRow.getChildren().addAll(shopButtons[1][0], shopButtons[1][1], shopButtons[1][2]);
		shopContainer.getChildren().addAll(firstRow, secondRow, mainMenu);
		shopPane.setCenter(shopContainer);
	}
	
	

}
