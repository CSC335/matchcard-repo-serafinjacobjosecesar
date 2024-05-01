package model;

/**
 * RandomCollection class extends AbstractCardCollection : collection class
 * that represents the abstract random collection
 */
public class RandomCollection extends AbstractCardCollection {
	public RandomCollection(int cols, String back) {
		super(cols, back);
		setupDeck();
	}

	/**
	 * setupDeck : initialized the random card deck
	 */
	private void setupDeck() {
		addCard("Chameleon", "Animals", "chameleon(front).jpg", cols);
		addCard("Monkey", "Animals", "monkey(front).jpg", cols);
		addCard("Pig", "Animals", "pig(front).jpg", cols);
		addCard("Zebra", "Animals", "zebra(front).jpg", cols);
		addCard("Kangaroo", "Animals", "kangaroo(front).png", cols);
		addCard("Cat", "Animals", "cat(front).png", cols);
		addCard("Sheep", "Animals", "sheep(front).png", cols);
		addCard("Chicken", "Animals", "chicken(front).png", cols);
		addCard("Bull", "Animals", "bull(front).png", cols);
		addCard("Cow", "Animals", "cow(front).png", cols);
		String type = "Food";
		addCard("Bacon-Eggs", type, "baconeggs(front).png", cols);
		addCard("Burger", type, "burger(front).png", cols);
		addCard("Hotdog", type, "hotdog(front).jpg", cols);
		addCard("Pasta", type, "pasta(front).png", cols);
		addCard("Pizza", type, "pizza(front).png", cols);
		addCard("Salad", type, "salad(front).jpg", cols);
		addCard("Steak", type, "steak(front).png", cols);
		addCard("Sushi", type, "sushi(front).png", cols);
		addCard("Taco", type, "taco_new(front).jpg", cols);
		addCard("Wings", type, "wings(front).jpg", cols);
		addCard("Car", "Cars", "car(front).jpg", cols);
		addCard("Limo", "Cars", "limo(front).png", cols);
		addCard("Police", "Cars", "police(front).png", cols);
		addCard("Truck", "Cars", "truck(front).png", cols);
		addCard("Ambulance", "Cars", "ambulance(front).png", cols);
		addCard("Bus", "Cars", "bus(front).png", cols);
		addCard("Firetruck", "Cars", "firetruck(front).png", cols);
		addCard("Jeep", "Cars", "jeep(front).png", cols);
		addCard("Tractor", "Cars", "tractor(front).png", cols);
		addCard("Van", "Cars", "van(front).png", cols);
	}

	/**
	 * getNewDeck : returns a new deck
	 * 
	 * @param number : int that represents the number of cards in deck
	 * @param scale  : int that represents the scale for image
	 * @return temp : AbstractCardCollection a new deck
	 */
	@Override
	public AbstractCardCollection getNewDeck(int number, int scale) {
		RandomCollection temp = new RandomCollection(10, super.getBackOfCard());
		temp.getDiffDeck(number, scale);
		return temp;
	}
}
