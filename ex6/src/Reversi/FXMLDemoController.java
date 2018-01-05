package Reversi;

import ReversiCode.Board;
import ReversiCode.ConsolePrinter;
import ReversiCode.GameManager;
import ReversiCode.GameState;
import ReversiCode.HumanPlayer;
import ReversiCode.ReversiDefaultRules;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FXMLDemoController {
	private Button Start_Game;

	@FXML
	protected void startGame() {
			
		Board board = new Board(4, 4);
		HumanPlayer player1 = new HumanPlayer('x');
		HumanPlayer player2 = new HumanPlayer('o');
		GameState gameState = new GameState(board);
		ReversiDefaultRules rules = new ReversiDefaultRules();
		ConsolePrinter printer = new ConsolePrinter(board, player1, player2);
		GameManager gameManager = new GameManager(gameState, player1, player2, printer, rules, false);
		
		gameManager.run();

	}
}