package Reversi;

import java.io.IOException;

import GeneralDef.Owner;
import ReversiCode.Board;
import ReversiCode.Cell;
import ReversiCode.Point;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class ReversiBoard extends GridPane {

	private Board board;
	BoardSquare[][] boardSquars;

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
				Cell cell = board.getCell(new Point(i, j));
				
				if (cell.getSymbol() == Owner.PLAYER_1) {
					this.add(new BoardSquare(this, cell, row, col, "Black"), j, i);
				}
				
				if (cell.getSymbol() == Owner.PLAYER_2)
					this.add(new BoardSquare(this, cell, row, col, "White"), i, j);
				
				if (cell.getSymbol() == Owner.NONE)
					this.add(new BoardSquare(this, cell, row, col, "Gray"), i, j);

			}
		}
	}

}
