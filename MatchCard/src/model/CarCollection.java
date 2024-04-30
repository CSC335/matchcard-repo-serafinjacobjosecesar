package model;

import java.util.ArrayList;


public class CarCollection extends AbstractCardCollection {

	/**
	 * CarCollection acts as the collection class for the cards for Car Collection
	 * @param num int is the scale for the images, front and back images of the Cards
	 */
	public CarCollection(int cols) {
		super(cols);
		this.cols= cols;
		setupDeck();
	}
	
	/**
	 * setupDeck sets up the deck with pre-determined cards 
	 */
	private void setupDeck() {
		//String name, String type, String file, int num
//		addCard("Car",		"Cars", 	"car(front).jpg", 		cols );
//		addCard("Limo",		"Cars", 	"limo(front).png", 		cols );
//		addCard("Police",	"Cars", 	"police(front).png",	cols );
//		addCard("Truck",	"Cars", 	"truck(front).png", 	cols );
//		addCard("Ambulance","Cars", 	"ambulance(front).png", cols );
//		addCard("Bus",		"Cars", 	"bus(front).png", 		cols );
//		addCard("Firetruck","Cars", 	"firetruck(front).png", cols );
//		addCard("Jeep",		"Cars", 	"jeep(front).png", 		cols );
//		addCard("Tractor",	"Cars", 	"tractor(front).png", 	cols );
//		addCard("Van",		"Cars", 	"van(front).png", 		cols );
	}

	@Override
	public AbstractCardCollection getNewDeck(int number, int scale) {
		CarCollection temp = new CarCollection(10);
		temp.getDiffDeck(number,scale);
		return temp;
	}
	
	


}
