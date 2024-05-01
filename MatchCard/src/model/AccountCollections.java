package model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * AccountCollections acts as the collection class for accounts
 * uses ArrayList as underlining data structure
 */
public class AccountCollections implements Serializable {
	private ArrayList<Account> accounts;
	
	/**
	 * AccountCollections constructor for collection class
	 * initializes accounts - an array list of account objects
	 */
	public AccountCollections() {
		accounts = new ArrayList<Account>();
	}
	
	/**
	 * addAccount takes an account object and adds it to accounts
	 * @param newAccount an account object to be added to collections
	 */
	public void addAccount(Account newAccount) {
		accounts.add(newAccount);
	}
	
	/**
	 * contains checks to see if the Account taken as argument is contained in collections
	 * @param accountToCheck Account is an account object to verify if it is in accountCollections
	 * @return Boolean true if account is in Accounts otherwise false
	 */
	public Boolean contains(Account accountToCheck) {
		return accounts.contains(accountToCheck);
	}
	
	/**
	 * returnAccouts returns the AccountCollections ArrayList
	 * @return accounts : ArrayList an array list of account objects
	 */
	public ArrayList<Account> returnAccounts() {
		return accounts;
	}
	
	/**
	 * set : sets acconts to the given array list
	 * @param arr : ArrayList of account objects
	 */
	public void set(ArrayList<Account> arr) {
		accounts = arr;
	}
}
