package model;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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


public class GameBoard extends BorderPane{
	
	private Label statusOfGame = new Label("Click to make a move");
	private HBox moveContainer = new HBox(statusOfGame);
	private VBox outsideContainer = new VBox();
	private Button[][] boardButtons;
	private Card[][] gameBoardArr;
	private ArrayList<int[]> toCompare = new ArrayList<>();;
	
	
	public GameBoard(CardCollections uniqueCards, int cols, int rows) {
		// TODO construct takes in the deck of uniqueCards needed and the grid that
		//need to be made (cols X rows)
		
		boardButtons = new Button[rows][cols];
		gameBoardArr = new Card[rows][cols];
		
		//will make the gameBoardArr 
		intializeArrCards(uniqueCards,cols,rows);
		
		// will make the button board for Gameboard Gui
		intializePanel(cols,rows);
	}
	
	//2D representation of GameBoard Gui
	public Card[][] getGameBoardArr(){
		return gameBoardArr;
	}
	
	public void flip() {
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
			}
			else {
				gameBoardArr[stCardCords[0]][stCardCords[1]].flip();
				gameBoardArr[ndCardCords[0]][ndCardCords[1]].flip();
				System.out.println("they are not a match");
			}
			toCompare.clear();
		}
	}

	private void intializeArrCards(CardCollections uniqueCards,int cols,int rows) {
		int currCard = 0;
		for(int i=0; i < rows ; i++) {
			for(int j=0; j < cols; j++) {
				gameBoardArr[i][j] = uniqueCards.getCard(currCard);
				currCard++;
			}
		}
	}

	private void intializePanel(int cols, int rows) {
		// TODO creates the gui buttons for the card game 
		
		GridPane buttonPane = new GridPane();
		Font font = new Font("Courier New", 32);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				
				//Allows for row and col to be available in event
				final int row = i;
				final int col = j;
				
				
				final Card currCard = gameBoardArr[i][j];
				
				boardButtons[i][j] = new Button();
				
				ImageView imgView = new ImageView(currCard.getImage());
				boardButtons[i][j].setGraphic(imgView);
				
				
				boardButtons[i][j].setId(currCard.getName());
				buttonPane.add(boardButtons[i][j], j, i);
				boardButtons[i][j].setMinSize(300, 300);
				
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
						currCard.flip();
						System.out.println("FRONT");
						toCompare.add(cords);
						check();
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
		outsideContainer.getChildren().addAll(buttonPane, moveContainer);
		this.setCenter(outsideContainer);

	}
	
	
}