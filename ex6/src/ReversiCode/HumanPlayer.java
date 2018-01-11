/*package ReversiCode;

import java.util.Scanner;

public class HumanPlayer implements Player {
	private char symbol;

	public HumanPlayer(char s) {
		this.symbol = s;
	}

	@Override
	public char getSymbol() {
		return this.symbol;
	}

	@Override
	public Point getMove(GameState gameState) {

		Scanner scanIn = new Scanner(System.in);
		String requset = null;

		requset = scanIn.nextLine();

		int commaOccurence = requset.indexOf(",");

		String xString = requset.substring(0, commaOccurence).trim();
		String yString = requset.substring(commaOccurence + 1, requset.length()).trim();

		int x = Integer.parseInt(xString) - 1;
		int y = Integer.parseInt(yString) - 1;
		// scanIn.close();
		return new Point(x, y);
	}

}
*/