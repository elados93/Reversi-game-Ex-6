package Reversi;

import ReversiCode.Point;

public class ClickListener {

	private Point lastClick;
	private boolean isPressed;
	
	public ClickListener() {
		this.lastClick = null;
		this.isPressed = false;
	}

	public Point getLastClick() {
		isPressed = false;
		return lastClick;
	}

	public boolean isPressed() {
		return isPressed;
	}
	
	public void clickEvent(BoardSquare boardSquare) {
		System.out.println("Hey now Brown Cow!!! " + boardSquare.getRow()+ " ," + boardSquare.getCol());

		isPressed = true;
		lastClick = new Point(boardSquare.getRow() - 1, boardSquare.getRow() - 1);
	}
	
}
