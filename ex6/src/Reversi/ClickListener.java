package Reversi;

public class ClickListener {
	
	private ReversiBoardController reversiBoardController;

	public ClickListener(ReversiBoardController reversiBoardController) {
		this.reversiBoardController = reversiBoardController;
	}
	
	/**
	 * this function make sure the reversi board will know when a board square had
	 * a click from the user. it gives the controller the details
	 * of the place the click was by the row and column on the board.
	 * @param boardSquare is the specific board square that had a click
	 */
	public void ClickEvent(BoardSquare boardSquare) {
		reversiBoardController.doOneClick(boardSquare.getRow(), boardSquare.getCol());
	}
}
