package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
	public void setAbstractCardCollection(ArrayList<Card> deck) {
		Cards = deck;
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
		Cards.add(card.getPair());
	}
	
	public Card getCard(int index) {
		return Cards.get(index);
	}
	
	public void remove() {
		Card temp = getCard(0);
		int i=0;
		for (Card card : Cards) {
			if (card.sameComparison(temp)) {
				Cards.remove(i);
			}
			i++;
		}
		Cards.remove(0);
		Cards.remove(0);
	}
		
	public ArrayList<Card> shuffle(){
		Collections.shuffle(Cards);	
		return Cards;
	}
	
	public void printCards() {
		for (Card card : Cards) {
			System.out.println("Card:"+card.getName()+": "+card.getType());
		}
	}
	
	public abstract AbstractCardCollection getSomeCards(int number);

}
