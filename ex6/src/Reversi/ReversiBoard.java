package Reversi;

import java.io.IOException;

import ReversiCode.Board;
import ReversiCode.Point;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ReversiBoard extends GridPane {

	private Board board;
	// Adding CirclePad!@#!##$!

	public ReversiBoard(Board board) {
		this.board = board;

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public void draw() {
		this.getChildren().clear();

		int height = (int) this.getPrefHeight();
		int width = (int) this.getPrefWidth();

		int row = board.getRow();
		int col = board.getCol();
		
		int cellHeight = height / row;
		int cellWidth = width / col;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board.getCell(new Point(i, j)).isCellActive())
					this.add(new Button(), i, j);
				else
					this.add(new Button(), i, j);
			}
		}
	}

}
