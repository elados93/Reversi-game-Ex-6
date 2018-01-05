package ReversiCode;

import GeneralDef.Owner;

public class Board {

	private int row;
	private int col;
	private Cell[][] matrix;

	Board(int row, int col) {
		this.row = row;
		this.col = col;

		matrix = new Cell[row][col];
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				this.matrix[i][j] = new Cell();

		initBoard();
	}

	public void initBoard() {
		// Initialize all the board with no owners.
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				matrix[i][j].setSymbol(Owner.NONE);

		// Set the center of the board with the 2 players.
		matrix[(row / 2) - 1][(col / 2) - 1].setSymbol(Owner.PLAYER_2);
		matrix[(row / 2) - 1][(col / 2)].setSymbol(Owner.PLAYER_1);
		matrix[(row / 2)][(col / 2)].setSymbol(Owner.PLAYER_2);
		matrix[(row / 2)][(col / 2) - 1].setSymbol(Owner.PLAYER_1);

	}

	public Board(Board board) {
		row = board.row;
		col = board.col;

		matrix = new Cell[row][col];
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				this.matrix[i][j] = new Cell();

		// Copy each cell from the parameter.
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Point p = new Point(i, j);
				matrix[i][j] = new Cell(board.getCell(p));
			}
		}
	}

	public Cell getCell(Point p) {
		return matrix[p.getX()][p.getY()];
	}

	public boolean isCellEmpty(Point p) {
		return !matrix[p.getX()][p.getY()].isCellActive();
	}

	public boolean isBoardFull() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (!matrix[i][j].isCellActive())
					return false;
			}
		}
		return true;
	}

	public boolean isInBoard(Point p) {
		return ((row > p.getX() && p.getX() >= 0) && (col > p.getY() && p.getY() >= 0));
	}

	public void markCell(Point p, Owner symbol) {
		matrix[p.getX()][p.getY()].setSymbol(symbol);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Point countOwner() {
		int counterX = 0;
		int counterO = 0;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j].getSymbol() == Owner.PLAYER_1)
					counterX++;
				else if (matrix[i][j].getSymbol() == Owner.PLAYER_2)
					counterO++;
			}
		}
		return new Point(counterX, counterO);
	}

	
}
