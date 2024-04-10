package model;

import javafx.scene.image.Image;

public class Card {
	
	// private Image image = new Image("file:doge.jpeg", false);
	private Image picture;
	private String Name;
	private String type;
	private boolean flipped;
	private String boardPos;
	
	public Card(Image img, String name, String type) {
		this.Name = name;
		this.picture = img;
		this.type = type;
		
		//this will help for the event handler to know when they are flipped or not
		this.flipped = false;
	}
	
	public void setId(String id) {
		this.boardPos = id;
	}
	
	public Image getImage() {
		return picture;
	}
	
	public String getName() {
		return this.Name;
	}
	
	
	public void flip(){
		this.flipped = !this.flipped;
	}
	
	public boolean isItFlipped() {
		return this.flipped;
	}
	
	
	public boolean sameComparison(Card card1) {
		
		return this.Name.equals(card1.Name);
	}
	
	public boolean typeComparsion(Card card1) {
		return (this.type.compareTo(card1.Name) ==0) ? true: false;
	}
//		
//	}
	
	
	
	

}
