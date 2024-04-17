package model;

import java.util.ArrayList;

import javafx.event.ActionEvent;

public class Game {
	private GameBoard gameboard;
	private Card[][] gameboardArrRep;
	//private Card[][] gameBoard;
	private AbstractCardCollection deck;
	private int numberOfPairs;
	
	public Game(Account player) {
		//gameBoard = new Card[3][2];
		
		/*TODO
		 * set for 2x3
		 *  is set for 6 cards (3 pairs of animals)
		 */
//		deck = new CardCollections();
//		deck = deck.getAnimalCollection();
//		deck.shuffle();
		
		deck = new AnimalCollection();
		
		deck.shuffle();
		
//		deck = deck.getSomeCards(3);
//		deck.getSomeCards(3);
		gameboard = new GameBoard(deck,3,2);
		
		/*
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				gameBoard[i][j] = deck.getCard(i);
			}
			deck.shuffle();
		}
		*/
		numberOfPairs = (gameboard.getGameBoardArr().length)/2;
		
		gameboard.newGame.addEventFilter(ActionEvent.ACTION, event2 -> {
			deck.shuffle();
			gameboard = new GameBoard(deck,3,2);
		});
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
	
	/*
	public Card getCard(int row, int col) {
		return gameBoard[row][col];
	}
	*/
	
	/*
	public void addToCompare(Card currCard) {
		toCompare.add(currCard);
	
	}
	
	public boolean compareCards() {
		Card cardOne = toCompare.remove(0);
		Card cardTwo = toCompare.remove(1);
		return cardOne.sameComparison(cardTwo);
	}
	
	public ArrayList<Card> getToCompare(){
		return toCompare;
	}
	*/
	public void updatePairs() {
		numberOfPairs--;
	}
	
	// TODO provide javadoc for func
	public boolean isWin() {
		return numberOfPairs == 0;
	}
}
