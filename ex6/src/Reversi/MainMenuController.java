package Reversi;

import java.io.IOException;

import ReversiCode.Board;
import ReversiCode.ConsolePrinter;
import ReversiCode.GameManager;
import ReversiCode.GameState;
import ReversiCode.HumanPlayer;
import ReversiCode.ReversiDefaultRules;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
			HBox root = (HBox) FXMLLoader.load(getClass().getResource("/Reversi/GameScene.fxml"));
			Scene reversiGameScene = new Scene(root, 640, 500);
						
			stage.setScene(reversiGameScene);
			stage.show();
							
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	protected void settings() {
		try {
			Stage stage = (Stage) Settings.getScene().getWindow();
			VBox root = (VBox) FXMLLoader.load(getClass().getResource("/Reversi/Settings.fxml"));
			Scene settingsScene = new Scene(root, 640, 500);
						
			stage.setScene(settingsScene);
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