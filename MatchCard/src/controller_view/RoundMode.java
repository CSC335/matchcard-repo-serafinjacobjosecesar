package controller_view;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.AbstractCardCollection;
import model.Account;
import model.AnimalCollection;
import model.Card;
import model.GameBoard;
import model.WinScreen;
/**
 * RoundMode public class that extends Border Pane represents a round based game mode
 */
public class RoundMode extends BorderPane{
	GridPane gridPane = new GridPane();
	private Label statusOfGame = new Label("Click to make a move");
	private HBox moveContainer = new HBox(statusOfGame);
	private VBox outsideContainer = new VBox();
	private AbstractCardCollection deck;
	private GameBoard gameboard;
	private Button[][] ButtonArr;
	private Card[][] cardArr;
	private int col;
	private int row;
	private Group root = new Group();
	public Button returnMainMenu = new Button("Main Menu");
	private ArrayList<int[]> toCompare = new ArrayList<>();
	boolean flag =false;
	int round = 0;
	Account player;
	private Label timerLabel;
	private Timeline timerliner;
	private int clock =0;
	
	/**
	 * RoundMode : public constructor that generates the round based game mode
	 * @param player : Account object that represents the current active account
	 * @param currDeck : AbstractCardCollection that represents the current card collection in play
	 */
	public RoundMode(Account player, AbstractCardCollection currDeck) {
		col = 3;
		row = 2;
		this.player = player;
		
		int scale = 850/col;
		scale = (scale+170)/2;
		
//		deck = new AnimalCollection(col, player.getCurrCardBack());
		deck = currDeck.getNewDeck(row*col, col);
		gameboard = new GameBoard(player,deck,col,row,0);
		ButtonArr = gameboard.getButtonArray();
		initClock();
		VBox clockContainer = new VBox(timerLabel);
		clockContainer.setAlignment(Pos.CENTER);
		clockContainer.setMargin(timerLabel, new Insets(5));
		this.setTop(clockContainer);
		start();
	}
	
	/**
	 * start : drives round mode
	 */
	private void start() {
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		cardArr = gameboard.getCardArr();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				final int row = i;
				final int col = j;
				final Card currCard = cardArr[i][j];
				ImageView frontView = new ImageView(currCard.getImage());
				ImageView backView = new ImageView(currCard.getBackOfCard());
				
				ButtonArr[i][j].setGraphic(backView);
				gridPane.add(ButtonArr[i][j], j, i);
				ButtonArr[i][j].setOnAction((event)->{ 
					int [] cords = {row,col};
					ButtonArr[row][col].setGraphic(frontView);
					
					if(!currCard.isItFlipped()) {
						ButtonArr[row][col].setGraphic(frontView);
						currCard.flip();
						gameboard.toCompare.add(cords);
						new Thread(() -> {
						    try {
						    	if (toCompare.size()== 2) {
						    		gameboard.wait(true);
						    	}
 						    	// Makes a pause so both cards face up, (shows the frontView)
						        Thread.sleep(250);
						        Platform.runLater(() -> {
						        	flag = gameboard.check();
						        	if (flag) {
						        		win();
						        	}
				                    
				                });
						    } catch (InterruptedException e) {
						        e.printStackTrace();
						    }
						}).start();						
						
					}
					else {
					}
				});
				
			}
		}
		outsideContainer.setAlignment(Pos.CENTER);
		statusOfGame.setAlignment(Pos.CENTER);
		moveContainer.setAlignment(Pos.CENTER);
		outsideContainer.getChildren().addAll(gridPane, moveContainer);
		this.setCenter(outsideContainer);
	}
	
	/**
	 * createConfettiPiece : generates "confetti" objects
	 * @return rect : Rectangle object that acts as the "confetti"
	 */
    private Rectangle createConfettiPiece() {
	    final Random rand = new Random();
        Rectangle rect = new Rectangle(10, 20, Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        rect.setX(rand.nextDouble() * 950);
        rect.setY(rand.nextDouble() * 950);
        rect.setRotate(rand.nextDouble() * 360);
        return rect;
    }
    
    /**
     * moveConfetti : updates the position of the "confetti" objects
     * @param root : Group that holds all "confetti" objects
     */
    private void moveConfetti(Group root) {
	    final Random rand = new Random();
        for (Object obj : root.getChildren()) {
            if (obj instanceof Rectangle) {
                Rectangle rect = (Rectangle) obj;
                rect.setY(rect.getY() + 3);
                rect.setRotate(rect.getRotate() + 3);

                if (rect.getY() > 950) {
                    rect.setY(-10);
                    rect.setX(rand.nextDouble() * 950);
                }
            }
        }
    }
    
    /**
     * initClock : initializes the clock object for round mode
     */
    public void initClock() {
		timerLabel = new Label("00 : 00");
		String labelStyles = "-fx-text-fill: white; " + "-fx-font-size: 20px;";
		timerLabel.setStyle(labelStyles);
		timerLabel.setAlignment(Pos.CENTER);
		timerliner = new Timeline(
				new KeyFrame(Duration.seconds(1), event -> {
					clock++;
					String timerStr = String.format("%02d : %02d", clock/60,clock%60);
					timerLabel.setText(timerStr);
				})
		);
		timerliner.setCycleCount(Timeline.INDEFINITE);
		timerliner.play();
	}
	
    /**
     * stopClock : stops the game clock
     */
	public void StopClock() {
		timerliner.stop();
	}
	
	/**
	 * win Goes to Win page until all 3 rounds of cards has been played
	 */
	public void win() {
		if (round<2) {
			updateGame();
			start();
			return;
		}
		StopClock();
	    Label winPrompt = new Label("You Won!");

        VBox buttonsLayout = new VBox(10);
        
        //buttonsLayout.setTranslateX(950/2 - 200); // Adjust X as needed
        //buttonsLayout.setTranslateY(); // Adjust Y as needed
        String buttonStyles = "-fx-background-color: #424549; " +
                              "-fx-text-fill: white; " +
                              "-fx-font-size: 30px;";

        returnMainMenu.setStyle(buttonStyles);
        buttonsLayout.getChildren().addAll(winPrompt, returnMainMenu);
        String labelStyles = "-fx-text-fill: black; " + "-fx-font-size: 100px;";
        winPrompt.setStyle(labelStyles);
        buttonsLayout.setAlignment(Pos.CENTER);
        
        int confetti = 750;
        for (int i = 0; i < confetti; i++) {
            Rectangle rect = createConfettiPiece();
            root.getChildren().add(rect);
        }
        
        returnMainMenu.setOnAction(Event -> {});
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e -> moveConfetti(root)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
		
		winPrompt.setAlignment(Pos.CENTER);
		winPrompt.setStyle(labelStyles);
		buttonsLayout.toFront();
		setTop(buttonsLayout);
		outsideContainer.getChildren().clear();
		outsideContainer.getChildren().add(root);
		this.setCenter(outsideContainer);
		this.player.setGamemode2Hiscore(clock);
	}

	/**
	 * updateGame Updates the game, adds another column and row to the board. Adds more cards to the deck
	 */
	private void updateGame() {
		round++; 
		col++; 
		row++;
		deck = deck.getNewDeck(col*row, gameboard.buttonScale);
		gameboard = new GameBoard(player,deck,col,row,0);
		ButtonArr = gameboard.getButtonArray();
		outsideContainer.getChildren().clear();
		GridPane newPane = new GridPane();
		gridPane = newPane;
	}

}
