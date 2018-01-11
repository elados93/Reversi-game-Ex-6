package ReversiCode;

public interface Player {
	
	public String getSymbol();
	public Point getMove(GameState gameState);
}
