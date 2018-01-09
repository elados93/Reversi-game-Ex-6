package Reversi;

import java.net.URL;
import java.util.ResourceBundle;

import ReversiCode.Board;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class ReversiBoardController implements Initializable {

	@FXML
	private HBox root;
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ReversiBoard reversiBoard = new ReversiBoard(new Board(8, 8));
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

		/*
		 * ReversiBoard reversiBoard = new ReversiBoard(new Board(8, 8));
		 * reversiBoard.setPrefWidth(400); reversiBoard.setPrefHeight(400);
		 * root.getChildren().add(0, reversiBoard);
		 * 
		 * ImageView imageBlack = new
		 * ImageView(getClass().getResource("BlackCircle.jpg").toExternalForm())
		 * ; ImageView imageWhite = new
		 * ImageView(getClass().getResource("WhiteCircle.jpg").toExternalForm())
		 * ; ImageView imageGray = new
		 * ImageView(getClass().getResource("GraySquare.png").toExternalForm());
		 * 
		 * imageBlack.setFitWidth(80); imageBlack.setFitHeight(80);
		 * 
		 * imageWhite.setFitWidth(80); imageWhite.setFitHeight(80);
		 * 
		 * imageGray.setFitWidth(80); imageGray.setFitHeight(80);
		 * 
		 * 
		 * reversiBoard.add(imageBlack, 0, 0); reversiBoard.add(imageWhite, 1,
		 * 0); reversiBoard.add(imageGray, 4, 4);
		 */

	}
}