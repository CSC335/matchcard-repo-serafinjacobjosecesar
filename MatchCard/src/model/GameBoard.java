package model;

import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;


public class GameBoard extends BorderPane{
	
	private Label statusOfGame = new Label("Click to make a move");
	private HBox moveContainer = new HBox(statusOfGame);
	private VBox outsideContainer = new VBox();
	private Button[][] boardButtons;
	private Card[][] gameBoardArr;
	private ArrayList<int[]> toCompare = new ArrayList<>();;
	private int numOfPairs;
	private int rows;
	private int cols;
	private Account playerInformation;
	
	
	
	public Button returnMainMenu = new Button("Main Menu");
	public Button newGame = new Button("New Game");
	
	
	public GameBoard(Account player,AbstractCardCollection uniqueCards, int cols, int rows) {
		this.cols = cols;
		this.rows = rows;
		//construct takes in the deck of uniqueCards needed and the grid that
		//need to be made (cols X rows)
		
//		System.out.println(uniqueCards.getSize()/2);
		// AbstractCardCollection counts only pairs
		// thats why its no longer uniqueCards.getSize()/2
		
		playerInformation = player;
		numOfPairs = uniqueCards.getSize();
		boardButtons = new Button[rows][cols];
		gameBoardArr = new Card[rows][cols];
		
		
		//will make the gameBoardArr 
		intializeArrCards(uniqueCards,cols,rows);
		
		// will make the button board for Gameboard Gui
		intializePanel(cols,rows);
	}
	
	
	//TODO this should be done within game 
//	public void newGameBoard() {
//		numOfPairs = deck.getSize()/2;
//		outsideContainer.getChildren().clear();
//		deck.shuffle();
//		intializeArrCards(deck,colsForNewGame,rowsForNewGame);
//		intializePanel(colsForNewGame,rowsForNewGame);
//	}
	
	//2D representation of GameBoard Gui
	/**
	* returns an instance of a 2D array of Card objects
	*
	* @return A 2D array of Card object of size ixj
	*/
	public Card[][] getGameBoardArr(){
		return gameBoardArr;
	}
	
	/*
	 * flip - Flips both cards from the toCompare array to the back side of the card
	 */
	public void flip() {
		System.out.println("Called");
		int [] stCardCords = toCompare.get(0);
		int [] ndCardCords = toCompare.get(1);

		Card card1 = gameBoardArr[stCardCords[0]][stCardCords[1]];
		card1.flip();
		ImageView backView1 = new ImageView(card1.getBackOfCard());
		boardButtons[stCardCords[0]][stCardCords[1]].setGraphic(backView1);
		
		Card card2 = gameBoardArr[ndCardCords[0]][ndCardCords[1]];
		card2.flip();
		ImageView backView2 = new ImageView(card2.getBackOfCard());
		boardButtons[ndCardCords[0]][ndCardCords[1]].setGraphic(backView2);
		
	/*
	 * will be used for future iteration for the game flipping down both
	 * cards if they are not a match 	
	 */
	}
	
	public void check() {
	/*
	 * once toCompare has 2 cards to compare it checks their name and 
	 * if thay are a match then the cards disappear from the board
	 * else both cards get flipped and enabled for flipping 	
	 */
		if(toCompare.size()==2) {
			int [] stCardCords = toCompare.get(0);
			int [] ndCardCords = toCompare.get(1);
			
			if(gameBoardArr[stCardCords[0]][stCardCords[1]].sameComparison(gameBoardArr[ndCardCords[0]][ndCardCords[1]])) {
				System.out.println("they are a match");
				boardButtons[stCardCords[0]][stCardCords[1]].setDisable(true);
				boardButtons[stCardCords[0]][stCardCords[1]].setVisible(false);
				
				boardButtons[ndCardCords[0]][ndCardCords[1]].setDisable(true);
				boardButtons[ndCardCords[0]][ndCardCords[1]].setVisible(false);
				numOfPairs--;
				
				playerInformation.setMatch(true);
				playerInformation.updateCurrScore();
			}
			else {
				System.out.println("they are not a match");
				flip();
				playerInformation.setMatch(true);
				playerInformation.updateCurrScore();
			}
			toCompare.clear();
		}
		System.out.println(numOfPairs);
		if(numOfPairs==0) {
			win();
		}
		wait(false);
	}

