package Reversi;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainMenuController {

	@FXML
	private Button Start_Game;
	@FXML
	private Button Settings;
	@FXML
	private Button Exit;

	@FXML
	protected void startGame() {
		try {
			Stage stage = (Stage) Start_Game.getScene().getWindow();
			HBox root = (HBox) FXMLLoader.load(getClass().getResource("GameScene.fxml"));
			Scene reversiGameScene = new Scene(root, 400, 350);
						
			stage.setScene(reversiGameScene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void exitGame() {
		Stage stage = (Stage) Exit.getScene().getWindow();
		stage.close();
	}
}