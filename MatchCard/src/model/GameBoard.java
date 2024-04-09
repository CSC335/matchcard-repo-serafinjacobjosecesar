package model;

import java.util.ArrayList;
import java.util.Collections;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import views_controllers.DrawingView.MousePressed;

public class GameBoard {
	
	ArrayList<Card> arryRepOfBoard;
	
	private VBox outsideContainer = new VBox();
	private GraphicsContext gc;
	Canvas canvas = new Canvas(200, 200);
	private HBox canvasContainer = new HBox(canvas);

	private void intializePanel() {
		// TODO Auto-generated method stub
		canvasContainer.setAlignment(Pos.CENTER);

		outsideContainer.getChildren().addAll(canvas);

		outsideContainer.setAlignment(Pos.CENTER);

		gc = canvas.getGraphicsContext2D();

		gc.strokeRect(0, 0, 200, 200);

		// vert lines
		gc.strokeLine(66.66, 0, 66.66, 200);
		gc.strokeLine(132.32, 0, 132.32, 200);
		// hor lines
		gc.strokeLine(0, 66.66, 200, 66.66);
		gc.strokeLine(0, 132.32, 200, 132.33);
		registerHandlers();
	}

	private void registerHandlers() {
		canvas.setOnMousePressed(new MousePressed());

	}
	
	public GameBoard(CardCollections uniqueCards) {
		
		
		
	}

	//will be used for the game in order to know how the canvas is going to be arranged
	public ArrayList<Card> getCardsOnBOard() {
		return null;
	}
	
	private class MousePressed implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {

			double x = event.getSceneX() - 20;

			double y = event.getSceneY() - 100;

			int row = -1, col = -1;

			// rows
			if (x >= 0 && x <= 66.66) {
				col = 0;
			}
			if (x > 66.66 && x <= 132.32) {
				col = 1;
			}
			if (x > 132.32 && x <= 200) {
				col = 2;
			}

			// col
			if (y >= 0 && y <= 66.66) {
				row = 0;
			}
			if (y > 66.66 && y <= 132.32) {
				row = 1;
			} 
			if (y > 132.32 && y <= 200) {
				row = 2;
			}
		}
	}
}
