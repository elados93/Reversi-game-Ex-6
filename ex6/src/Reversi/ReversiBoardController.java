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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ReversiBoard reversiBoard = new ReversiBoard(new Board(8, 8));
		reversiBoard.setPrefWidth(400);
		reversiBoard.setPrefHeight(400);
		root.getChildren().add(0, reversiBoard);
		reversiBoard.draw();
	}

}
