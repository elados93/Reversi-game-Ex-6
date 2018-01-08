package Reversi;


import com.sun.javafx.geom.Rectangle;

import ReversiCode.Cell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class BoardSquare extends GridPane {

	private GridPane grid;
	private int row;
	private int col;
	private Cell cell;
	private ImageView image;

	public BoardSquare(GridPane gridPane, Cell cell, int row, int col, String color) {
		this.grid = gridPane;
		this.cell = cell;
		this.row = row;
		this.col = col;
		if (color.compareTo("Black") == 0)
			this.image = new ImageView(getClass().getResource("BlackCircle.jpg").toExternalForm());
		if (color.compareTo("White") == 0)
			this.image = new ImageView(getClass().getResource("WhiteCircle.jpg").toExternalForm());
		if (color.compareTo("Gray") == 0)
			this.image = new ImageView(getClass().getResource("GrayCircle.jpg").toExternalForm());

	}

	public void draw(int cellWidth, int cellHeight) {
		grid.add(image, col, row);
		image.setFitWidth(cellWidth);
		image.setFitHeight(cellHeight);
		grid.getChildren().remove(image);
		grid.add(image, col, row);
	}

}
