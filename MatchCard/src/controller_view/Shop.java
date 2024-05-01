package controller_view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Account;

/**
 * Shop public class that extends Border Pane represents the shop which holds
 * new card backs and backgrounds
 */
public class Shop extends BorderPane {
	private BorderPane shopPane = new BorderPane();
	private Button[] items = new Button[12];
	public Button returnMainMenu = new Button("Main Menu");
	private Button cardPreview = new Button();
	private Label pointsAvaliable;
	private Account currAccount;
	private int cardPrice = 200;
	private int backgroundPrice = 100;
	private Button defaultCardBack = new Button("Default Card Back");
	private Button defaultBackground = new Button("Default Background");
	private Label sysReply = new Label("");
	private String defaultCardBackStr = "matchCard(backClose)";
	private String defaultBackgroundStr = "-fx-background-color: #7e61ab";
	private int height = 100;
	private int width = 75;
	private Label priceCardBack = new Label("Price of CardBack: " + cardPrice);
	private Label priceBackground = new Label("Price of Background: " + backgroundPrice);

	/**
	 * Shop : public constructor for shop
	 * 
	 * @param account : Account object represents the active user account
	 */
	public Shop(Account account) {
		currAccount = account;
		cardPreview.setPrefSize(150, 200);
		cardPreview.setStyle(currAccount.getCurrBackground());
		Image cardBackImg = getFileName(currAccount.getCurrCardBack(), 150, 200);
		ImageView cardBackView = new ImageView(cardBackImg);
		cardPreview.setGraphic(cardBackView);

		if (account.getBack() != null) {
			Image back = new Image(account.getBack());
			ImageView backView = new ImageView(back);
			cardPreview.setGraphic(backView);
		}

		for (int i = 0; i < 12; i++) {
			items[i] = new Button();
		}

		pointsAvaliable = new Label("Points: " + currAccount.getPoints());
		configLayout();
		buttonHandlers();
		returnMainMenu.setOnAction(event -> {
		});
	}

	/**
	 * configLayout : configures the layout of shop
	 */
	private void configLayout() {

		String buttonStyles = "-fx-background-color: #424549; " + "-fx-text-fill: white; " + "-fx-font-size: 20px;";
		String labelStyles = "-fx-text-fill: white; " + "-fx-font-size: 20px;";

		sysReply.setStyle(labelStyles);
		pointsAvaliable.setStyle(labelStyles);

		HBox previewContainer = new HBox();
		VBox cardBackContainer = new VBox();
		VBox backgroundContainer = new VBox();
		HBox tier1Row = new HBox();
		HBox tier2Row = new HBox();
		HBox tier3Row = new HBox();

		VBox shopContainer = new VBox();

		itemSetting();
		for (Button currItem : items) {
			currItem.getStyleClass().add("no-focus-ring");

			if (currItem.getId().contains("-fx-background-color:")) {
				currItem.setPrefSize(width, height);
				String background = currItem.getId();
				currItem.setStyle(background);
			} else {
				currItem.setStyle("-fx-background-color:rgba(0, 0, 0, 0.5);");
				currItem.setMinSize(width, height);
				String cardBack = currItem.getId();
				Image cardBackImg = getFileName(cardBack, width, height);
				ImageView cardBackView = new ImageView(cardBackImg);
				currItem.setGraphic(cardBackView);
			}
		}

		priceCardBack.setStyle(labelStyles);
		priceBackground.setStyle(labelStyles);
		cardBackContainer.getChildren().addAll(priceCardBack, defaultCardBack);
		cardBackContainer.setAlignment(Pos.CENTER);
		cardBackContainer.setSpacing(15);
		backgroundContainer.getChildren().addAll(priceBackground, defaultBackground);
		backgroundContainer.setAlignment(Pos.CENTER);
		backgroundContainer.setSpacing(15);
		previewContainer.getChildren().addAll(cardBackContainer, cardPreview, backgroundContainer);

		tier1Row.getChildren().addAll(items[0], items[1], items[2], items[3]);
		tier2Row.getChildren().addAll(items[4], items[5], items[6], items[7]);
		tier3Row.getChildren().addAll(items[8], items[9], items[10], items[11]);
		shopContainer.getChildren().addAll(pointsAvaliable, previewContainer, sysReply, tier1Row, tier2Row, tier3Row,
				returnMainMenu);

		previewContainer.setAlignment(Pos.CENTER);
		tier1Row.setAlignment(Pos.CENTER);
		tier2Row.setAlignment(Pos.CENTER);
		tier3Row.setAlignment(Pos.CENTER);

		String tierStyle = "-fx-border-color: Black;" + " -fx-background-color: rgba(0, 0, 0, 0.5);"
				+ "-fx-border-radius: 20px;" + "-fx-background-radius: 20px;" + "-fx-border-width: 5px";
		int tierW = 500;
		int tierH = 150;

		tier1Row.setStyle(tierStyle);
		tier2Row.setStyle(tierStyle);
		tier3Row.setStyle(tierStyle);

		// tier1Row.setStyle("-fx-border-color: rgba(0,0,0,0.75)");

		tier1Row.setMinSize(tierW, tierH);
		tier2Row.setMinSize(tierW, tierH);
		tier3Row.setMinSize(tierW, tierH);

		tier1Row.setMaxSize(tierW, tierH);
		tier2Row.setMaxSize(tierW, tierH);
		tier3Row.setMaxSize(tierW, tierH);

		previewContainer.setSpacing(10);
		tier1Row.setSpacing(15);
		tier2Row.setSpacing(15);
		tier3Row.setSpacing(15);
		shopContainer.setSpacing(5);

		shopContainer.setAlignment(Pos.CENTER);

		returnMainMenu.setStyle(buttonStyles);
		defaultCardBack.setStyle(buttonStyles);
		defaultBackground.setStyle(buttonStyles);

		shopPane.setCenter(shopContainer);
		this.setCenter(shopPane);
	}

