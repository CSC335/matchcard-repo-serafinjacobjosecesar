package tests;

import model.CardCollections;
import model.GameBoard;

public class testboard {
	public static void main(String[] args){
		
	int i = 0;
	i = i+i;
	
	CardCollections deck = new CardCollections();
	deck = deck.getAnimalCollection();
	deck.shuffle();
	GameBoard gameboard = new GameBoard(deck,3,2);
	}
}
