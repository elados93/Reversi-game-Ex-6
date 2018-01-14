package ReversiCode;

import java.util.Vector;

import GeneralDef.Owner;
import GeneralDef.Possible_OutCome;
import Reversi.ParseSettingsFile;
import Reversi.SettingsController;

public class ReversiDefaultRules implements GameRules {

	private String p1Color;
	private String p2Color;
	
	public ReversiDefaultRules(){
		ParseSettingsFile parser = new ParseSettingsFile();
		parser.parseSettingsFile();
		this.p1Color = parser.getPlayer1Color();
		this.p2Color = parser.getPlayer2Color();
	};

	/**
     * make a vector with all the possible moves of symbol.
     * @param symbol The current player.
     * @return The possible moves vector.
     */
    
	@Override
	public void makePossibleMoves(GameState gameState, Owner symbol) {
		// Free all the previous allocations of the possible points.
	    freePointsInVec(gameState, symbol);

	    int row = (gameState.getBoard()).getRow();
	    int col = (gameState.getBoard()).getCol();

	    for(int i = 0;i < row;i++) {
	        for(int j = 0;j < col;j++) {
	            Point p = new Point(i, j);
	            Owner currentSymbol = (gameState.getBoard()).getCell(p).getSymbol();
	            if (currentSymbol == symbol)  // Check only the relevant cells.
	                checkSurround(gameState, p, symbol);
	        }
	    }

	}

	/**
	 * Returns the possible moves of "symbol"
	 * @param symbol The player.
	 * @return Possible moves of symbol.
	 */
	@Override
	public Vector<Point> getPossibleMoves(GameState gameState, Owner symbol) {
		return symbol == Owner.PLAYER_1 ? (gameState.vec1) : (gameState.vec2);
	}

	/**
     * Make a single move of "symbol" at point p.
     * @param p Given point to mark.
     * @param symbol The player who plays.
     * @return Possible outcome of the procedure.
     */
	@Override
	public Possible_OutCome makeMove(GameState gameState, Point p, Owner symbol) {
		// Not in the board.
	    if (!(gameState.getBoard()).isInBoard(p)) {
	        return Possible_OutCome.OUT_OF_BOUND;
	    }

	    // The cell is occupied.
	    if (!(gameState.getBoard()).isCellEmpty(p)) {
	        return Possible_OutCome.OCCUPIED_CELL;
	    }

	    // Not one of the possible options.
	    if (!isAlreadyContains(gameState, p, symbol)) {
	        return Possible_OutCome.ILLEGAL_MOVE;
	    }

	    // Check all the possible directions of the point and mark the required cells.
	    Vector<Point> possibleMoves = (symbol == Owner.PLAYER_1 ? gameState.vec1 : gameState.vec2);
	    Vector<Point> flowVec = getPointFromVec(p, possibleMoves).getDirVector();

	    for (int i = 0; i < flowVec.size(); i++) {
	        int dRow = flowVec.elementAt(i).getX();
	        int dCol = flowVec.elementAt(i).getY();

	        Point currentPoint = new Point(p);
	        Cell currentCell;

	        // Mark the cell in the right direction until you first meet the player symbol.
	        do {
	        	String color = null;
	        	if (symbol == Owner.PLAYER_1)
	    			color = p1Color;
	    		else
	    			if (symbol == Owner.PLAYER_2)
	    				color = p2Color;
	    		
	            (gameState.getBoard()).markCell(currentPoint, symbol, color);
	            currentPoint.setX(currentPoint.getX() + dRow);
	            currentPoint.setY(currentPoint.getY() + dCol);
	            if ((gameState.getBoard()).isInBoard(currentPoint)) {
	                currentCell = (gameState.getBoard()).getCell(currentPoint);
	            } else {
	                break;
	            }
	        } while (currentCell.getSymbol() != symbol);
	    }

	    return Possible_OutCome.SUCCESS;
	}
	
	/**
     * Free all the points in the vector of "symbol" player.
     * @param symbol The player to delete his vector.
     */
    
	public void freePointsInVec(GameState gameState, Owner symbol) {

		if (symbol == Owner.PLAYER_1) {
	        gameState.vec1.clear();
	    }

	    if (symbol == Owner.PLAYER_2) {
	    	gameState.vec2.clear();
	        
	    }

	}

	/**
     * Check all the surrounding cell of Point p of the player symbol.
     * @param p Point to check his surrounding.
     * @param symbol The current player.
     */
    
