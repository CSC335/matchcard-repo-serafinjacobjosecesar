package model;

import java.util.ArrayList;

public class CardCollections {
	private ArrayList<Card> Cards;
	
	public CardCollections() {
		Cards = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		Cards.add(card);
	}
	
}
