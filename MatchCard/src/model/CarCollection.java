package model;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

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
		
//		Image image1 = new Image(fileName+"car(front).jpg",300,300,false,false);
//		Card car = new Card(image1, "Car", "Car",scale);
//		addCard(car);
//		
//		Image image2 = new Image(fileName+"limo(front).png",300,300,false,false);
//		Card limo = new Card(image2, "Limo", "Car", scale);
//		addCard(limo);
//		
//		Image image3 = new Image(fileName+"police(front).png",300,300,false,false);
//		Card police = new Card(image3, "Police", "Car",scale);
//		addCard(police);
//
//		Image image4 = new Image(fileName+"truck(front).png",300,300,false,false);
//		Card truck = new Card(image4, "Truck", "Car",scale);
//		addCard(truck);
	}

	@Override
	public AbstractCardCollection getNewDeck(int number) {
		CarCollection temp = new CarCollection(number);
		temp.getDiffDeck(number);
		return temp;
	}
	
	


}
