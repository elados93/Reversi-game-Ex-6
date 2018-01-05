package Reversi;

import ReversiCode.Board;
import ReversiCode.ConsolePrinter;
import ReversiCode.GameManager;
import ReversiCode.GameState;
import ReversiCode.HumanPlayer;
import ReversiCode.ReversiDefaultRules;
import javafx.fxml.FXML;

public class FXMLDemoController {
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