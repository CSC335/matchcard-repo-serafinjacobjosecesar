package model;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.image.Image;

public abstract class AbstractCardCollection {

	private ArrayList<Card> Cards;
	private int size;
	
	public AbstractCardCollection() {
	}
	
	public ArrayList<Card> getArrayList(){
		return Cards;
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
	
	public CardCollections getCollection(int num) {
		if (num > Cards.size()) {
			System.out.println("ISSUE: GET_COLLECTION");
			return null;
		}
		return (CardCollections) Cards.subList(0, num);	
	}
	
	public ArrayList<Card> shuffle(){
		Collections.shuffle(Cards);	
		return Cards;
	}

}
