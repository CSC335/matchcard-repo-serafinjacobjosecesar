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
	private int scale;
	
	/**
	 * AbstractCardCollection constructor for Card Collection class
	 * initializes Collections - an array list of Card objects
	 * @param int Integer dictates size of images(front/back of card)
	 */
	public AbstractCardCollection(int num) {
		this.Cards = new ArrayList<Card>();
		this.UniCards = new ArrayList<Card>();
		this.size = num;
		this.cols = num;
	}

	/**
	 * getArrayList Returns the ArrayList with Card objects
	 * @return ArrayList of Card objects
	 */
	public ArrayList<Card> getArrayList(){
		return Cards;
	}
	
	/**
	 * getSize returns the size of the deck
	 * @return int size of deck
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * addCard adds a Card object to the deck
	 * @param card Card object that will get added to the deck
	 */
	public void addCard(Card card) {
		Cards.add(card);
		UniCards.add(card);
		size++;
		Cards.add(card.getPair());
	}
	
	/**
	 * addCard Creates a Card object from the given parameters and adds it to the Deck
	 * @param name String reprensents the name
	 * @param type String reprensents the type
	 * @param file String reprensents the name of the file
	 * @param num int reprensents the number of columns that the cards are going to be placed in
	 */
	public void addCard(String name, String type, String file, int num) {
		scale = 850/num;
		Image image = getFileName(file,type);
		Card newCard = new Card(image,name, type, scale);
		Cards.add(newCard);
		UniCards.add(newCard);
		size++;
		Cards.add(newCard.getPair());
	}
	
	private Image getFileName(String file, String type) {
		String userDir = System.getProperty("user.dir");
		String fileName = "";
		
		if (userDir.substring(0, 1).equals("/")) {
		    fileName = "file:" + userDir + "/Card Images/"+type+"/";
		} 
		else {
			userDir = userDir.replace('\\', '/');
			fileName = "file:/" + userDir + "/Card Images/"+type+"/";
		}
		Image image1 = new Image(fileName+file,scale,scale,false,false);
		return image1;
	}	
	
	/**
	 * getCard gets a Card object from the deck at the given index 
	 * @param index int represents the index of a Card in the deck
	 * @return
	 */
	public Card getCard(int index) {
		return Cards.get(index);
	}
	
	/**
	 * getDiffDeck creates a new deck with 'num' number of Cards and shuffles them,
	 *  the original deck is still available
	 * @param number int represents the wanted amount of Cards
	 * @return ArrayList<Card> a new Deck with the wanted number of cards
	 */
	public ArrayList<Card> getDiffDeck(int num) {	
		ArrayList<Card> newDeck = Cards;
		if (number < size) {
			createNewDeck(num, newDeck);
			Cards = newDeck;
		}
		shuffle();
		return Cards;
	}

	private void createNewDeck(int num, ArrayList<Card> newDeck) {
		Collections.shuffle(UniCards);	
		size = num;
		while (num > 0) {
			Card temp = UniCards.get(num-1);
			newDeck.add(temp);
			newDeck.add(temp.getPair());
			num--;
		}
	}
		
	/**
	 * shuffle Shuffles the deck
	 * @return ArrayList<Card> a shuffled version of the Deck
	 */
	public ArrayList<Card> shuffle(){
		Collections.shuffle(Cards);
		return Cards;
	}
	
	/**
	 * printCards prints out the cards in the Deck
	 */
	public void printCards() {
		for (Card card : Cards) {
			System.out.println("Card:"+card.getName()+": "+card.getType());
		}
	}
	/**
	 * getNewDeck returns a new deck with 'number' of cards wanted
	 * @param number int represents the size of the new deck
	 * @return AbstractCardCollection the new deck of size 'number'
	 */
	public abstract AbstractCardCollection getNewDeck(int number);

}
