package ReversiCode;

import GeneralDef.*;
import javafx.geometry.Pos;

import java.util.Scanner;
import java.util.Vector;

public class GameManager {

	protected GameState gameState;
	protected Player player1; // Reference to player 1.
	protected Player player2; // Reference to player 2.
	protected Printer printer; // Reference to the printer.
	protected GameRules gameRules; // Reference to the game rules with the game
									// logic.
	protected Player currentPlayer; // Pointer to the current player.
	protected boolean firstRun; // Boolean switch if it's the first turn.
	protected Point lastMove; // Pointer to the last player move, null if there
								// isn't one.
	protected boolean isAIPlayer; // True if the second player is AIPlayer.

	public GameManager(GameState gameState, Player player1, Player player2, Printer printer, GameRules gameRules,
			boolean isAIPlayer) {
		this.gameState = gameState;
		this.player1 = player1;
		this.player2 = player2;
		this.printer = printer;
		this.gameRules = gameRules;
		this.isAIPlayer = isAIPlayer;

		this.currentPlayer = player1;
		this.lastMove = null;
		this.firstRun = true;
	}

	public Owner getWinner() {
		int p1Counter = 0, p2Counter = 0;
		int row = gameState.getBoard().getRow();
		int col = gameState.getBoard().getCol();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Point p = new Point(i, j);
				if (gameState.getBoard().getCell(p).getSymbol() == Owner.PLAYER_1)
					p1Counter++;
				else
					p2Counter++;
			}
		}
		if (p1Counter > p2Counter)
			return Owner.PLAYER_1;

		if (p1Counter < p2Counter)
			return Owner.PLAYER_2;

		return Owner.NONE;
	}

	public Status checkStatus() {
		gameRules.makePossibleMoves(gameState, Owner.PLAYER_1);
		gameRules.makePossibleMoves(gameState, Owner.PLAYER_2);

		boolean isFirstEmpty = gameState.vec1.isEmpty();
		boolean isSecondEmpty = gameState.vec2.isEmpty();

		if (gameState.getBoard().isBoardFull() || (isFirstEmpty && isSecondEmpty)) {
			Owner result = getWinner();
			if (result == Owner.NONE)
				return Status.DRAW;
			else
				return Status.WIN;
		}

		return Status.RUNNING;
	}

	public void run() {
	    Status status1 = checkStatus();

	    while (status1 == Status.RUNNING) {
	        playOneTurn();
	        if (currentPlayer == player1) 
	            currentPlayer = player2;
	         else 
	            currentPlayer = player1;
	        
	        status1 = checkStatus();
	    }

	    printer.printBoard();
	    printer.printLastMove(currentPlayer, lastMove);

	    if (checkStatus() == Status.WIN) {
	        Owner winner = getWinner();
	        if (winner == Owner.PLAYER_1)
	            printer.printEndOfGame(player1, status1);

	        if (winner == Owner.PLAYER_2)
	            printer.printEndOfGame(player2, status1);

			Scanner scanIn = new Scanner(System.in);
	        char dummy = scanIn.next().charAt(0);
	        scanIn.close();
	    }

	    if (status1 == Status.DRAW)
	        printer.printEndOfGame(player1, Status.DRAW);

	}
	
	public void playOneTurn() {

	    if (isAIPlayer && currentPlayer == player1) // Print the board for player1 v.s the AIPlayer.
	        printer.printBoard();
	    if (!isAIPlayer) // Print the board normally when 2 humans play.
	        printer.printBoard();

	    Possible_OutCome result;
	    Vector<Point> playerPossibleMoves;

	    if (currentPlayer == player1) {
	        playerPossibleMoves = gameRules.getPossibleMoves(gameState, Owner.PLAYER_1);
	    } else {
	        playerPossibleMoves = gameRules.getPossibleMoves(gameState, Owner.PLAYER_2);
	    }

	    // If the game v.s the computer and it's player1 turn OR 2 humans are playing aware they have no move.
	    if ((!isAIPlayer && playerPossibleMoves.isEmpty()) ||
	        (isAIPlayer && playerPossibleMoves.isEmpty() && currentPlayer == player1)) {
	        printer.printLastMove(currentPlayer, lastMove);
	        printer.printNextPlayerMove(currentPlayer, playerPossibleMoves);
	        Scanner reader = new Scanner(System.in);
	        char dummy; // Input any key from the user
	        dummy = reader.next().charAt(0);


	        lastMove = null;
	        return;
	    }

	    if (firstRun) {

	        // The first turn in the game player1 play.
	        printer.printNextPlayerMove(player1, playerPossibleMoves);

	        // Get a point from the player.
	        lastMove = new Point(player1.getMove(gameState));
	        result = gameRules.makeMove(gameState, lastMove, Owner.PLAYER_1);
	        firstRun = false;

	        gameRules.makePossibleMoves(gameState, Owner.PLAYER_2);
	    } else { // It's not the first turn in the game.

	        if (currentPlayer == player1) {

	            // Player 1 turn.
	            printer.printLastMove(player2, lastMove);

	            printer.printNextPlayerMove(player1, playerPossibleMoves);
	            lastMove = new Point(player1.getMove(gameState));
	            result = gameRules.makeMove(gameState, lastMove, Owner.PLAYER_1);
	            gameRules.makePossibleMoves(gameState, Owner.PLAYER_2);

	        } else {
	            // Player 2 turn.
	            if (isAIPlayer) {

	                lastMove = new Point(player2.getMove(gameState));

	                if (!lastMove.isEqual(new Point(-1, -1))) { // Check the option of AIPlayer which has no moves.
	                    result = gameRules.makeMove(gameState, lastMove, Owner.PLAYER_2);
	                    gameRules.makePossibleMoves(gameState, Owner.PLAYER_1);
	                } else
	                    result = Possible_OutCome.SUCCESS; // AIPlayer didn't player but the show must go on.
	            } else { // The option where player 2 is human.
	                printer.printLastMove(player1, lastMove);

	                printer.printNextPlayerMove(player2, playerPossibleMoves);

	                lastMove = new Point(player2.getMove(gameState));

	                result = gameRules.makeMove(gameState, lastMove, Owner.PLAYER_2);

	                gameRules.makePossibleMoves(gameState, Owner.PLAYER_1);
	            }

	        } // End Player2 flow.

	    }

	    // If the result isn't well defined check again.
	    if (result != Possible_OutCome.SUCCESS) {
	        inputUntilValid(result);
	    }
	}
	
	private void inputUntilValid(Possible_OutCome result) {
	    Owner currentP;

	    if (currentPlayer == player1) {
	        currentP = Owner.PLAYER_1;
	    } else {
	        currentP = Owner.PLAYER_2;
	    }

	    while (result != Possible_OutCome.SUCCESS) {
	        printer.printError(result);

	        lastMove = new Point(currentPlayer.getMove(gameState));
	        result = gameRules.makeMove(gameState, lastMove, currentP);
	    }
	}

}
