package model;

import java.util.ArrayList;

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
	}
	@Override
	public AbstractCardCollection getNewDeck(int number) {
		AnimalCollection temp = new AnimalCollection(number);
		temp.getDiffDeck(number);
		return temp;
	}
	
	


}
