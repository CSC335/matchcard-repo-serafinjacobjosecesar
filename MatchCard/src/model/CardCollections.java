package model;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class CardCollections {
	private ArrayList<Card> Cards;
	
	public CardCollections() {
		Cards = new ArrayList<Card>();
		
	}
	
	public void addCard(Card card) {
		Cards.add(card);
	}
	
	public Card getCard(int index) {
		return Cards.get(index);
	}
	
	public CardCollections getAnimalCollection() {
		CardCollections AnimalsCards = new CardCollections();
		
		Image image1 = new Image("file:chameleon.jpg", false);
		Card chameleon = new Card(image1, "Chameleon", "Animal");
		AnimalsCards.addCard(chameleon);
		
		Image image2 = new Image("file:monkey.jpg", false);
		Card monkey = new Card(image2, "Monkey", "Animal");
		AnimalsCards.addCard(monkey);
		
		Image image3 = new Image("file:pig.jpg", false);
		Card pig = new Card(image3, "Pig", "Animal");
		AnimalsCards.addCard(pig);
		
		Image image4 = new Image("file:Zebra.jpg", false);
		Card zebra = new Card(image4, "Zebra", "Animal");
		AnimalsCards.addCard(zebra);
		
		return AnimalsCards;
	}
	
}
