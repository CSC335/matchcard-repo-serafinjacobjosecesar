package model;


public class FoodCollection extends AbstractCardCollection {

	public FoodCollection(int cols) {
		super(cols);
		this.cols= cols;
		setupDeck();
	}

	private void setupDeck() {		
		String type = "Food";
		addCard("Bacon-Eggs", 	type, "baconeggs(front).png",	cols);
		addCard("Burger", 		type, "burger(front).png",		cols);
		addCard("Hotdog", 		type, "hotdog(front).jpg",		cols);	
		addCard("Pasta", 		type, "pasta(front).png",		cols);	
		addCard("Pizza", 		type, "pizza(front).png",		cols);
		addCard("Salad", 		type, "salad(front).jpg",		cols);	
		addCard("Steak", 		type, "steak(front).png",		cols);
		addCard("Sushi", 		type, "sushi(front).png",		cols);	
		addCard("Taco", 		type, "taco(front).png",		cols);	
		addCard("Wings", 		type, "wings(front).jpg",		cols);
		
	}
	@Override
	public AbstractCardCollection getNewDeck(int number,int scale) {
		FoodCollection temp = new FoodCollection(10);
		temp.getDiffDeck(number,scale);
		return temp;
	}
}
