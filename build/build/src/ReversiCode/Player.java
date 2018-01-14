package ReversiCode;

public interface Player {
	
	public String getSymbol();
	
	/**
     * Get the move from the user according to the rules of the game.
     * @return The valid input point from user.
     */
	public Point getMove(GameState gameState);
}
