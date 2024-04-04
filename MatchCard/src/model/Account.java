package model;

public class Account {
	private String userName,password;
	private int longestStreak, currStreak, hiScore, currScore, points;
	private Boolean hadMatch;
	
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
}
