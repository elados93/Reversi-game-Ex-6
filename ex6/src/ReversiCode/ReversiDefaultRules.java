package ReversiCode;

import java.util.Vector;

import GeneralDef.Owner;
import GeneralDef.Possible_OutCome;

public class ReversiDefaultRules implements GameRules {

	public ReversiDefaultRules(){};
	
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

	@Override
	public Vector<Point> getPossibleMoves(GameState gameState, Owner symbol) {
		return symbol == Owner.PLAYER_1 ? (gameState.vec1) : (gameState.vec2);
	}

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
	            (gameState.getBoard()).markCell(currentPoint, symbol);
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
	
	public void freePointsInVec(GameState gameState, Owner symbol) {

		if (symbol == Owner.PLAYER_1) {
	        gameState.vec1.clear();
	    }

	    if (symbol == Owner.PLAYER_2) {
	    	gameState.vec2.clear();
	        
	    }

	}

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
	            Point pointToAddFlow = new Point(getPointFromVec(p, currentPlayerVector));
	            pointToAddFlow.insertFlowPoint(flowPoint);
	        }
	    }
	}

	boolean isLegal(GameState gameState, Point p){
		return (gameState.getBoard()).isInBoard(p);
	}
	
	Point getPointFromVec(Point point, Vector<Point> vec){
		for (int i = 0; i < vec.size(); ++i) {
	        if (vec.elementAt(i).isEqual(point)) {
	            return vec.elementAt(i);
	        }
	    }
	    return null; // If the point isn't in the vector return null.
	}
	
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


