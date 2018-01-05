package ReversiCode;

import java.util.Vector;

import GeneralDef.Owner;
import GeneralDef.Possible_OutCome;

public interface GameRules {
	
	 /**
     *  Make the vector of possible moves of "symbol" and also returns it.
     *  Notice the difference with getPossibleMoves function that's only returns it.
     * @param symbol The player.
     */
	public void makePossibleMoves(GameState gameState, Owner symbol);
	
	 /**
	 * Returns the possible moves of "symbol"
	 * @param symbol The player.
	 * @return Possible moves of symbol.
	 */
	Vector<Point> getPossibleMoves(GameState gameState, Owner symbol);
	 /**
     * Make a single move of "symbol" at point p.
     * @param p Given point to mark.
     * @param symbol The player who plays.
     * @return Possible outcome of the procedure.
     */
	Possible_OutCome makeMove(GameState gameState, Point p, Owner symbol);
}
