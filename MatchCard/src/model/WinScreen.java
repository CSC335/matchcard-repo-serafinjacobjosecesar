package model;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WinScreen extends Application {

    private static final int WIDTH = 950;
    private static final int HEIGHT = 800;
    private final Random rand = new Random();



	@Override
	public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        int confetti = 200;
        for (int i = 0; i < confetti; i++) {
            Rectangle rect = createConfettiPiece();
            root.getChildren().add(rect);
        }

        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e -> moveConfetti(root)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();		
	}

	
    private Rectangle createConfettiPiece() {
        Rectangle rect = new Rectangle(10, 20, Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        rect.setX(rand.nextDouble() * WIDTH);
        rect.setY(rand.nextDouble() * HEIGHT);
        rect.setRotate(rand.nextDouble() * 360);
        return rect;
    }
    
    private void moveConfetti(Group root) {
        for (Object obj : root.getChildren()) {
            Rectangle rect = (Rectangle) obj;
            rect.setY(rect.getY() + 3);
            rect.setRotate(rect.getRotate() + 3);

            if (rect.getY() > HEIGHT) {
                rect.setY(0);
                rect.setX(rand.nextDouble() * WIDTH);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
