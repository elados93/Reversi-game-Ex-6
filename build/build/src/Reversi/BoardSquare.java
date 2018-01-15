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
	private ClickListener clickListener;

	public BoardSquare(GridPane gridPane, Cell cell, int row, int col, ClickListener clickListener) {
		super();
		this.grid = gridPane;
		this.cell = cell;
		this.row = row;
		this.col = col;
		this.clickListener = clickListener;
	}

	/**
	 * draws the cell whether it is a plane cell or if it is occiupied.
	 * 
	 * @param cellWidth
	 *            is the cell width
	 * @param cellHeight
	 *            is the cell height
	 */
	public void draw(double cellWidth, double cellHeight) {
		Rectangle rec = new Rectangle(cellWidth, cellHeight, Color.DARKGRAY);
		rec.setStroke(Color.BLACK);
		this.setCenter(rec);
		grid.add(this, col, row);

		double radius;
		if (cellHeight < cellWidth)
			radius = cellHeight / 2.0 - 5.0;
		else
			radius = cellWidth / 2.0 - 5.0;

		// draw the circle according the player owning the cell
		if (cell.getSymbol() != Owner.NONE) {
			BorderPane pane = new BorderPane();
			Circle circle = new Circle(cellWidth / 2.0, cellHeight / 2.0, radius, Color.web(cell.getColor()));
			pane.setCenter(circle);
			grid.add(pane, col, row);
			pane.setOnMouseClicked(e -> {
				clickListener.ClickEvent(this);
			});
		} else {
			// the cell is a possible option that drawing a hole circle
			if (cell.isPossibleOption()) {
				BorderPane pane = new BorderPane();
				Circle circleOptional = new Circle();
				circleOptional.setFill(Color.TRANSPARENT);
				circleOptional.setRadius(radius);
				circleOptional.setStroke(Color.WHITE);
				pane.setCenter(circleOptional);
				grid.add(pane, col, row);
				// letting know what was the option of the player
				pane.setOnMouseClicked(e -> {
					clickListener.ClickEvent(this);
				});
			}
		}
		cell.setPossibleOption(false);

		// in case the specific board square experienced a click the listener
		// listen
		this.setOnMouseClicked(e -> {
			clickListener.ClickEvent(this);
		});
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

}