	public void checkSurround(GameState gameState, Point p, Owner symbol){
		int r = p.getX();
	    int c = p.getY();
	    int dRow = -1;
	    int dCol;

	    for (int i = 0; i < 3; i++) {
	        dCol = -1;
	        for (int j = 0; j < 3; j++) {

	            // Make sure not to check the current cell again.
	            if (dRow == 0 && dCol == 0) {
	                dCol++;
	                continue;
	            }

	            Point currentPoint = new Point(r + dRow, c + dCol);
	            if ((gameState.getBoard()).isInBoard(currentPoint)) {
	            
	                Cell currentCell = (gameState.getBoard()).getCell(currentPoint);
	                Owner otherSymbol = currentCell.getSymbol();

	                // Check if the near cell belongs to the other player.
	                if (isLegal(gameState, currentPoint) && otherSymbol != symbol && currentCell.isCellActive()) 
	                    moveAlong(gameState, currentPoint, otherSymbol, dRow, dCol);
	            }
	            dCol++;
	        }
	        dRow++;
	    }
	}
	
	/**
     * Move along the dRow & dCol direction and look for possible moves.
     * @param p The point to begin with.
     * @param symbol The current player.
     * @param dRow The difference in row.
     * @param dCol The difference in col.
     */
    
	void moveAlong(GameState gameState, Point p ,Owner symbol, int dRow, int dCol) {
	    Cell currentCell = (gameState.getBoard()).getCell(p);
	    Owner currentSymbol = (symbol == Owner.PLAYER_1 ? Owner.PLAYER_2 : Owner.PLAYER_1);

	    while(isLegal(gameState, p) && currentCell.getSymbol() == symbol) {

	        // Advance the current point.
	        p.setX(p.getX() + dRow);
	        p.setY(p.getY() + dCol);
	        if (gameState.getBoard().isInBoard(p)) {
	            // Advance the current cell.
	            currentCell = gameState.getBoard().getCell(p);
	        } else {
	            break;
	        }
	    }
	    
	    // Check if the cell isn't active.
	    if (!currentCell.isCellActive()) {

	        // Check if the potential point isn't already in the vector.
	        boolean check = this.isAlreadyContains(gameState, p, currentSymbol);
	        Point flowPoint = new Point(dRow * -1, dCol * -1);
	        if (!check) {
	            Point wantedPoint = new Point(p);
	            wantedPoint.insertFlowPoint(flowPoint);

	            if (symbol == Owner.PLAYER_1) {
	                gameState.vec2.addElement(wantedPoint);
	            }

	            if (symbol == Owner.PLAYER_2) {
	                gameState.vec1.addElement(wantedPoint);
	            }
	        } else {
	            Vector <Point> currentPlayerVector = getPossibleMoves(gameState, currentSymbol);
	            // Although the points is in the vector, it might have another direction to move along.
	            Point pointToAddFlow = getPointFromVec(p, currentPlayerVector);
	            pointToAddFlow.insertFlowPoint(flowPoint);
	        }
	    }
	}

	/**
     * Returns true if the point is in the board.
     * @param p Point to check.
     * @return True if the point it's in the board.
     */
    
	boolean isLegal(GameState gameState, Point p){
		return (gameState.getBoard()).isInBoard(p);
	}
	
	/**
     * Get the specific point "point" in vector "vec".
     * @param point Point to extract from vec.
     * @param vec Given vector of Point objects.
     * @return The point in the vector.
     */
	Point getPointFromVec(Point point, Vector<Point> vec){
		for (int i = 0; i < vec.size(); ++i) {
	        if (vec.elementAt(i).isEqual(point)) {
	            return vec.elementAt(i);
	        }
	    }
	    return null; // If the point isn't in the vector return null.
	}
	
	/**
     * checks if a specific point is in the vector of the player with symbol that we get as a parameter.
     * @param gameState
     * @param p is the point to check.
     * @param symbol is the symbol of the current player.
     * @return true if the point is in the vector.
     */
	boolean isAlreadyContains(GameState gameState, Point p, Owner symbol){
	    if (symbol == Owner.PLAYER_1) {
	        for (int i = 0; i < gameState.vec1.size(); i++) {

	            // Check if the point already exists.
	            if (p.isEqual(gameState.vec1.elementAt(i))) {
	                return true;
	            }
	        }
	    } else {
	        if (symbol == Owner.PLAYER_2) {
	            for (int i = 0; i < gameState.vec2.size(); i++) {

	                // Check if the point already exists.
	                if (p.isEqual(gameState.vec2.elementAt(i))) {
	                    return true;
	                }
	            }
	        }
	    }

	    return false;

	}
}


