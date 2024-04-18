package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.image.Image;
/**
 * Class represents a generic collection class for abstract card objects
 */
public abstract class AbstractCardCollection {
	protected int number;
	protected ArrayList<Card> Cards;
	protected ArrayList<Card> UniCards;
	protected int size;
	protected int cols;
	
	/**
	 * constructor for Abstract Card Collection
	 */
	public AbstractCardCollection(int num) {
		this.Cards = new ArrayList<Card>();
		this.UniCards = new ArrayList<Card>();
		this.size = num;
		this.cols = num;
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
		UniCards.add(card);
		size++;
		Cards.add(card.getPair());
	}
	
	public Card getCard(int index) {
		return Cards.get(index);
	}
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	public ArrayList<Card> remove(int number) {
		//
		
		if (number > size) {
			return Cards;
		}
		
		Collections.shuffle(UniCards);	
		ArrayList<Card> newDeck = new ArrayList<Card>();
		size = number;
		// Adds the 'number' of cards to a new deck of shuffled cards
		while (number > 0) {
			Card temp = UniCards.get(number-1);
			newDeck.add(temp);
			newDeck.add(temp.getPair());
			number--;
		}
		Cards = newDeck;
		return Cards;
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
