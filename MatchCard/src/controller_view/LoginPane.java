package controller_view;

import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Account;
import model.AccountCollections;

public class LoginPane extends BorderPane {
	Account currentAcc = null;
	GridPane gridPane = new GridPane();
	int numOfSongs = 0;
	ArrayList<Account> accounts = new ArrayList<Account>();
	AccountCollections accountCollections = new AccountCollections();
	
	Label accountNameLabel = new Label("Acccount Name");
	Label passWordLabel = new Label("Password");
	String str = String.format("Login First");
	Label strLabel = new Label(str);

	TextField nameField = new TextField("");
	PasswordField passwordField = new PasswordField();

	Button selSong1 = new Button("Select Song 1");
	Button selSong2 = new Button("Select Song 2");
	Button loginB = new Button("Login");
	Button logOutB = new Button("Log out");
	Button createAcc = new Button("Create new Account");

	public LoginPane() {
		gridPane.setHgap(10);
		gridPane.setVgap(5);

		gridPane.add(accountNameLabel, 2, 3);
		gridPane.add(passWordLabel, 2, 4);

		gridPane.add(nameField, 3, 3);
		gridPane.add(passwordField, 3, 4);

		gridPane.add(loginB, 4, 3);
		gridPane.add(strLabel, 3, 1);
		gridPane.add(logOutB, 4, 4);
		gridPane.add(createAcc, 3, 5);
		setButtonHandler();
		this.setCenter(gridPane);
	}

	public void setButtonHandler() {

		loginB.setOnAction(event -> {
			// Clear Names after log in
			boolean flag = false;
			String user = nameField.getText();
			String passW = passwordField.getText();
			accounts = accountCollections.returnAccounts();			
			for (Account acc : accounts) {
				if (acc.getUsername().equals(user) && acc.getPassWord().equals(passW)) {
					currentAcc = acc;
					flag = true;
					nameField.setText("");
					passwordField.setText("");
					strLabel.setText("Succesfully Logged in");
				}
			}

			if (!flag) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setContentText("No account found");
				Optional<ButtonType> result = alert.showAndWait();
				strLabel.setText("No account found");
				nameField.setText("");
				passwordField.setText("");
			}
		});

		logOutB.setOnAction(event -> {
			currentAcc = null;
			nameField.setText("");
			passwordField.setText("");
		});

		createAcc.setOnAction(event -> {
			String name = nameField.getText();
			String passWord = passwordField.getText();

			if (!checkAcc(name)) {
				Account newAcc = new Account(name, passWord);
				currentAcc = newAcc;
				accountCollections.addAccount(newAcc);
				strLabel.setText("User Created");
				nameField.setText("");
				passwordField.setText("");
			}
			else {
				currentAcc = null;
				nameField.setText("");
				passwordField.setText("");
				strLabel.setText("Invalid");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setContentText("Account with username " + name + " already exists. Please login.");
				Optional<ButtonType> result = alert.showAndWait();
			}
		});
	}

	private boolean checkAcc(String name) {
		for (Account acc : accounts) {
			if (acc.getUsername().equals(name)) {
				return true;
			}
		}
		return false;
	}

}
