package model;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.image.Image;
/**
 * Class represents a generic collection class for abstract card objects
 */
public abstract class AbstractCardCollection {

	protected ArrayList<Card> Cards;
	protected int size;
	
	/**
	 * constructor for Abstract Card Collection
	 */
	public AbstractCardCollection() {
		this.Cards = new ArrayList<Card>();
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
		
	public ArrayList<Card> shuffle(){
		Collections.shuffle(Cards);	
		return Cards;
	}

}
