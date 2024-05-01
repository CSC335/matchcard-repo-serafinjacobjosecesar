package model;

public class Game {
	private GameBoard gameboard;
	private AbstractCardCollection deck;
	private int numberOfPairs;

	/**
	 * public Game that handles creating a game object
	 * 
	 * @param player: Account object
	 * @param mode:   int that indicates the gamemode
	 */

	public Game(Account player, int mode) {
		int col = 3;
		int row = 2;

		deck = new AnimalCollection(col, player.getBack());

		int scale = 950 / row;
		deck = deck.getNewDeck(col, scale);
		deck.shuffle();
		gameboard = new GameBoard(player, deck, col, row, mode);

		numberOfPairs = (row * col) / 2;

	}

	/**
	 * Getter for 2D array of Card objects that represent GameBoard object
	 *
	 * @return 2D array of Card objects that represent the GameBoard object
	 */
	public Card[][] getBoard() {
		return gameboard.getGameBoardArr();
	}

	/**
	 * getter for GameBoard object
	 *
	 * @return A GameBoard object
	 */
	public GameBoard getGameBoardObj() {
		return gameboard;
	}

	/**
	 * lowers the amount of pairs on the board
	 */
	public void updatePairs() {
		numberOfPairs--;
	}

	/**
	 * 
	 * @return numberOfPairs: boolean that return true when pairs is 0 signifying a
	 *         win
	 */
	public boolean isWin() {
		return numberOfPairs == 0;
	}
}
