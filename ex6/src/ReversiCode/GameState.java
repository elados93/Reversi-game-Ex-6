package ReversiCode;

import java.util.Vector;

public class GameState {
	
	private Board board;
	Vector <Point> vec1;
	Vector <Point> vec2;
	
	public GameState(Board board) {
		this.board = board;
		this.vec1 = new Vector<>();
		this.vec2 = new Vector<>();
	}
	
	public Board getBoard() {
		return board;
	}

	public GameState(GameState gameState) {
	    //creates a new board like the board of the game state we get.
	    board = new Board(gameState.board);
	    
	    // creating the same vectors of the options of the player as the game state parameter.
	    for (int i = 0; i < gameState.vec1.size(); i++) {
	        Point pointToBeAdded = new Point(gameState.vec1.elementAt(i));
	        vec1.addElement(pointToBeAdded);
	    }

	    for (int i = 0; i < gameState.vec2.size(); i++) {
	        Point pointToBeAdded = new Point(gameState.vec2.elementAt(i));
	        vec2.addElement(pointToBeAdded);
	    }

	}
	
	boolean compare2Vectors(Vector<Point> v1, Vector<Point> v2) {
	    boolean boolTerm = true;
	    for (int i = 0; i < v1.size(); ++i) {
	        for (int j = 0; j < v2.size(); ++j) {
	            if (!v1.elementAt(i).isEqual(v2.elementAt(j)))
	                boolTerm = false;
	            else {
	                boolTerm = true;
	                break;
	            }
	        }
	        if (!boolTerm)
	            break;
	    }

	    for (int i = 0; i < v2.size(); ++i) {
	        for (int j = 0; j < v1.size(); ++j) {
	            if (!v2.elementAt(i).isEqual(v1.elementAt(j)))
	                boolTerm = false;
	            else {
	                boolTerm = true;
	                break;
	            }
	        }
	        if (!boolTerm)
	            break;
	    }
	    return boolTerm;
	}

	
}
