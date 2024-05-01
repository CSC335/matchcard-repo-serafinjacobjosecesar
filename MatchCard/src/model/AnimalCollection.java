package model;

import java.util.ArrayList;

/**
 * AnimalCollections class extends AbstractCardCollection : collection class that represents the abstract animal collection
 */
public class AnimalCollection extends AbstractCardCollection {
	public AnimalCollection(int cols, String back) {
		super(cols,back);
		setupDeck();
	}
	
	/**
	 * setupDeck : initialized the animal card deck
	 */
	private void setupDeck() {	
		addCard("Chameleon","Animals", "chameleon(front).jpg",	cols );
		addCard("Monkey",	"Animals", "monkey(front).jpg", 	cols );
		addCard("Pig",		"Animals", "pig(front).jpg",		cols );
		addCard("Zebra",	"Animals", "zebra(front).jpg", 		cols );
		addCard("Kangaroo",	"Animals", "kangaroo(front).png", 	cols );
		addCard("Cat",		"Animals", "cat(front).png", 		cols );
		addCard("Sheep",	"Animals", "sheep(front).png", 		cols );
		addCard("Chicken",	"Animals", "chicken(front).png", 	cols );
		addCard("Bull",		"Animals", "bull(front).png", 		cols );
		addCard("Cow",		"Animals", "cow(front).png", 		cols );
	}
	
	/**
	 *getNewDeck : returns a new deck 
	 *@param number : int that represents the number of cards in deck
	 *@param scale : int that represents the scale for image
	 *@return temp : AbstractCardCollection a new deck  
	 */
	@Override
	public AbstractCardCollection getNewDeck(int number,int scale) {
		AnimalCollection temp = new AnimalCollection(10, super.getBackOfCard());
		temp.getDiffDeck(number,scale);
		return temp;
	}
}
