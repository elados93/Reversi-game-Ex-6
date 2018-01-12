package Reversi;

import GeneralDef.Owner;
import ReversiCode.Cell;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BoardSquare extends BorderPane {

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

		Rectangle rec = new Rectangle(cellWidth, cellHeight, Color.DARKGRAY);
		rec.setStroke(Color.BLACK);
		this.setCenter(rec);
		grid.add(this, col, row);

		if (cell.getSymbol() != Owner.NONE) {
			BorderPane pane = new BorderPane();
			Circle circle = new Circle(cellWidth / 2.0, cellHeight / 2.0, (cellHeight + cellWidth) / 4.0 - 5.0,
					Color.web(cell.getColor()));
			pane.setCenter(circle);
			grid.add(pane, col, row);
		} else {
			if (cell.isPossibleOption()) {
				BorderPane pane = new BorderPane();
				Circle circleOptional = new Circle();
				circleOptional.setFill(Color.TRANSPARENT);
				circleOptional.setRadius((cellHeight + cellWidth) / 4.0 - 5.0);
				circleOptional.setStroke(Color.WHITE);
				pane.setCenter(circleOptional);
				grid.add(pane, col, row);
			}
		}
		cell.setPossibleOption(false);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

}
