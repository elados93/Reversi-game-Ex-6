package Reversi;

import java.net.URL;
import java.util.ResourceBundle;

import ReversiCode.Board;
import ReversiCode.GuiManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class ReversiBoardController implements Initializable {

	private ClickListener clickListener;
	private Board board;

	@FXML
	private HBox root;

	public ReversiBoardController(Board board, ClickListener clickListener) {
		super();
		this.clickListener = clickListener;
		this.board = board;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ReversiBoard reversiBoard = new ReversiBoard(board, clickListener);
		reversiBoard.setPrefWidth(400);
		reversiBoard.setPrefHeight(400);
		root.getChildren().add(0, reversiBoard);

		reversiBoard.draw();

		root.widthProperty().addListener((observable, oldValue, newValue) -> {
			double boardNewWidth = newValue.doubleValue() - 120;
			reversiBoard.setPrefWidth(boardNewWidth);
			reversiBoard.draw();
		});

		root.heightProperty().addListener((observable, oldValue, newValue) -> {
			reversiBoard.setPrefHeight(newValue.doubleValue());
			reversiBoard.draw();
		});
	}
}