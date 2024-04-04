package model;

import java.util.ArrayList;

public class AccountCollections {
	private ArrayList<Account> accounts;
	
	public AccountCollections() {
		accounts = new ArrayList<Account>();
	}
	
	public void addAccount(Account newAccount) {
		accounts.add(newAccount);
	}
	
	public Boolean contains(Account accountToCheck) {
		return accounts.contains(accountToCheck);
	}
	
}
