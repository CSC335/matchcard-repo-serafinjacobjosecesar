package controller_view;

import java.time.LocalDate;
import java.util.Optional;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private LoginPane loginPane;
	private BorderPane everything;
	private Button testBut;

	@Override
	public void start(Stage primaryStage) throws Exception {
		everything = new BorderPane();
		loginPane = new LoginPane();
		LayoutGUI();

		Scene loginScene = new Scene(loginPane, 400, 500);
		Scene mainScene = new Scene(everything,1000,1000);
		primaryStage.setScene(loginScene);
		primaryStage.setResizable(false);
		primaryStage.show();

		
		loginPane.loginB.addEventFilter(ActionEvent.ACTION, event -> {
			if (loginPane.currentAcc != null) {
				new Thread(() -> {
				    try {
				        Thread.sleep(1000);
		                Platform.runLater(() -> primaryStage.setScene(mainScene));
				    } catch (InterruptedException e) {
				        e.printStackTrace();
				    }
				}).start();
			}
		});
	}

	private void LayoutGUI() {
		// login pane
		loginPane.setPadding(new Insets(10));
		
		testBut = new Button("Test Button");
		everything.setCenter(testBut);

	}

	public void setLoginHandler(Stage primaryStage) {

	}
	
	

}
