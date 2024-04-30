package controller_view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Account;

public class Profile extends BorderPane{
	private Account currAcc;
	public Button profileMainMenu;
	private BorderPane profilePane = new BorderPane();
	public Button changePasswordButton;
	
	public Profile (Account account) {
		currAcc = account;
		configLayout();
		
	}
	
	private void configLayout() {
		final Label passwordChangePrompt = new Label();
		Account currAccount = currAcc;
		Label user = new Label(currAccount.getUsername());
		Label longestStreak = new Label("Longest Streak: " + String.valueOf(currAccount.getLongestStreak()));
		Label highscore = new Label("HighScore: " + String.valueOf(currAccount.getHighScore()));
		profileMainMenu = new Button("Main Menu");
		final TextField newPasswordField =  new TextField();
		changePasswordButton = new Button("Change Password");
		
		Label bestTimeG1 = new Label("Game mode 1 Best Time");
		Label bestTimeG2 = new Label("Game mode 2 Best Time");
		
		Label G1BestTime;
		if(currAccount.getG1Time()==0) {
			G1BestTime = new Label("");
		}
		else {
			int time = currAccount.getG1Time();
			System.out.println(time+" |time?");
			G1BestTime = new Label(String.format("%02d : %02d", time/60,time%60));
		}
		Label G2BestTime;
		if(currAccount.getG2Time()==0) {
			G2BestTime = new Label("");
		}
		else {
			int time = currAccount.getG2Time();
			G2BestTime = new Label(String.format("%02d : %02d", time/60,time%60));
		}
		
		// Styling for stats page HERE !!!!
		newPasswordField.setMaxWidth(100);
		newPasswordField.setMaxHeight(50);
		
		String buttonStyles = "-fx-background-color: #424549; " +
	                "-fx-text-fill: white; " +
	                "-fx-font-size: 30px;";
		
		String labelStyles = "-fx-text-fill: white; " + "-fx-font-size: 30px;";
		
		
		profileMainMenu.setStyle(buttonStyles);
		changePasswordButton.setStyle(buttonStyles);
		
		bestTimeG1.setStyle(labelStyles);
		bestTimeG2.setStyle(labelStyles);
		G1BestTime.setStyle(labelStyles);
		G2BestTime.setStyle(labelStyles);
		user.setStyle(labelStyles);
		longestStreak.setStyle(labelStyles);
		highscore.setStyle(labelStyles);
		
		changePasswordButton.setOnAction(event2->{
			String newPass = newPasswordField.getText();
			
			String InvalidPassPromptStyle = "-fx-text-fill: #900D09; "
					+ "-fx-font-size: 30px;";

			if(newPass.equals("")) {
				passwordChangePrompt.setStyle(InvalidPassPromptStyle);
				passwordChangePrompt.setText("Invalid Password!");
			}
			else if(newPass.equals(currAcc.getPassWord())) {
				passwordChangePrompt.setStyle(InvalidPassPromptStyle);
				passwordChangePrompt.setText("Password Already in Use!");
				newPasswordField.clear();
			}
			else {
				currAcc.setPassword(newPass);
				String passPromptStyle = "-fx-text-fill: white; "
						+ "-fx-font-size: 30px;";
				passwordChangePrompt.setStyle(passPromptStyle);
				passwordChangePrompt.setText("Password Change Succesful!");
				newPasswordField.clear();
			}
		});
		
		profileMainMenu.setOnAction(Event -> {});
		
		VBox statsPane = new VBox();
		statsPane.setAlignment(Pos.CENTER);
		statsPane.setSpacing(20);
		statsPane.getChildren().addAll(passwordChangePrompt,user,bestTimeG1,G1BestTime,bestTimeG2,G2BestTime,longestStreak,highscore,
		profileMainMenu,newPasswordField,changePasswordButton);
		profilePane.setCenter(statsPane);
		this.setCenter(profilePane);
		
		
	}

}
