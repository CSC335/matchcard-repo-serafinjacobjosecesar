package model;


public class FoodCollection extends AbstractCardCollection {
	/**
	 * FoodCollection instructor
	 * @param cols the number of columns needed
	 * @param back the image of the card back
	 */
	public FoodCollection(int cols, String back) {
		super(cols,back);
		setupDeck();
	}

	/**
	 * setupDeck sets up the deck with pre-chosen cards
	 */
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
		addCard("Taco", 		type, "taco_new(front).jpg",	cols);	
		addCard("Wings", 		type, "wings(front).jpg",		cols);
	}
	@Override
	/**
	 * getNewDeck gets a new deck with the new desired number of cards
	 */
	public AbstractCardCollection getNewDeck(int number,int scale) {
		FoodCollection temp = new FoodCollection(10, super.getBackOfCard());
		temp.getDiffDeck(number,scale);
		return temp;
	}
}
