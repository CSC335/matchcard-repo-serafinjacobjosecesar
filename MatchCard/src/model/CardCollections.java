package model;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.image.Image;

public class CardCollections {
	private ArrayList<Card> Cards;
	private int size;
	
	public CardCollections() {
		Cards = new ArrayList<Card>();
	}
	
	public int getSize() {
		return size;
	}
	
	public void addCard(Card card) {
		Cards.add(card);
		size++;
	}
	
	public Card getCard(int index) {
		return Cards.get(index);
	}
	
	public CardCollections getAnimalCollection() {
		CardCollections AnimalsCards = new CardCollections();
		
		//double cards so they could have pairs inside the deck
		
		Image image1 = new Image("file:chameleon.jpg",100,100,false,false);
		Card chameleon = new Card(image1, "Chameleon", "Animal");
		AnimalsCards.addCard(chameleon);
		AnimalsCards.addCard(chameleon);
		
		Image image2 = new Image("file:monkey.jpg",100,100,false, false);
		Card monkey = new Card(image2, "Monkey", "Animal");
		AnimalsCards.addCard(monkey);
		AnimalsCards.addCard(monkey);
		
		Image image3 = new Image("file:pig.jpg",100,100,false, false);
		Card pig = new Card(image3, "Pig", "Animal");
		AnimalsCards.addCard(pig);
		AnimalsCards.addCard(pig);
		
		//TODO temperarily disabled for testing 2x3
		/*
		Image image4 = new Image("file:Zebra.jpg", false);
		Card zebra = new Card(image4, "Zebra", "Animal");
		AnimalsCards.addCard(zebra);
		AnimalsCards.addCard(zebra);
		*/
		return AnimalsCards;
	}
	
	
	
	public ArrayList<Card> shuffle(){
		Collections.shuffle(Cards);	
		return Cards;
	}
	
}
