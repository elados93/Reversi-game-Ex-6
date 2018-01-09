package Reversi;

import GeneralDef.Owner;
import ReversiCode.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BoardSquare extends Circle {

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
						
		Rectangle rec = new Rectangle(cellWidth, cellHeight);
		rec.setStroke(Color.BLACK);
		rec.setFill(Color.GRAY);
		grid.add(rec, col, row);

		if (cell.getSymbol() == Owner.PLAYER_1) {
			this.setRadius((cellHeight + cellWidth) / 4.0);
			this.setFill(Color.BLACK);
			grid.add(this, col, row);
		}

		if (cell.getSymbol() == Owner.PLAYER_2) {
			this.setRadius((cellHeight + cellWidth) / 4.0);
			this.setFill(Color.WHITE);
			grid.add(this, col, row);
		}
		
		rec.setOnMouseClicked(e-> {
	        System.out.println(row + " ," + col);
		});
		
		this.setOnMouseClicked(e-> {
	        System.out.println(row + " ," + col);
		});

	}

}
