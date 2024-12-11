package model;

import java.util.ArrayList;
import java.util.Collections;
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
	private String file;
	private String backOfCard;

	/**
	 * AbstractCardCollection constructor for Card Collection class initializes
	 * Collections - an array list of Card objects
	 * 
	 * @param num         : int dictates size of images(front/back of card)
	 * @param newCardBack : String that represents the new card back
	 */
	public AbstractCardCollection(int num, String newCardBack) {
		this.Cards = new ArrayList<Card>();
		this.UniCards = new ArrayList<Card>();
		this.size = num;
		this.cols = num;
		this.backOfCard = newCardBack;

	}

	/**
	 * getBackOfCard : getter for back of card
	 * 
	 * @return backOfCard : String that represents the back of the card
	 */
	public String getBackOfCard() {
		return backOfCard;
	}

	/**
	 * getArrayList Returns the ArrayList with Card objects
	 * 
	 * @return ArrayList of Card objects
	 */
	public ArrayList<Card> getArrayList() {
		return Cards;
	}

	/**
	 * setColumns : setter for columns
	 * 
	 * @param col : int Col that represents the number of colums
	 */
	public void setColumns(int col) {
		cols = col;
	}

	/**
	 * getSize returns the size of the deck
	 * 
	 * @return int size of deck
	 */
	public int getSize() {
		return size;
	}

	/**
	 * addCard adds a Card object to the deck
	 * 
	 * @param card Card object that will get added to the deck
	 */
	public void addCard(Card card) {
		Cards.add(card);
		UniCards.add(card);
		size++;
		Cards.add(card.getPair());
	}

	/**
	 * addCard Creates a Card object from the given parameters and adds it to the
	 * Deck
	 * 
	 * @param name  String reprensents the name
	 * @param type  String reprensents the type
	 * @param file  String reprensents the name of the file
	 * @param scale int reprensents the number of columns that the cards are going
	 *              to be placed in
	 */
	public void addCard(String name, String type, String file, int scale) {
		Image image = getImage(file, type);
		Card newCard = new Card(image, name, type, scale, backOfCard);
		newCard.setPath(this.file);
		Cards.add(newCard);
		UniCards.add(newCard);
		size++;

		Cards.add(newCard.getPair());
	}

	/**
	 * getImage gets the image given the name of the file and folder
	 * 
	 * @param file   String represents the name of the file
	 * @param folder String represents the name of the folder
	 * @return Image actual image of the subject
	 */
	private Image getImage(String file, String folder) {
		String userDir = System.getProperty("user.dir");
		String fileName = "";

		if (userDir.substring(0, 1).equals("/")) {
			fileName = "file:" + userDir + "/Card Images/" + folder + "/";
		} else {
			userDir = userDir.replace('\\', '/');
			fileName = "file:/" + userDir + "/Card Images/" + folder + "/";
		}
		this.file = fileName + file;
		Image image1 = new Image(fileName + file, 100, 100, false, false);
		return image1;
	}

	/**
	 * getCard gets a Card object from the deck at the given index
	 * 
	 * @param index int represents the index of a Card in the deck
	 * @return
	 */
	public Card getCard(int index) {
		return Cards.get(index);
	}

	/**
	 * getDiffDeck creates a new deck with 'num' number of Cards and shuffles them,
	 * the original deck is still available
	 * 
	 * @param number int represents the wanted amount of Cards
	 * @return ArrayList a new Deck with the wanted number of cards
	 */
	public ArrayList<Card> getDiffDeck(int number, int scale) {
		number = number / 2;
		if (number < size) {
			/*
			 * Creates a new deck and adds num number of cards into the new deck Then sets
			 * the new deck as Cards
			 */
			Collections.shuffle(UniCards);
			ArrayList<Card> newDeck = new ArrayList<Card>();
			size = number;
			while (number > 0) {
				Card temp = UniCards.get(number - 1);
				rescaleCard(scale, temp);
				newDeck.add(temp);
				newDeck.add(temp.getPair());
				number--;
			}
			Cards = newDeck;
		}
		shuffle();
		return Cards;
	}

	/**
	 * rescaleCard : rescales a card object by a given scale
	 * 
	 * @param scale : int that represents the scaler
	 * @param temp  : Card object that is the card to scale
	 */
	private void rescaleCard(int scale, Card temp) {
		Image scaledImage = new Image(temp.getPath(), scale, scale, false, false);
		Image scaledBack = temp.getFileName(temp.getNameOfBackOfCard(), scale);
		temp.setScale(scale);
		temp.setImage(scaledImage, scaledBack);
	}

	/**
	 * shuffle Shuffles the deck
	 * 
	 * @return ArrayList a shuffled version of the Deck
	 */
	public ArrayList<Card> shuffle() {
		Collections.shuffle(Cards);
		return Cards;
	}

	/**
	 * printCards prints out the cards in the Deck
	 */
	public void printCards() {
		for (Card card : Cards) {
			System.out.println("Card:" + card.getName() + ": " + card.getType());
		}
	}

	/**
	 * getNewDeck returns a new deck with 'number' of cards wanted
	 * 
	 * @param number int represents the size of the new deck
	 * @return AbstractCardCollection the new deck of size 'number'
	 */
	public abstract AbstractCardCollection getNewDeck(int number, int scale);

}
