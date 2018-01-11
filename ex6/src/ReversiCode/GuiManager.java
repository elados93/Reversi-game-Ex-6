package ReversiCode;

import java.util.Scanner;
import java.util.Vector;

import GeneralDef.Owner;
import GeneralDef.Possible_OutCome;
import GeneralDef.Status;

public class GuiManager extends GameManager {

	public GuiManager(GameState gameState, Player player1, Player player2, GameRules gameRules, int currentPlayer) {
		super(gameState, player1, player2, null, gameRules, false);
		this.currentPlayer = currentPlayer == 1 ? player1 : player2;
	}

	@Override
	public void run() {
		Status status1 = checkStatus();

		while (status1 == Status.RUNNING) {
			playOneTurn();
			status1 = checkStatus();
		}

		if (checkStatus() == Status.WIN) {
			Owner winner = getWinner();
			// TODO print the winner biachhh
		}

		if (status1 == Status.DRAW) {
			// TODO print the draw biachhh
		}

	}

	@Override
	public void playOneTurn() {

		Possible_OutCome result;
		Owner currentOwner = currentPlayer == player1 ? Owner.PLAYER_1 : Owner.PLAYER_2;
		Owner otherOwner = currentPlayer == player1 ? Owner.PLAYER_2 : Owner.PLAYER_1;

		// TODO print this using the vector.
		Vector<Point> playerPossibleMoves;
		playerPossibleMoves = gameRules.getPossibleMoves(gameState, currentOwner);

		if (firstRun) {
			// TODO print player move possible moves...

			// Get a point from the player.
			lastMove = new Point(currentPlayer.getMove(gameState));
			result = gameRules.makeMove(gameState, lastMove, currentOwner);
			firstRun = false;

			gameRules.makePossibleMoves(gameState, otherOwner);
		} else { // It's not the first turn in the game.

			// TODO print last move.
			// TODO print next player move possible moves.

			lastMove = new Point(currentPlayer.getMove(gameState));
			result = gameRules.makeMove(gameState, lastMove, currentOwner);
			gameRules.makePossibleMoves(gameState, otherOwner);

		}

		// If the result isn't well defined check again.
		if (result == Possible_OutCome.SUCCESS) {
			if (currentPlayer == player1)
				currentPlayer = player2;
			else
				currentPlayer = player1;
		}
	}
}
