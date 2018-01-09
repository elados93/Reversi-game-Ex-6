package Reversi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SettingsController implements Initializable {

	private static final int LINES = 4;

	@FXML
	private ColorPicker Player1Color;

	@FXML
	private ColorPicker Player2Color;

	@FXML
	private ChoiceBox<Integer> rowBox;

	@FXML
	private ChoiceBox<Integer> colBox;

	@FXML
	private Button mainMenuButton;

	@FXML
	protected void mainMenu() {
		try {
			writeSettings();

			Stage primaryStage = (Stage) mainMenuButton.getScene().getWindow();

			GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("MainMenuFXML.fxml"));

			Scene scene = new Scene(root, 400, 350);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Reversi");
			primaryStage.setScene(scene);

			primaryStage.show();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override

	public void initialize(URL location, ResourceBundle resources) {

		for (int j = 4; j < 21; j++) {
			rowBox.getItems().add(j);
			colBox.getItems().add(j);
		}

		parseSettingsFile();

	}

	public void writeSettings() {

		try {
			File file = new File("settings.txt");

			if (!file.exists())
				file.createNewFile();

			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(rowBox.getValue().toString());
			bufferedWriter.newLine();
			bufferedWriter.write(colBox.getValue().toString());
			bufferedWriter.newLine();

			bufferedWriter.write(Player1Color.getValue().toString());
			bufferedWriter.newLine();

			bufferedWriter.write(Player2Color.getValue().toString());

			bufferedWriter.close();
		} catch (Exception e) {
			System.out.println("Can't write settings to file!");
		}
	}

	public void parseSettingsFile() {
		try {
			File file = new File("settings.txt");
			if (!file.exists()) {
				writeDefaultValues();
				return;
			}

			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			for (int i = 0; i < LINES; i++) {
				line = bufferedReader.readLine();
				if (i == 0)
					this.rowBox.setValue(Integer.parseInt(line));

				if (i == 1)
					this.colBox.setValue(Integer.parseInt(line));

				if (i == 2) {
					Color c = Color.web(line);
					this.Player1Color.setValue(c);
				}

				if (i == 3) {
					Color c = Color.web(line);
					this.Player2Color.setValue(c);
				}
			}

			fileReader.close();
		} catch (Exception e) {
			System.out.println("Can't parse settings from file, using the default settings");

			// using the default values
			writeDefaultValues();
		}
	}

	private void writeDefaultValues() {
		this.rowBox.setValue(8);
		this.colBox.setValue(8);
		this.Player1Color.setValue(Color.PINK);
		this.Player2Color.setValue(Color.CYAN);
	}

}
