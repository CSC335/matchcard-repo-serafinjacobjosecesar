package model;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class AnimalCollection extends AbstractCardCollection{
	
	
	public AnimalCollection() {
		super();
		setupDeck();
	}
	
	private void setupDeck() {
			CardCollections AnimalsCards = new CardCollections();
			
			String userDir = System.getProperty("user.dir");
			String fileName = "";
			
			if (userDir.substring(0, 1).equals("/")) {
			    fileName = "file:" + userDir + "/Card Images/";
			} 
			else {
				userDir = userDir.replace('\\', '/');
				fileName = "file:/" + userDir + "/Card Images/";
			}

			Image image1 = new Image(fileName+"chameleon.jpg",300,300,false,false);
			Card chameleon1 = new Card(image1, "Chameleon", "Animal");
			AnimalsCards.addCard(chameleon1);
			AnimalsCards.addCard(chameleon1.getPair());
			
			Image image2 = new Image(fileName+"monkey.jpg",300,300,false,false);
			Card monkey1 = new Card(image2, "Monkey", "Animal");
			AnimalsCards.addCard(monkey1);
			AnimalsCards.addCard(monkey1.getPair());
			
			Image image3 = new Image(fileName+"pig.jpg",300,300,false,false);
			Card pig1 = new Card(image3, "Pig", "Animal");
			AnimalsCards.addCard(pig1);
			AnimalsCards.addCard(pig1.getPair());

			Image image4 = new Image("file:Zebra.jpg", false);
			Card zebra = new Card(image4, "Zebra", "Animal");
			AnimalsCards.addCard(zebra);
			AnimalsCards.addCard(zebra.getPair());
	}	

}
