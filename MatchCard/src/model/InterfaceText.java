package model;

import javafx.scene.text.*;
import javafx.scene.*;
import javafx.scene.transform.*;
import javafx.scene.paint.Color;

public class InterfaceText {

	private Group text = new Group();
	private static final double START_X = 850;
	private static final double START_Y = 400;

	public InterfaceText() {
		drawTitle();
	}

	public Group getText() {
		return text;
	}

	public void setPlayerCredit(int c) {
		Text playerCreditText = new Text("Credit: £" + Integer.toString(c));
		text.getChildren().clear();
		placeText(playerCreditText, START_X, START_Y + 200, 40, 0);
		text.getChildren().add(playerCreditText);
		drawTitle();
	}

	private void drawTitle() {
		Text textNode = new Text("Rob's Roulette");
		placeText(textNode, START_X, START_Y - 300, 60, 0);
		text.getChildren().add(textNode);
	}

	private void placeText(Text t, double x, double y, int f, double r) {
		t.setFill(Color.YELLOW);
		t.setFont(Font.font("FreeSerif", FontWeight.NORMAL, FontPosture.ITALIC, f));
		t.relocate(x, y);
		t.getTransforms().add(new Rotate(r));
	}

}