package model;

import java.util.ArrayList;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class GameBoard{
	private Button[][] boardButtons;
	private Card[][] gameBoardArr;
	public ArrayList<int[]> toCompare = new ArrayList<>();
	private int numOfPairs;
	private int rows;
	private int cols;
	private Account playerInformation;
	public int buttonScale=0;
	
	public Button returnMainMenu = new Button("Main Menu");
	public Button newGame = new Button("New Game");
	
	public GameBoard(Account player,AbstractCardCollection uniqueCards, int cols, int rows, int mode) {
		this.cols = cols;
		this.rows = rows;
		
		playerInformation = player;
		numOfPairs = uniqueCards.getSize();
		int scale = 850/cols;
		scale = (scale+170)/2;
		uniqueCards = uniqueCards.getNewDeck(rows*cols, scale);
		boardButtons = new Button[rows][cols];
		gameBoardArr = new Card[rows][cols];
		
		//will make the gameBoardArr 
		intializeArrCards(uniqueCards,cols,rows);
		intializeButtons(cols,rows);
	}
	
	public int getNumberOfPairs() {
		return numOfPairs;
	}
	
	public Button[][] getButtonArray(){
		return boardButtons;
	}
	
	public Card[][] getCardArr(){
		return gameBoardArr;
	}
	
	private void intializeButtons(int cols, int rows) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Card currCard = gameBoardArr[i][j];
				boardButtons[i][j] = new Button();
				ImageView backView = new ImageView(currCard.getBackOfCard());
				boardButtons[i][j].setGraphic(backView);
				boardButtons[i][j].setId(currCard.getName());
				int scale = 850/cols;
				scale = (scale+170)/2;
				this.buttonScale = scale;
				boardButtons[i][j].setMinSize(scale, scale);
				boardButtons[i][j].setPadding(new Insets(0));
				boardButtons[i][j].setFocusTraversable(false);
			}
		}		
	}
	
	//2D representation of GameBoard Gui
	/**
	* returns an instance of a 2D array of Card objects
	*
	* @return A 2D array of Card object of size ixj
	*/
	public Card[][] getGameBoardArr(){
		return gameBoardArr;
	}
	
	/*
	 * flip - Flips both cards from the toCompare array to the back side of the card
	 */
	public void flip() {
		int [] stCardCords = toCompare.get(0);
		int [] ndCardCords = toCompare.get(1);

		Card card1 = gameBoardArr[stCardCords[0]][stCardCords[1]];
		card1.flip();
		ImageView backView1 = new ImageView(card1.getBackOfCard());
		boardButtons[stCardCords[0]][stCardCords[1]].setGraphic(backView1);
		
		Card card2 = gameBoardArr[ndCardCords[0]][ndCardCords[1]];
		card2.flip();
		ImageView backView2 = new ImageView(card2.getBackOfCard());
		boardButtons[ndCardCords[0]][ndCardCords[1]].setGraphic(backView2);
		
	/*
	 * will be used for future iteratbuttonsLayoution for the game flipping down both
	 * cards if they are not a match 	
	 */
	}
	
	public boolean check() {
	/*
	 * once toCompare has 2 cards to compare it checks their name and 
	 * if thay are a match then the cards disappear from the board
	 * else both cards get flipped and enabled for flipping 	
	 */
		if(toCompare.size()==2) {
			wait(false);
			int [] stCardCords = toCompare.get(0);
			int [] ndCardCords = toCompare.get(1);
			String s1 = gameBoardArr[stCardCords[0]][stCardCords[1]].getName();
			String s2 = gameBoardArr[ndCardCords[0]][ndCardCords[1]].getName();
			if(gameBoardArr[stCardCords[0]][stCardCords[1]].sameComparison(gameBoardArr[ndCardCords[0]][ndCardCords[1]])) {
				boardButtons[stCardCords[0]][stCardCords[1]].setDisable(true);
				boardButtons[stCardCords[0]][stCardCords[1]].setVisible(false);
				
				boardButtons[ndCardCords[0]][ndCardCords[1]].setDisable(true);
				boardButtons[ndCardCords[0]][ndCardCords[1]].setVisible(false);
				numOfPairs--;
				
				playerInformation.setMatch(true);
				playerInformation.updateCurrScore();
			}
			else {
				flip();
				playerInformation.setMatch(false);
				playerInformation.updateCurrScore();
			}
			toCompare.clear();
		}
		
		if(numOfPairs==0) {
			playerInformation.setMatch(false);
			playerInformation.win();
			return true;
		}
		wait(false);
		return false;
	}
	/**
	 * 
	 * @param uniqueCards
	 * @param cols
	 * @param rows
	 */
	private void intializeArrCards(AbstractCardCollection uniqueCards,int cols,int rows) {
		int currCard = 0;
		for(int i=0; i < rows ; i++) {
			for(int j=0; j < cols; j++) {
				gameBoardArr[i][j] = uniqueCards.getCard(currCard);
				currCard++;
			}
		}
	}
	/**
	 * wait public void - delays button inputs 
	 * @param val
	 */
	public void wait(boolean val) {
		Task<Void> task = new Task<Void>() {
	        @Override
	        protected Void call() throws Exception {
	            // Simulate delay
	            Thread.sleep(10000); // Adjust the delay time as needed
	            return null;
	        }
	    };

	    // Event handler for when the task is finished
	    task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
	        @Override
	        public void handle(WorkerStateEvent event) {
	            // Re-enable buttons after the delay
	            for (int i = 0; i < rows; i++) {
	                for (int j = 0; j < cols; j++) {
	                    Button tempButton = boardButtons[i][j];
	                    if (tempButton.isVisible()) {
	                        tempButton.setOpacity(1.0); // Reset opacity
	                        tempButton.setDisable(val);
	                    }
	                }
	            }
	        }
	    });

	    // Start the task
	    new Thread(task).start();
	}
}