	/**
	 * buttonHandlers : acts as the button handlers for shop
	 */
	private void buttonHandlers() {
		for (Button currItem : items) {
			currItem.setOnAction(event -> {

				if (!currAccount.inventory.contains(currItem.getId())) {
					int price = getPrice(currItem);
					if (currAccount.getPoints() >= price) {
						purchase(price);
						setItem(currItem);
						currAccount.inventory.add(currItem.getId());
						updatePoints();
					} else {
						sysReply.setText("Insufficient Funds");
					}

				} else {
					setItem(currItem);
					sysReply.setText("Item Already Owned");
				}

			});
		}

		defaultCardBack.setOnAction(event -> {
			currAccount.setCurrCardBack(defaultCardBackStr);
			Image cardBackImg = getFileName(defaultCardBackStr, 150, 200);
			ImageView cardBackView = new ImageView(cardBackImg);
			cardPreview.setGraphic(cardBackView);
			sysReply.setText("Changed to default card back");

		});

		defaultBackground.setOnAction(event -> {
			this.setStyle(defaultBackgroundStr);
			currAccount.setCurrBackground(defaultBackgroundStr);
			cardPreview.setStyle(currAccount.getCurrBackground());
			sysReply.setText("Changed to default background");
		});

	}

	/**
	 * setItem : updates elements in shop to the selected element
	 * 
	 * @param currItem : Button that represents the selected item from shop
	 */
	private void setItem(Button currItem) {
		if (currItem.getId().contains("-fx-background-color:")) {
			String background = currItem.getId();
			currAccount.setCurrBackground(background);
			this.setStyle(background);
			cardPreview.setStyle(background);
		} else {
			String cardBack = currItem.getId();
			currAccount.setCurrCardBack(cardBack);

			Image cardBackImg = getFileName(cardBack, 150, 200);
			ImageView cardBackView = new ImageView(cardBackImg);
			cardPreview.setGraphic(cardBackView);

		}
	}

	/**
	 * getPrice : gets the price of the item selected
	 * 
	 * @param currItem : Button that represents the selected item from shop
	 * @return cardPrice : int that represents the cost of the element
	 */
	private int getPrice(Button currItem) {
		if (currItem.getId().contains("-fx-background-color:")) {
			return backgroundPrice;
		}
		return cardPrice;
	}

	/**
	 * purchase : updates account to withdraw cost of new element
	 * 
	 * @param price : int that represent the cost of the element
	 */
	private void purchase(int price) {
		currAccount.withdrawPoints(price);
		updatePoints();
		sysReply.setText("Purchase Successful!");
	}

	/**
	 * updates the display of players current points
	 */
	private void updatePoints() {
		pointsAvaliable.setText("Points: " + currAccount.getPoints());
	}

	/**
	 * itemSetting : sets the items in the shop
	 */
	private void itemSetting() {

		// tier 1
		items[0].setId("blackAndWhite(back_uneditted)");
		items[1].setId("CartoonStyle(back_uneditted)");
		items[2].setId("-fx-background-color: #61ab8c;");
		items[3].setId("-fx-background-color: #6b61ab;");

		// tier 2
		items[4].setId("cyberPunk(back_uneditted)");
		items[5].setId("EightBit(back_uneditted)");
		items[6].setId("-fx-background-color: #9e7f57;");
		items[7].setId("-fx-background-color: #9e5788;");

		// tier 3
		items[8].setId("horseAss(back_uneditted)");
		items[9].setId("steamPunk(back_uneditted)");
		items[10].setId("-fx-background-color: #7a1815;");
		items[11].setId("-fx-background-color: #bd7777;");

	}

	/**
	 * getFileName : gets the file name of the provided string
	 * 
	 * @param str : String that represents the file name
	 * @param w   : int that represent the width of the image
	 * @param h   : int that represents the height of the image
	 * @return image1 : Image object that is the image to display on the shop
	 *         buttons
	 */
	public Image getFileName(String str, int w, int h) {
		String userDir = System.getProperty("user.dir");
		String fileName = "";

		if (userDir.substring(0, 1).equals("/")) {
			fileName = "file:" + userDir + "/Card Images/CardBacks/";
		} else {
			userDir = userDir.replace('\\', '/');
			fileName = "file:/" + userDir + "/Card Images/CardBacks/";
		}
		Image image1 = new Image(fileName + str + ".jpg", w, h, false, false);
		return image1;
	}

}
