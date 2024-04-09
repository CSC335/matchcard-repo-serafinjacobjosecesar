package model;

import java.util.ArrayList;

public class Game {
	private Card[][] gameBoard;
	private CardCollections deck;
	private ArrayList<Card> toCompare;
	private int compareLength = 2;
	
	public Game() {
		gameBoard = new Card[3][2];
		deck = new CardCollections();
		deck.shuffle();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				gameBoard[i][j] = deck.getCard(i);
			}
			deck.shuffle();
		}
	}

	public Card[][] getBoard(){
		return gameBoard;
	}
	
	public Card getCard(int row, int col) {
		return gameBoard[row][col];
	}
	
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
	
}
