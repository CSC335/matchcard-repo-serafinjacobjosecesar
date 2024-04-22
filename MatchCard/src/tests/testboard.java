package tests;

import model.AbstractCardCollection;
import model.Account;
import model.AnimalCollection;
import model.CardCollections;
import model.GameBoard;

public class testboard {
	public static void main(String[] args){
		
	int i = 0;
	i = i+i;
	
//	CardCollections deck = new CardCollections();
//	deck = deck.getAnimalCollection();
//	deck.shuffle();
	
	AbstractCardCollection deck = new AnimalCollection(3);
	deck.shuffle();
	Account p = new Account("1","1");
	GameBoard gameboard = new GameBoard(p, deck,3,2);
	}
}
