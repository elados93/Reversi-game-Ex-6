package ReversiCode;

import GeneralDef.Owner;
import Reversi.ParseSettingsFile;
import Reversi.ScoreTracker;

public class Board {

	private int row;
	private int col;
	private Cell[][] matrix;
	private ScoreTracker scoreTracker;

	public Board() {
		new Board(8, 8);
		this.scoreTracker = new ScoreTracker(2, 2);
	}

	public Board(int row, int col) {
		this.row = row;
		this.col = col;

		this.scoreTracker = new ScoreTracker(2, 2);

		matrix = new Cell[row][col];
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				this.matrix[i][j] = new Cell();

		initBoard();
	}

	public Board(Board board) {
		row = board.row;
		col = board.col;

		scoreTracker = board.scoreTracker;

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

	public void initBoard() {

		ParseSettingsFile parser = new ParseSettingsFile();
		parser.parseSettingsFile();
		String player1Color = parser.getPlayer1Color();
		String player2Color = parser.getPlayer2Color();

		// Set the center of the board with the 2 players.
		matrix[(row / 2) - 1][(col / 2) - 1].setSymbol(Owner.PLAYER_2, player2Color);
		matrix[(row / 2) - 1][(col / 2)].setSymbol(Owner.PLAYER_1, player1Color);
		matrix[(row / 2)][(col / 2)].setSymbol(Owner.PLAYER_2, player2Color);
		matrix[(row / 2)][(col / 2) - 1].setSymbol(Owner.PLAYER_1, player1Color);

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

	public void markCell(Point p, Owner symbol, String color) {
		Cell cell = matrix[p.getX()][p.getY()];

		if (cell.getSymbol() == Owner.PLAYER_1)
			scoreTracker.setP1Score(scoreTracker.getP1Score() - 1);
		else if (cell.getSymbol() == Owner.PLAYER_2)
			scoreTracker.setP2Score(scoreTracker.getP2Score() - 1);

		if (symbol == Owner.PLAYER_1)
			scoreTracker.setP1Score(scoreTracker.getP1Score() + 1);
		else if (symbol == Owner.PLAYER_2)
			scoreTracker.setP2Score(scoreTracker.getP2Score() + 1);

		matrix[p.getX()][p.getY()].setSymbol(symbol, color);
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

	public int getScore1() {
		return scoreTracker.getP1Score();
	}
	
	public int getScore2() {
		return scoreTracker.getP2Score();
	}
}
