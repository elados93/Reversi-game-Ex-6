package Reversi;


import java.io.IOException;

import GeneralDef.Owner;
import ReversiCode.Board;

import ReversiCode.GameState;
import ReversiCode.GuiManager;
import ReversiCode.GuiPlayer;
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
			SettingsController settingsController = new SettingsController();
			settingsController.parseSettingsFile();
			int row = settingsController.getRowBox();
			int col = settingsController.getColBox();
			String player1Color = settingsController.getPlayer1Color();
			String player2Color = settingsController.getPlayer2Color();
						
			ClickListener clickListener = new ClickListener();
			Board board = new Board(row, col);
			GuiPlayer p1 = new GuiPlayer(player1Color, Owner.PLAYER_1, clickListener);
			GuiPlayer p2 = new GuiPlayer(player2Color, Owner.PLAYER_2, clickListener);
			GameState gameState = new GameState(board);
			ReversiDefaultRules rules = new ReversiDefaultRules();
			GuiManager guiManager = new GuiManager(gameState, p1, p2, rules, 1);

			ReversiBoardController reversiBoardController = new ReversiBoardController(board, clickListener);

			Stage stage = (Stage) Start_Game.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reversi/GameScene.fxml"));
			
			loader.setController(reversiBoardController);
			
			HBox root = (HBox) loader.load();
			
			
			Scene reversiGameScene = new Scene(root, 640, 500);
						
			stage.setScene(reversiGameScene);
			stage.show();
			
			guiManager.run();
			
			
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
	
	
	private void handleGame(ClickListener clickListener) {
		
	}
}