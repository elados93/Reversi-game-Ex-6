package Reversi;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;

public class FXMLDemoController {
	@FXML
	private Label Reversi_Title;
	@FXML
	private Button Start_Game;
	@FXML
	private Button Settings;
	@FXML
	private Button Exit;

	@FXML
	protected void menuDetails() {
		Start_Game.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Start_Game.setText("Clicked!! OMGGGG");

			}
		});

		Settings.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Settings.setText("Don't change anything!!!");
			}

		});

		Exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Exit.setFont(new Font("Arial", 50));
			}
		});

	}
}