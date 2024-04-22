package model;

import java.util.ArrayList;


public class CarCollection extends AbstractCardCollection {

	/**
	 * CarCollection acts as the collection class for the cards for Car Collection
	 * @param num int is the scale for the images, front and back images of the Cards
	 */
	public CarCollection(int num) {
		super(num);
		setupDeck();
	}
	
	/**
	 * setupDeck sets up the deck with pre-determined cards 
	 */
	private void setupDeck() {
		//String name, String type, String file, int num
		addCard("Car",	"Car", 	"car(front).jpg", 	cols );
		addCard("Limo",	"Car", 	"limo(front).png", 	cols );
		addCard("Police","Car", "police(front).png",cols );
		addCard("Truck","Car", 	"truck(front).png", cols );
	}

	@Override
	public AbstractCardCollection getNewDeck(int number) {
		CarCollection temp = new CarCollection(number);
		temp.getDiffDeck(number);
		return temp;
	}
	
	


}
