package Reversi;

public class ScoreTracker {

	private int p1Score;
	private int p2Score;

	/**
	 * constructor
	 * @param p1Score
	 * @param p2Score
	 */
	public ScoreTracker(int p1Score, int p2Score) {
		super();
		this.p1Score = p1Score;
		this.p2Score = p2Score;
	}

	/** 
	 * @return players 1 score
	 */
	public int getP1Score() {
		return p1Score;
	}

	/** 
	 * change the score of player 1
	 */
	public void setP1Score(int p1Score) {
		this.p1Score = p1Score;
	}

	/** 
	 * @return players 2 score
	 */
	public int getP2Score() {
		return p2Score;
	}

	/** 
	 * change the score of player 1
	 */
	public void setP2Score(int p2Score) {
		this.p2Score = p2Score;
	}

}
