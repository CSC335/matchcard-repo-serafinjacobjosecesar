package model;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class AnimalCollection extends AbstractCardCollection {

	public AnimalCollection() {
		super();
		setupDeck();
	}

	private void setupDeck() {
		String userDir = System.getProperty("user.dir");
		
		String fileName = "";
		
		if (userDir.substring(0, 1).equals("/")) {
		    fileName = "file:" + userDir + "/Card Images/";
		} 
		else {
			userDir = userDir.replace('\\', '/');
			fileName = "file:/" + userDir + "/Card Images/";
		}

		Image image1 = new Image(fileName+"chameleon(front).jpg",300,300,false,false);
		Card chameleon1 = new Card(image1, "Chameleon", "Animal");
		addCard(chameleon1);
		
		Image image2 = new Image(fileName+"monkey(front).jpg",300,300,false,false);
		Card monkey1 = new Card(image2, "Monkey", "Animal");
		addCard(monkey1);
		
		Image image3 = new Image(fileName+"pig(front).jpg",300,300,false,false);
		Card pig1 = new Card(image3, "Pig", "Animal");
		addCard(pig1);

		Image image4 = new Image(fileName+"Zebra.jpeg",300,300,false,false);
		Card zebra = new Card(image4, "Zebra", "Animal");
		addCard(zebra);
	}

	@Override
	public AbstractCardCollection getSomeCards(int number) {
		AnimalCollection temp = new AnimalCollection();
		temp.remove(number);
		return temp;
	}
	
	


}
