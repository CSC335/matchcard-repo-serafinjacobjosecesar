package model;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.util.Random;

/**
 * WinScreen is a demo win screen used to format active win screen
 */
public class WinScreen extends Application {
	private static final int WIDTH = 950;
	private static final int HEIGHT = 800;
	private final Random rand = new Random();

	private Label winPrompt = new Label("You Won!");
	private Button returnMainMenu = new Button("Main Menu");
	private Button newGame = new Button("New Game");

	/**
	 * start : starts the application
	 * 
	 * @param stage : Stage current stage
	 */
	@Override
	public void start(Stage stage) {
		Group root = new Group();

		VBox buttonsLayout = new VBox(10); // Vertical box for buttons
		buttonsLayout.getChildren().addAll(winPrompt, returnMainMenu, newGame);
		buttonsLayout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root, WIDTH, HEIGHT);

		// Style and position
		buttonsLayout.setTranslateX(WIDTH / 2 - 200); // Adjust X as needed
		buttonsLayout.setTranslateY(HEIGHT / 2 - 200); // Adjust Y as needed
		String buttonStyles = "-fx-background-color: #424549; " + "-fx-text-fill: white; " + "-fx-font-size: 30px;";
		returnMainMenu.setStyle(buttonStyles);
		newGame.setStyle(buttonStyles);

		String labelStyles = "-fx-text-fill: black; " + "-fx-font-size: 100px;";
		winPrompt.setStyle(labelStyles);

		int confetti = 225;
		for (int i = 0; i < confetti; i++) {
			Rectangle rect = createConfettiPiece();
			root.getChildren().add(rect);
		}
		root.getChildren().add(buttonsLayout);
		setListeners(stage);

		stage.setScene(scene);
		stage.show();

		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e -> moveConfetti(root)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	/**
	 * createConfettiPieces : creates the "confetti" pieces
	 * 
	 * @return rect : Rectangle object to be used as confetti
	 */
	private Rectangle createConfettiPiece() {
		Rectangle rect = new Rectangle(10, 20, Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
		rect.setX(rand.nextDouble() * WIDTH);
		rect.setY(rand.nextDouble() * HEIGHT);
		rect.setRotate(rand.nextDouble() * 360);
		return rect;
	}

	/**
	 * moveConfetti : updates the position of the confetti
	 * 
	 * @param root : Group groups all confetti pieces
	 */
	private void moveConfetti(Group root) {
		for (Object obj : root.getChildren()) {
			if (obj instanceof Rectangle) {
				Rectangle rect = (Rectangle) obj;
				rect.setY(rect.getY() + 3);
				rect.setRotate(rect.getRotate() + 3);

				if (rect.getY() > HEIGHT) {
					rect.setY(-10);
					rect.setX(rand.nextDouble() * WIDTH);
				}
			}
		}
	}

	/**
	 * setListeners : creates listeners for action events
	 * 
	 * @param stage : Stage current stage
	 */
	public void setListeners(Stage stage) {
		returnMainMenu.setOnAction(event -> {
			stage.setScene(null);
		});
		newGame.setOnAction(event -> {

		});

	}

	/**
	 * main : acts as the main
	 * 
	 * @param args : String[] array of current args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
