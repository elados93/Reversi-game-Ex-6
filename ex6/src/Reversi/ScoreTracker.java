package Reversi;

public class ScoreTracker {

	private int p1Score;
	private int p2Score;

	public ScoreTracker(int p1Score, int p2Score) {
		super();
		this.p1Score = p1Score;
		this.p2Score = p2Score;
	}

	public int getP1Score() {
		return p1Score;
	}

	public void setP1Score(int p1Score) {
		this.p1Score = p1Score;
	}

	public int getP2Score() {
		return p2Score;
	}

	public void setP2Score(int p2Score) {
		this.p2Score = p2Score;
	}

}
