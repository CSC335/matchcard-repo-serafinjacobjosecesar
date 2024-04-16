package model;

import java.io.Serializable;

/**
 * Account class that represents the user's account
 * tracks longest streak, current streak, high score, current score
 * and points.
 */

public class Account implements Serializable {
	private String userName,password;
	private int longestStreak, currStreak, hiScore, currScore, points;
	private Boolean hadMatch;
	
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
	}
	
	/**
	 * setPassword sets the account password
	 * @param newPassword String that represents the password 
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
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
			return;
		}
		points += (points + currStreak);
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
	 * getHighScore returns the account high score
	 * @return int represents account high score
	 */
	public int getHighScore() {
		return hiScore;
	}
	
	

}
