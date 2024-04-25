package controller_view;


import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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


public class GameMode2 extends BorderPane{
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
	private int k=0;
	public Button returnMainMenu = new Button("Main Menu");
	public Button newGame = new Button("New Game");
	private ArrayList<int[]> toCompare = new ArrayList<>();
	private Group root = new Group();
	boolean flag =false;
	int round = 0;
	Account player;
	private Label timerLabel;
	private int clock = 0;
	private Timeline timeline;
	private final Random rand = new Random();
	
	public GameMode2(Account player) {
		col = 4;
		row = 2;
		this.player = player;
		deck = new AnimalCollection(col);
		
		int scale = 850 /col;
		deck = deck.getNewDeck(col*row,scale);
		gameboard = new GameBoard(player,deck,col,row,0);
		ButtonArr = gameboard.getButtonArray();
		start(0);
	}

	private void start(int t) {
		k=t;
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		cardArr = gameboard.getCardArr();
		for (int i = 0; i < row+k; i++) {
			for (int j = 0; j < col+k; j++) {
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
		initClock();
		VBox clockContainer = new VBox(timerLabel);
		clockContainer.setAlignment(Pos.CENTER);
		clockContainer.setMargin(timerLabel, new Insets(30));
		this.setTop(clockContainer);
	}
	
	public void initClock() {
		timerLabel = new Label("00 : 00");
		
		String labelStyles = "-fx-text-fill: white; " + "-fx-font-size: 30px;";
		timerLabel.setStyle(labelStyles);
		timerLabel.setAlignment(Pos.CENTER);
		timeline = new Timeline(
				new KeyFrame(Duration.seconds(1), event -> {
					clock++;
					String timerStr = String.format("%02d : %02d", clock/60,clock%60);
					timerLabel.setText(timerStr);
				})
		);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
	public void StopClock() {
		timeline.stop();
	}
	
	private Rectangle createConfettiPiece() {
        Rectangle rect = new Rectangle(10, 20, Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        rect.setX(rand.nextDouble() * 950);
        rect.setY(rand.nextDouble() * 950);
        rect.setRotate(rand.nextDouble() * 360);
        return rect;
    }
    
    private void moveConfetti(Group root) {
        for (Object obj : root.getChildren()) {
            if (obj instanceof Rectangle) {
                Rectangle rect = (Rectangle) obj;
                rect.setY(rect.getY() + 3);
                rect.setRotate(rect.getRotate() + 1);
                
                if (rect.getY() > 950) {
                    rect.setY(-20);
                    rect.setX(rand.nextDouble() * 950);
                }
            }
        }
    }
    
	public void win() {
		StopClock();
	    Label winPrompt = new Label("You Won!");

        VBox buttonsLayout = new VBox(10);
        
        //buttonsLayout.setTranslateX(950/2 - 200); // Adjust X as needed
        //buttonsLayout.setTranslateY(); // Adjust Y as needed
        String buttonStyles = "-fx-background-color: #424549; " +
                              "-fx-text-fill: white; " +
                              "-fx-font-size: 30px;";

        returnMainMenu.setStyle(buttonStyles);
        newGame.setStyle(buttonStyles);
        buttonsLayout.getChildren().addAll(winPrompt, returnMainMenu, newGame);
        String labelStyles = "-fx-text-fill: black; " + "-fx-font-size: 100px;";
        winPrompt.setStyle(labelStyles);
        buttonsLayout.setAlignment(Pos.CENTER);
        
        int confetti = 500;
        for (int i = 0; i < confetti; i++) {
            Rectangle rect = createConfettiPiece();
            root.getChildren().add(rect);
        }
        returnMainMenu.setOnAction(Event -> {});
        newGame.setOnAction(Event -> {});
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e -> moveConfetti(root)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
		
		winPrompt.setAlignment(Pos.CENTER);
		winPrompt.setStyle(labelStyles);
		buttonsLayout.toFront();

		outsideContainer.getChildren().clear();
		outsideContainer.getChildren().add(buttonsLayout);
		outsideContainer.getChildren().add(root);
		this.setCenter(outsideContainer);
		this.player.setGamemode2Hiscore(clock);
	}
}
