package controller_view;


import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	boolean flag =false;
	int round = 0;
	Account player;
	private Label timerLabel;
	private int clock = 0;
	
	public GameMode2(Account player) {
		System.out.println("here");
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
						System.out.println("Nothing happened");
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
		this.setTop(timerLabel);
	}
	
	public void initClock() {
		timerLabel = new Label("00 : 00");
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(1), event -> {
					clock++;
					String timerStr = String.format("%02d : %02d", clock/60,clock%60);
					timerLabel.setText(timerStr);
				})
		);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	public void win() {
		
		Label winPrompt = new Label("You Won!");
		returnMainMenu.setOnAction(event->{
			
		});
		
		//Styles for win condition HERE !!!!!
		String buttonStyles = "-fx-background-color: #424549; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 30px;";
		
		returnMainMenu.setStyle(buttonStyles);
		
		String labelStyles = "-fx-text-fill: white; " + "-fx-font-size: 100px;";
		
		winPrompt.setAlignment(Pos.CENTER);
		winPrompt.setStyle(labelStyles);
		outsideContainer.getChildren().clear();
		outsideContainer.getChildren().addAll(winPrompt,returnMainMenu);
		this.setCenter(outsideContainer);
	}
	


}
