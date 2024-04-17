package model;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class CarCollection extends AbstractCardCollection {

	public CarCollection() {
		super();
		setupDeck();
	}

	private void setupDeck() {
		String userDir = System.getProperty("user.dir");
		
		String fileName = "";
		
		if (userDir.substring(0, 1).equals("/")) {
		    fileName = "file:" + userDir + "/Card Images/Cars/";
		} 
		else {
			userDir = userDir.replace('\\', '/');
			fileName = "file:/" + userDir + "/Card Images/Cars/";
		}

		Image image1 = new Image(fileName+"car(front).jpg",300,300,false,false);
		Card car = new Card(image1, "Car", "Car");
		addCard(car);
		
		Image image2 = new Image(fileName+"limo(front).png",300,300,false,false);
		Card limo = new Card(image2, "Limo", "Car");
		addCard(limo);
		
		Image image3 = new Image(fileName+"police(front).png",300,300,false,false);
		Card police = new Card(image3, "Police", "Car");
		addCard(police);

		Image image4 = new Image(fileName+"truck(front).png",300,300,false,false);
		Card truck = new Card(image4, "Truck", "Car");
		addCard(truck);
	}

	@Override
	public AbstractCardCollection getSomeCards(int number) {
		CarCollection temp = new CarCollection();
		temp.remove(number);
		return temp;
	}
	
	


}
