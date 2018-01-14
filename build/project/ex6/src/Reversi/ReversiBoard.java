package Reversi;

import java.io.IOException;

import GeneralDef.Owner;
import ReversiCode.Board;
import ReversiCode.Cell;
import ReversiCode.Point;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

public class ReversiBoard extends GridPane {

	private Board board;
	private BoardSquare[][] boardSquars;
	private ClickListener clickListener;

	/**
	 * constructor
	 * @param board is the board of the game with the specific game state
	 * @param clickListener is the listener that follow each mouse press.
	 */
	public ReversiBoard(Board board, ClickListener clickListener) {
		this.board = board;
		this.boardSquars = new BoardSquare[board.getRow()][board.getCol()];
		this.clickListener = clickListener;
		
		// the details of how the board will look like
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Reversi/ReversiBoard.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

	}

	/**
	 * draw the board with each cell in the board with the specific owner
	 */
	public void draw() {
		this.getChildren().clear();

		double height = (int) this.getPrefHeight();
		double width = (int) this.getPrefWidth();

		double row = board.getRow();
		double col = board.getCol();

		double cellHeight = height / row;
		double cellWidth = width / col;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				Cell cell = board.getCell(new Point(i, j));
				// moves the click listener to each board square
				boardSquars[i][j] = new BoardSquare(this, cell, i, j, clickListener);
				boardSquars[i][j].draw(cellWidth, cellHeight);
			}
		}
	}

	/** 
	 * @return the cell height
	 */
	public double getCellHeight() {
		return this.getPrefHeight() / board.getRow();
	}

	/** 
	 * @return the cell width
	 */
	public double getCellWidth() {
		return this.getPrefWidth() / board.getCol();
	}
}
