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

				final int row = i;
				final int col = j;
				
				
				Card currCard = gameBoardArr[i][j];
				
				boardButtons[i][j] = new Button(currCard.getName());
				
				ImageView imgView = new ImageView(currCard.getImage());
				boardButtons[i][j].setGraphic(imgView);
				
				
				boardButtons[i][j].setId("button" + i + "x" + j +" - "+ currCard.getName());
				buttonPane.add(boardButtons[i][j], j, i);
				boardButtons[i][j].setMinSize(300, 300);
				
				//Initializes all the events for the button cards 
				boardButtons[i][j].setOnAction((event)->{ 
					
					int boardY = row;
					int boardX = col;
					
					if(!currCard.isItFlipped()) {
						currCard.flip();
						System.out.println("FRONT");
					}
					else {
						currCard.flip();
						System.out.println("BACK");
					}
					
					//for debug purposes 
					
					System.out.println("this is the Pos: " + boardY + " x " + boardX);
					String id = boardButtons[row][col].getId();
					System.out.println("This is the id:"+id + "\n\n");
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