package Reversi;

import GeneralDef.Owner;
import ReversiCode.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BoardSquare extends Rectangle {

	private GridPane grid;
	private int row;
	private int col;
	private Cell cell;

	public BoardSquare(GridPane gridPane, Cell cell, int row, int col) {
		super();
		this.grid = gridPane;
		this.cell = cell;
		this.row = row;
		this.col = col;
	}

	public void draw(double cellWidth, double cellHeight) {

		this.setWidth(cellWidth);
		this.setHeight(cellHeight);
		this.setStroke(Color.BLACK);
		this.setFill(Color.SADDLEBROWN); // default brown square color
		grid.add(this, col, row);

		if (cell.getSymbol() != Owner.NONE) {
			Circle circle = new Circle();
			circle.setRadius((cellHeight + cellWidth) / 4.0);
			circle.setFill(Color.web(cell.getColor()));
			grid.add(circle, col, row);
		}
		
		if (cell.isPossibleOption()) {
			cell.setPossibleOption(false);
			Circle circle = new Circle();
			circle.setStroke(Color.PINK);
			grid.add(circle, col, row);
		}
	}

	public int getRow() {
		return row;
	}
	

	public int getCol() {
		return col;
	}

}
