package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Account class that represents the user's account
 * tracks longest streak, current streak, high score, current score
 * and points.
 */
public class Account implements Serializable {
	private String userName,password;
	private int longestStreak, currStreak, hiScore, currScore, points;
	private int bestTimeGamemode2=0;
	private int bestTimeGamemode1=0;
	private Boolean hadMatch;
	private String currCardBack = null;
	private String currBackground = "-fx-background-color: #7e61ab;";
	public ArrayList<String> inventory = new ArrayList<>();
	
	/**
	 * Account constructor 
	 * @param userName String that represents the account name
	 * @param password String that represents the account password
	 */
	public Account(String userName, String password) {
		this.userName = userName;
	    this.password = password;
		longestStreak = 0;
		currStreak = 0;
		hiScore = 0;
		currScore = 0;
		points = 0;
		hadMatch = false;
		currCardBack = "matchCard(backClose)";
	}
	
	/**
	 * setPassword sets the account password
	 * @param newPassword String that represents the password 
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
	
	/**
	 * setCurrBackground : setter for currBackground
	 * @param newBack : String that represents the new background to set
	 */
	public void setCurrBackground(String newBack) {
		this.currBackground = newBack;
	}
	
	/**
	 * setCurrCardBack : setter for currCardBack
	 * @param newBack : String that represents the new card back 
	 */
	public void setCurrCardBack(String newBack) {
		this.currCardBack = newBack;
	}
	
	/**
	 * verifyPassword verify that the supplied password matches the account
	 * recorded password.
	 * @param passAttempt String that represents the user's attempted password
	 * @return Boolean true if password was valid false otherwise.
	 */
	public Boolean verifyPassword(String passAttempt) {
		if (this.password == passAttempt) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * setMatch updates hadMatch field to track matches and non matches
	 * @param match Boolean value that will be assigned to hadMatch
	 */
	public void setMatch(Boolean match) {
		hadMatch = match;
	}
	
	/**
	 * updateCurrScore uses current Boolean value assigned to hadMatch
	 * to determine if current score, points, and streak need to be updated
	 * or if current streak needs to be reset
	 */
	public void updateCurrScore() {
		if (hadMatch) {
			currScore++;
			updatePoints();
			updateCurrStreak();
		} else {
			updateCurrStreak();
		}
		
	}
	
	/*
	 * updateCurrStreak updates the current streak value
	 */
	private void updateCurrStreak() {
		if (hadMatch) {
			currStreak++;
			updateLongest();
		} else {
			resetStreak();
		}
	}
	
	/*
	 * updateCurrStreak updates the current streak value
	 */
	public void win() {
		hiScore++;
	}
	
	/**
	 * update longest updates longest to current if current > longest
	 */
	private void updateLongest() {
		if (currStreak > longestStreak) {
			longestStreak = currStreak;
		}
	}
	
	/**
	 * resetStreak sets current streak to 0
	 */
	private void resetStreak() {
		currStreak = 0;
	}
	
	/**
	 * updatePoints uses current streak to modify point values per match
	 */
	private void updatePoints() {
		if (currStreak < 2) {
			points++;
		}
		points = points + currStreak;
	}
	
	/**
	 * withdrawPoints : updates points to reflect a withdraw of points.
	 * @param withdraw : int that represents the amount of points to withdraw
	 */
	public void withdrawPoints(int withdraw) {
		points -= withdraw;
	}
	
	/**
	 * getPoints : getter for points 
	 * @return points : int that represents the current amount of points
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * getUsername return userName
	 * @return userName String represents userName
	 */
	public String getUsername() {
		return userName;
	}
	
	/**
	 * getPassWord returns password
	 * @return password String represents password
	 */
	public String getPassWord() {
		return password;
	}
	
	/**
	 * getLongestStrak returns longestStreak
	 * @return longestStreak int represents longest streak achieved by account
	 */
	public int getLongestStreak() {
		return longestStreak;
	}
	
	/**
	 * getCurrCardBack : getter for currCardBack
	 * @return currCardBack : String that represents the current card back
	 */
	public String getCurrCardBack() {
		return currCardBack;
	}
	
	/**
	 * getCurrBackground : gettter for current background
	 * @return currBackground : String that represents the current background
	 */
	public String getCurrBackground() {
		return currBackground;
	}
	
	/**
	 * getHighScore returns the account high score
	 * @return int represents account high score
	 */
	public int getHighScore() {
		return hiScore;
	}
	
	/**
	 * setGamemode2Hiscore : update game mode 2 best time
	 * @param time : int the time to update best time to
	 */
	public void setGamemode2Hiscore(int time) {
		if(time<bestTimeGamemode2) {
			bestTimeGamemode2 = time;
		}
		if (bestTimeGamemode2==0) {
			bestTimeGamemode2 = time;
		}
	}
	
	/**
	 * setGamemode1Hiscore : update game mode 1 best time
	 * @param time
	 */
	public void setGamemode1Hiscore(int time) {
		if(time<bestTimeGamemode1) {
			bestTimeGamemode1 = time;
		}
		if (bestTimeGamemode1==0) {
			bestTimeGamemode1 = time;
		}
	}
	
	/**
	 * getG2Time : getter for game mode 2 best time
	 * @return bestTimeGamemode2
	 */
	public int getG2Time() {
		return bestTimeGamemode2;
	}
	
	/**
	 * getG1Time : getter for game mode 1 best time
	 * @return bestTimeGamemode1 : int that represents game mode 1 best time
	 */
	public int getG1Time() {
		return bestTimeGamemode1;
	}
	
	/**
	 * getBack : getter for back
	 * @return String that represents the back
	 */
	public String getBack() {
		// TODO Auto-generated method stub
		return null;
	}

}
