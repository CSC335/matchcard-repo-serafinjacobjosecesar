package model;

import java.util.ArrayList;

import javafx.event.ActionEvent;

public class Game {
	private GameBoard gameboard;
	private Card[][] gameboardArrRep;
	//private Card[][] gameBoard;
	private AbstractCardCollection deck;
	private int numberOfPairs;
	
	public Game(Account player, int mode) {		
		/*TODO
		 * set for 2x3
		 *  is set for 6 cards (3 pairs of animals)
		 */
		
		int col = 4;
		int row = 4;
		deck = new AnimalCollection(col);
		
		int scale = 950 /row;
		deck = deck.getNewDeck(col);
//		deck.shuffle();
		gameboard = new GameBoard(player,deck,col,row);
		
		numberOfPairs = deck.cols;		
		
	}
	
	/**
	* Getter for 2D array of Card objects that represent GameBoard object
	*
	* @return 2D array of Card objects that represent the GameBoard object
	*/
	public Card[][] getBoard(){
		return gameboard.getGameBoardArr();
	}
	
	
	/**
	* getter for GameBoard object
	*
	* @return A GameBoard object
	*/
	public GameBoard getGameBoardObj() {
		return gameboard;
	}
	
	public void updatePairs() {
		numberOfPairs--;
	}
	
	// TODO provide javadoc for func
	public boolean isWin() {
		return numberOfPairs == 0;
	}
}
