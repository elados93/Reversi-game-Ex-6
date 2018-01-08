package Reversi;

import java.net.URL;
import java.util.ResourceBundle;

import ReversiCode.Board;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class ReversiBoardController implements Initializable {
	
	@FXML
	private HBox root;
	private Board board = new Board(8, 8);
	
	/*public ReversiBoardController(Board board) {
		this.board = board;
	}
*/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ReversiBoard reversiBoard = new ReversiBoard(board);
		reversiBoard.setPrefWidth(400);
		reversiBoard.setPrefHeight(400);
		root.getChildren().add(0, reversiBoard);
		reversiBoard.draw();
	}

}
