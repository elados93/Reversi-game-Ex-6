package Reversi;

public class ClickListener {
	
	private ReversiBoardController reversiBoardController;

	public ClickListener(ReversiBoardController reversiBoardController) {
		this.reversiBoardController = reversiBoardController;
	}
	
	public void ClickEvent(BoardSquare boardSquare) {
		reversiBoardController.doOneClick(boardSquare.getRow(), boardSquare.getCol());
	}
}
