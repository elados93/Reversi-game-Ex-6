package Reversi;

import java.io.IOException;

import GeneralDef.Owner;
import ReversiCode.Board;

import ReversiCode.GameState;
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

	/**
	 * this function is connected to the fxml and shows the scene of the game.
	 */
	@FXML
	protected void startGame() {
		try {
			// getting the details of the board and players from the settings
			// file.
			ParseSettingsFile parser = new ParseSettingsFile();

			parser.parseSettingsFile();
			int row = parser.getRowBox();
			int col = parser.getColBox();
			String player1Color = parser.getPlayer1Color();
			String player2Color = parser.getPlayer2Color();
			String firstPlayer = parser.getFirstPlayer();

			// creating the board of the game according to the values of the
			// settings file.
			Board board = new Board(row, col);

			Stage stage = (Stage) Start_Game.getScene().getWindow();

			GameState gameState = new GameState(board);
			GuiPlayer p1 = new GuiPlayer(player1Color, Owner.PLAYER_1);
			GuiPlayer p2 = new GuiPlayer(player2Color, Owner.PLAYER_2);
			ReversiDefaultRules rules = new ReversiDefaultRules();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reversi/GameScene.fxml"));
			GuiPlayer firstP = firstPlayer.compareTo("Player 1") == 0 ? p1 : p2;
			ReversiBoardController reversiBoardController = new ReversiBoardController(board, p1, p2, rules, firstP,
					gameState);

			loader.setController(reversiBoardController);

			HBox root = (HBox) loader.load();

			Scene reversiGameScene = new Scene(root, 650, 600);

			stage.setScene(reversiGameScene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this function set the setting scene in which the user can choose the
	 * players color and also which player will start. he also can choose the
	 * size of the board.
	 */
	@FXML
	protected void settings() {
		try {
			Stage stage = (Stage) Settings.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reversi/Settings.fxml"));

			VBox root = (VBox) loader.load();
			loader.setController(new SettingsController());
			Scene settingsScene = new Scene(root, 640, 500);
			settingsScene.getStylesheets().add(getClass().getResource("applicationSettings.css").toExternalForm());


			stage.setScene(settingsScene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this function set the exit scene.
	 */
	@FXML
	protected void exitGame() {
		Stage stage = (Stage) Exit.getScene().getWindow();
		stage.close();
	}

}