	private void intializeArrCards(AbstractCardCollection uniqueCards,int cols,int rows) {
		int currCard = 0;
		for(int i=0; i < rows ; i++) {
			for(int j=0; j < cols; j++) {
				gameBoardArr[i][j] = uniqueCards.getCard(currCard);
				currCard++;
			}
		}
	}

	private void intializePanel(int cols, int rows) {
		// creates the gui buttons for the card game 
		
		GridPane buttonPane = new GridPane();
		Font font = new Font("Courier New", 32);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				
				//Allows for row and col to be available in event
				final int row = i;
				final int col = j;
				
				
				final Card currCard = gameBoardArr[i][j];
				
				boardButtons[i][j] = new Button();
				ImageView backView = new ImageView(currCard.getBackOfCard());
				ImageView frontView = new ImageView(currCard.getImage());
				boardButtons[i][j].setGraphic(backView);
				//boardButtons[i][j].setPadding(new Insets(10));
				
				boardButtons[i][j].setId(currCard.getName());
				buttonPane.add(boardButtons[i][j], j, i);
				boardButtons[i][j].setMinSize(300, 300);
				boardButtons[i][j].setPadding(new Insets(0));
				//Initializes all the events for the button cards 
				boardButtons[i][j].setOnAction((event)->{ 
					
					int boardY = row;
					int boardX = col;
					int [] cords = {row,col};
					
					//for debug purposes - START {
					System.out.println("this is the Pos: " + boardY + " x " + boardX);
					String id = boardButtons[row][col].getId();
					System.out.println("This is the id:"+id + "\n\n");
					//for debug purposes - END }
					
					// flipped is a boolean in card when false shows BACK face 
					if(!currCard.isItFlipped()) {
						boardButtons[row][col].setGraphic(frontView);
						currCard.flip();
						System.out.println("FRONT");
						toCompare.add(cords);
						
						new Thread(() -> {
						    try {
						    	if (toCompare.size()== 2) {
						    		wait(true);
						    	}
						    	
 						    	// Makes a pause so both cards face up, (shows the frontView)
						        Thread.sleep(250);
						        Platform.runLater(() -> {
				                    check();
				                    
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
		
		//sets the gui in the borderpane parent 
		outsideContainer.setSpacing(10);
		outsideContainer.setAlignment(Pos.CENTER);
		buttonPane.setAlignment(Pos.CENTER);
		statusOfGame.setAlignment(Pos.CENTER);
		moveContainer.setAlignment(Pos.CENTER);
		buttonPane.setHgap(10);
		buttonPane.setVgap(10);
		outsideContainer.getChildren().addAll(buttonPane, moveContainer);
		this.setCenter(outsideContainer);

	}
	
	
	// POSSIBLE CHANGES NEEDED
	/**
	 * stop 
	 * @param val
	 */
	private void wait(boolean val) {
		System.out.println("here");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Button tempButton = boardButtons[i][j];
				if (tempButton.isVisible() == true) {
					tempButton.setOpacity(100);
					tempButton.setDisable(val);
				}
			}
		}
	}
	
	
	private void win() {
		Label winPrompt = new Label("You Won!");
		
		
		//TODO implement !!!!!
		returnMainMenu.setOnAction(event->{
			
		});
		
		//Styles for win condition HERE !!!!!
		String buttonStyles = "-fx-background-color: #424549; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 30px;";
		
		returnMainMenu.setStyle(buttonStyles);
		
		String labelStyles = "-fx-text-fill: white; " + "-fx-font-size: 100px;";
		
		//winPrompt.setAlignment(Pos.CENTER);
		winPrompt.setStyle(labelStyles);
		
		outsideContainer.getChildren().clear();
		outsideContainer.getChildren().addAll(winPrompt,returnMainMenu);
		this.setCenter(outsideContainer);
	}
}