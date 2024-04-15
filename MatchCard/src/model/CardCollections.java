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
	
	public ArrayList<Card> getArrayList(){
		return this.Cards;
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
		
		/*
		 
		 
		if (userDir.substring(0, 1).equals("/")) {
		    fileName = "file:" + userDir + "/songfiles/" + currSong.getPathfile();
		} else {
		        userDir = userDir.replace('\', '/');
		    fileName = "file:/" + userDir + "/songfiles/" + currSong.getPathfile();}
		userDir = System.getProperty("user.dir")
		double '\" not ""
		replace "/songfiles/" with "/Card Images/"
		
		
		*/
		String userDir = System.getProperty("user.dir");
		String fileName = "";
		
		if (userDir.substring(0, 1).equals("/")) {
		    fileName = "file:" + userDir + "/Card Images/";
		} 
		else {
			userDir = userDir.replace('\\', '/');
			fileName = "file:/" + userDir + "/Card Images/";
		}

		Image image1 = new Image(fileName+"chameleon.jpg",300,300,false,false);
		Card chameleon1 = new Card(image1, "Chameleon", "Animal");
		AnimalsCards.addCard(chameleon1);
		AnimalsCards.addCard(chameleon1.getPair());
		
		Image image2 = new Image(fileName+"monkey.jpg",300,300,false,false);
		Card monkey1 = new Card(image2, "Monkey", "Animal");
		AnimalsCards.addCard(monkey1);
		AnimalsCards.addCard(monkey1.getPair());
		
		Image image3 = new Image(fileName+"pig.jpg",300,300,false,false);
		Card pig1 = new Card(image3, "Pig", "Animal");
		AnimalsCards.addCard(pig1);
		AnimalsCards.addCard(pig1.getPair());

		
		//TODO temperarily disabled for testing 2x3
		/*
		Image image4 = new Image("file:Zebra.jpg", false);
		Card zebra = new Card(image4, "Zebra", "Animal");
		AnimalsCards.addCard(zebra);
		AnimalsCards.addCard(zebra);
		*/
		return AnimalsCards;
	}
	
	public CardCollections getCollection(int num) {
		if (num > Cards.size()) {
			System.out.println("ISSUE: GET_COLLECTION");
			return null;
		}
		return (CardCollections) AnimalsCards.getArrayList().subList(0, num);
			
	}
	
	
	
	
	
	public ArrayList<Card> shuffle(){
		Collections.shuffle(Cards);	
		return Cards;
	}
	
}
