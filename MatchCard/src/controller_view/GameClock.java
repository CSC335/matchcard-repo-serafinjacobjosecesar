package controller_view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameClock extends Application{
	private int clock = 0;
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		Label timerLabel = new Label("0");
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(1), event -> {
					clock++;
					String timerStr = String.format("%02d : %02d", clock/60,clock%60);
					timerLabel.setText(timerStr);
				})
		);
		timeline.setCycleCount(Timeline.INDEFINITE);
		
		primaryStage.setOnShown(event -> timeline.play());
		
		VBox root = new VBox(timerLabel);
		Scene scene = new Scene(root, 200, 100);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
