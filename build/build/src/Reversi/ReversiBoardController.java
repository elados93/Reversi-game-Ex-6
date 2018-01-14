package Reversi;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;

import GeneralDef.Owner;
import GeneralDef.Possible_OutCome;
import ReversiCode.Board;
import ReversiCode.GameRules;
import ReversiCode.GameState;
import ReversiCode.Player;
import ReversiCode.Point;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReversiBoardController implements Initializable {

	private Board board;
	private GameState gameState;
	private Player player1; // Reference to player 1.
	private Player player2; // Reference to player 2.
	private GameRules gameRules; // Reference to the game rules with the game
									// logic.
	private Player currentPlayer; // Pointer to the current player.
	private boolean firstRun; // Boolean switch if it's the first turn.
	private Point lastMove; // Pointer to the last player move, null if there
							// isn't one.
	private ReversiBoard reversiBoard;

	@FXML
	private HBox root;

	@FXML
	private Text player1Score;

	@FXML
	private Text player2Score;

	@FXML
	private Text currentPlayerField;

	@FXML
	private Text errorText;

	//constructor
	public ReversiBoardController(Board board, Player player1, Player player2, GameRules gameRules,
			Player currentPlayer, GameState gameState) {
		super();
		this.board = board;
		this.gameState = gameState;
		this.player1 = player1;
		this.player2 = player2;
		this.currentPlayer = currentPlayer;
		this.firstRun = true;
		this.lastMove = null;
		this.gameRules = gameRules;
	}

	/**
	 * initialize function.
	 * makes the screen resizable
	 * sets the text on the side of the game
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClickListener clickListener = new ClickListener(this);
		reversiBoard = new ReversiBoard(board, clickListener);

		reversiBoard.setPrefWidth(400);
		reversiBoard.setPrefHeight(400);
		root.getChildren().add(0, reversiBoard);
		root.setAlignment(Pos.CENTER);

		currentPlayerField.setText(currentPlayer == player1 ? "1" : "2");
		player1Score.setText(String.valueOf(board.getScore1()));
		player2Score.setText(String.valueOf(board.getScore2()));

		gameRules.makePossibleMoves(gameState, currentPlayer == player1 ? Owner.PLAYER_1 : Owner.PLAYER_2);
		markAllOptionalCells(
				gameRules.getPossibleMoves(gameState, currentPlayer == player1 ? Owner.PLAYER_1 : Owner.PLAYER_2));

		reversiBoard.draw();

		root.widthProperty().addListener((observable, oldValue, newValue) -> {
			markAllOptionalCells(
					gameRules.getPossibleMoves(gameState, currentPlayer == player1 ? Owner.PLAYER_1 : Owner.PLAYER_2));
			double boardNewWidth = newValue.doubleValue() - 150.0;
			reversiBoard.setPrefWidth(boardNewWidth);
			reversiBoard.draw();
		});

		root.heightProperty().addListener((observable, oldValue, newValue) -> {
			markAllOptionalCells(
					gameRules.getPossibleMoves(gameState, currentPlayer == player1 ? Owner.PLAYER_1 : Owner.PLAYER_2));
			reversiBoard.setPrefHeight(newValue.doubleValue());
			reversiBoard.draw();
		});

	}

	/**
	 * check what was the last click of the current player in the game and
	 * give the accurate details of the x and y values.
	 * @param x is the x value
	 * @param y is the y value.
	 */
	public void doOneClick(int x, int y) {
		lastMove = new Point(x, y);

		playOneTurn();
		currentPlayerField.setText(currentPlayer == player1 ? "1" : "2");
		// change the scores accordingly
		player1Score.setText(String.valueOf(board.getScore1()));
		player2Score.setText(String.valueOf(board.getScore2()));
		reversiBoard.draw();
	}

	/**
	 * this function make one turn in the game.
	 */
	public void playOneTurn() {

		Possible_OutCome result = null;
		// check who is the current player
		Owner currentOwner = currentPlayer == player1 ? Owner.PLAYER_1 : Owner.PLAYER_2;
		Owner otherOwner = currentPlayer == player1 ? Owner.PLAYER_2 : Owner.PLAYER_1;
		errorText.setText("");
		Vector<Point> playerPossibleMoves;
		playerPossibleMoves = gameRules.getPossibleMoves(gameState, currentOwner);

		if (firstRun) {
			result = gameRules.makeMove(gameState, lastMove, currentOwner);
			if (result != Possible_OutCome.SUCCESS) {
				unValidPointHandler(result, currentOwner);
				return;
			}

			firstRun = false;
			gameRules.makePossibleMoves(gameState, otherOwner);
			markAllOptionalCells(gameRules.getPossibleMoves(gameState, otherOwner));
		} else { // It's not the first turn in the game.
			if (!playerPossibleMoves.isEmpty()) {
				result = gameRules.makeMove(gameState, lastMove, currentOwner);
				if (result != Possible_OutCome.SUCCESS) {
					unValidPointHandler(result, currentOwner);
					return;
				}

				checkIfGameEnded(currentOwner, otherOwner);
				markAllOptionalCells(gameRules.getPossibleMoves(gameState, otherOwner));
				// check if the other owner has no moves
				if (gameRules.getPossibleMoves(gameState, otherOwner).isEmpty()) {
					gameRules.makePossibleMoves(gameState, currentOwner);
					markAllOptionalCells(gameRules.getPossibleMoves(gameState, currentOwner));
					return;
				}

			}
		}

		// after making a valid move ensures moving the turn to the other player
		if (result == Possible_OutCome.SUCCESS) {
			if (currentPlayer == player1)
				currentPlayer = player2;
			else
				currentPlayer = player1;
		}

	}

	/**
	 * this functions checks after each move of eeah player if the game ended.
	 * @param currentOwner is the current player
	 * @param otherOwner is the other player
	 */
	public void checkIfGameEnded(Owner currentOwner, Owner otherOwner) {
		// checks if the are option moves for any player
		gameRules.makePossibleMoves(gameState, currentOwner);
		gameRules.makePossibleMoves(gameState, otherOwner);

		// check if the game ended somehow
		if ((gameRules.getPossibleMoves(gameState, currentOwner).isEmpty()
				&& gameRules.getPossibleMoves(gameState, otherOwner).isEmpty()) || board.isBoardFull()) {
			String winner;
			// check the reason for ending the game
			if (board.getScore1() > board.getScore2())
				winner = "Player 1 wins!";
			else if (board.getScore2() > board.getScore1())
				winner = "Player 2 wins!";
			else
				winner = "Draw!";

			// make a message that the game anded for some reason
			Alert alert = new Alert(AlertType.INFORMATION, "Thank you for playing!");
			alert.setTitle("Game Ended");
			alert.setHeaderText(winner);
			alert.show();

			Stage primaryStage = (Stage) player1Score.getScene().getWindow();

			GridPane root = null;
			try {
				root = (GridPane) FXMLLoader.load(getClass().getResource("MainMenuFXML.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Scene scene = new Scene(root, 650, 600);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Reversi");
			primaryStage.setScene(scene);

			primaryStage.show();

		}
	}

	/**
	 * @param result is if the click that the user did was an optional move
	 * or if the move was not one of the options
	 * @param currentOwner is the current player
	 */
	public void unValidPointHandler(Possible_OutCome result, Owner currentOwner) {
		markAllOptionalCells(gameRules.getPossibleMoves(gameState, currentOwner));
		if (result == Possible_OutCome.ILLEGAL_MOVE)
			errorText.setText("Illegal Move !!");
		if (result == Possible_OutCome.OCCUPIED_CELL)
			errorText.setText("Occupied Cell !!");
	}

	/**
	 * mark all the optional cells for the current player
	 * 
	 * @param playerPossibleMoves
	 *            is the vector of optional moves for the current player
	 */
	public void markAllOptionalCells(Vector<Point> playerPossibleMoves) {
		for (int i = 0; i < playerPossibleMoves.size(); i++)
			board.getCell(playerPossibleMoves.get(i)).setPossibleOption(true);
	}
}