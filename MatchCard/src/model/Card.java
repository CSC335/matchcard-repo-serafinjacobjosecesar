package model;

import javafx.scene.image.Image;

public abstract class Card {
	
	// private Image image = new Image("file:doge.jpeg", false);
	private Image picture;
	private String Name;
	private String type;
	
	
	public Card(Image img, String name, String type) {
		this.Name = name;
		this.picture = img;
		this.type = type;
	}
	
	public Image getImage() {
		return picture;
	}
	
	
	public boolean sameComparison(Card card1) {
		//String result = (x > y) ? "x is greater" : "y is greater";
		return (this.Name.compareTo(Name)==0  && typeComparsion(card1)) ? true: false;
	}
	
	public boolean typeComparsion(Card card1) {
		return (this.type.compareTo(card1.Name) ==0) ? true: false;
	}
	
	
	
	

}
