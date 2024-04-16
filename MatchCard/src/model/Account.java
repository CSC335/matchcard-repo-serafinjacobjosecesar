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
	 * setPassword
	 * @param newPassword
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
	
	public Boolean verifyPassword(String passAttempt) {
		if (this.password == passAttempt) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setMatch(Boolean match) {
		hadMatch = match;
	}
	
	public void updateCurrScore() {
		if (hadMatch) {
			currScore++;
			updatePoints();
			updateCurrStreak();
		} else {
			updateCurrStreak();
		}
		
	}
	
	private void updateCurrStreak() {
		if (hadMatch) {
			currStreak++;
			updateLongest();
		} else {
			resetStreak();
		}
	}
	
	private void updateLongest() {
		if (currStreak > longestStreak) {
			longestStreak = currStreak;
		}
	}
		
	private void resetStreak() {
		currStreak = 0;
	}
	
	private void updatePoints() {
		if (currStreak < 2) {
			points++;
			return;
		}
		points += (points + currStreak);
	}

	public String getUsername() {
		return userName;
	}

	public String getPassWord() {
		return password;
	}
	
	public int getLongestStreak() {
		return longestStreak;
	}
	
	public int getHighScore() {
		return hiScore;
	}
	
	

}
