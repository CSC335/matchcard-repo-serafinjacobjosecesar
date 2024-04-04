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
	
	public abstract Image getImage();
	
	public abstract boolean sameComparison(Card card1);
		
//		return (this.Name.compareTo(Name)==0  && typeComparsion(card1)) ? true: false;
	
	
	public abstract boolean typeComparsion(Card card1);
//		return (this.type.compareTo(card1.Name) ==0) ? true: false;
//	}
	
	
	
	

}
