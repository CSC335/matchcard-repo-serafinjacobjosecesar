package model;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class AnimalCollection extends AbstractCardCollection {

	public AnimalCollection(int cols) {
		super(cols);
		this.cols= cols;
		setupDeck();
	}

	private void setupDeck() {		
		addCard("Chameleon","Animals", "chameleon(front).jpg",	cols );
		addCard("Monkey",	"Animals", "monkey(front).jpg", 	cols );
		addCard("Pig",		"Animals", "pig(front).jpg",		cols );
		addCard("Zebra",	"Animals", "zebra(front).jpg", 		cols );
		
//
//		Image image1 = new Image(fileName+"chameleon(front).jpg",scale,scale,false,false);
//		
//		Card chameleon1 = new Card(image1, "Chameleon", "Animal",scale);
//		addCard(chameleon1);
//		
//		Image image2 = new Image(fileName+"monkey(front).jpg",scale,scale,false,false);
//		Card monkey1 = new Card(image2, "Monkey", "Animal",scale);
//		addCard(monkey1);
//		
//		Image image3 = new Image(fileName+"pig(front).jpg",scale,scale,false,false);
//		Card pig1 = new Card(image3, "Pig", "Animal",scale);
//		addCard(pig1);
//
//		Image image4 = new Image(fileName+"zebra(front).jpg",scale,scale,false,false);
//		Card zebra = new Card(image4, "Zebra", "Animal",scale);
//		addCard(zebra);
	}

	@Override
	public AbstractCardCollection getNewDeck(int number) {
		AnimalCollection temp = new AnimalCollection(number);
		temp.getDiffDeck(number);
		return temp;
	}
	
	


}
