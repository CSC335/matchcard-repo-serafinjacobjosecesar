package model;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class NumberMarquee extends Application {
	private Label[] options = new Label[3];
	private TextField betField;
	private Button spinButton;
	private int currentIndex = 0;
	private Label bottomLabel = new Label("");
	private Account curAcc = new Account("1", "5");

	@Override
	public void start(Stage primaryStage) {
		curAcc.updateCurrScore();
		curAcc.updateCurrScore();
		VBox root = new VBox(20);
		root.setAlignment(Pos.CENTER);

		setupOptions(root);
		setupControls(root);

		Scene scene = new Scene(root, 400, 200);

		primaryStage.setTitle("Money Marquee");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void setupOptions(VBox root) {
		HBox optionBox = new HBox(10);
		optionBox.setAlignment(Pos.CENTER);
		String[] labels = { "Money Lost", "Money Back", "Double Money" };
		for (int i = 0; i < labels.length; i++) {
			options[i] = new Label(labels[i]);
			options[i].setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
			optionBox.getChildren().add(options[i]);
		}
		root.getChildren().add(optionBox);
	}

	private void setupControls(VBox root) {
		betField = new TextField();
		betField.setPromptText("Enter your bet");
		spinButton = new Button("Spin");
		spinButton.setOnAction(event -> spin());
		HBox controlBox = new HBox(10, betField, spinButton);
		controlBox.setAlignment(Pos.CENTER);
		bottomLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: black;");
		root.getChildren().addAll(controlBox, bottomLabel);
	}

	private void spin() {
		String betStr = betField.getText();
		int bet = 0;

		try {
			bet = Integer.parseInt(betStr);
			if (bet <= 0) {
				bottomLabel.setText("Please enter a bet greater than 0.");
				return; // Stop the method if the bet is not valid
			}
		} catch (NumberFormatException e) {
			bottomLabel.setText("Invalid bet amount. Please enter a valid number.");
			return;
		}

		if (curAcc.getPoints() < bet) {
			bottomLabel.setText("Insufficient funds. Please choose a lower bet amount.");
			return;
		}

		// subtract bet amount from account points

		final int startSpeed = 100;
		Random random = new Random();
		final int endSpeed = random.nextInt(30) + 330;
		int increments = random.nextInt(5) + 5;
		Timeline timeline = new Timeline();
		int totalDuration = 0;

		for (int i = startSpeed; i <= endSpeed; i += increments) {
			KeyFrame keyFrame = new KeyFrame(Duration.millis(totalDuration), e -> highlightNext());
			timeline.getKeyFrames().add(keyFrame);
			totalDuration += i;
		}

		timeline.setOnFinished(e -> finalizeSelection());
		timeline.play();
	}

	private void finalizeSelection() {
		int finalIndex = (currentIndex - 1 + options.length) % options.length;
		Label finalOption = options[finalIndex];
		int bet = Integer.parseInt(betField.getText());
		if (finalOption.getText() == "Money Lost") {
			bottomLabel.setText("Sorry! Better luck next time.");
		} else if (finalOption.getText() == "Double Money") {
			bottomLabel.setText("You win! " + (bet * 2) + " coins have been added to your account.");
			int i = 0;
			while (i < bet * 2) {
				curAcc.updateCurrScore();
				i++;
			}
		} else if (finalOption.getText() == "Money Back") {
			bottomLabel.setText("Spin again!");
			int i = 0;
			while (i < bet) {
				curAcc.updateCurrScore();
				i++;
			}
		}
		betField.clear();
	}

	private void highlightNext() {
		resetHighlights();
		options[currentIndex].setTextFill(Color.RED);
		currentIndex = (currentIndex + 1) % options.length;
	}

	private void resetHighlights() {
		for (Label option : options) {
			option.setTextFill(Color.BLACK);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}