package ReversiCode;

public interface Player {
	
	public char getSymbol();
	public Point getMove(GameState gameState);
}
