package model;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.image.Image;

public class CardCollections {
	private ArrayList<Card> Cards;
	private CardCollections AnimalsCards;
	private int size;
	
	public CardCollections() {
		Cards = new ArrayList<Card>();
		
	}
	
	public void setCardCollections(ArrayList<Card> Deck) {
		Cards = Deck;
	}
	
	
	/**
	 * getArrayList - Gives back the arraylist
	 * @return ArrayList of Card Objects
	 */
	public ArrayList<Card> getArrayList(){
		return this.Cards;
	}
	
	/**
	 * getSize - Gives back the size of the Cards
	 * @return int, the size of cards
	 */
	public int getSize() {
		return size;
	}
	
	/** addCard - Adds a Card object to the array list
	 * 
	 * @param Card object
	 */
	public void addCard(Card card) {
		Cards.add(card);
		size++;
	}
	
	/** getCard - Gets the Card from the given index
	 * @param index, int
	 * @return Card object
	 */
	public Card getCard(int index) {
		return Cards.get(index);
	}
	
	public void setAllCollections() {
		setAnimalCollection();
	}
	
	/** 
	 * 
	 * @return
	 */
	
	private void setAnimalCollection() {
//		AnimalsCards = new CardCollections();
//		String userDir = System.getProperty("user.dir");
//		
//		String fileName = "";
//		
//		if (userDir.substring(0, 1).equals("/")) {
//		    fileName = "file:" + userDir + "/Card Images/";
//		} 
//		else {
//			userDir = userDir.replace('\\', '/');
//			fileName = "file:/" + userDir + "/Card Images/";
//		}
//
//		Image image1 = new Image(fileName+"chameleon(front).jpg",300,300,false,false);
//		Card chameleon1 = new Card(image1, "Chameleon", "Animal");
//		AnimalsCards.addCard(chameleon1);
//		AnimalsCards.addCard(chameleon1.getPair());
//		
//		Image image2 = new Image(fileName+"monkey(front).jpg",300,300,false,false);
//		Card monkey1 = new Card(image2, "Monkey", "Animal");
//		AnimalsCards.addCard(monkey1);
//		AnimalsCards.addCard(monkey1.getPair());
//		
//		Image image3 = new Image(fileName+"pig(front).jpg",300,300,false,false);
//		Card pig1 = new Card(image3, "Pig", "Animal");
//		AnimalsCards.addCard(pig1);
//		AnimalsCards.addCard(pig1.getPair());
		
		

		
		//TODO temperarily disabled for testing 2x3
		/*
		Image image4 = new Image("file:Zebra.jpg", false);
		Card zebra = new Card(image4, "Zebra", "Animal");
		AnimalsCards.addCard(zebra);
		AnimalsCards.addCard(zebra);
		*/
	}
	
	public CardCollections getAnimalCollection() {		
		return AnimalsCards;
	}
	
	/**
	 * getCollection - Returns a Sublist of the arraylist with num element
	 * @param num - int
	 * @return
	 */
	
	
	
	/** shuffle - Shuffles the cards in the arraylist, then returns it 
	 * 
	 * @return ArrayList of Card Objects
	 */
	public ArrayList<Card> shuffle(){
		Collections.shuffle(Cards);	
		return Cards;
	}
	
}

