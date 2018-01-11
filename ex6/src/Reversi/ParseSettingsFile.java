package Reversi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javafx.scene.paint.Color;

public class ParseSettingsFile {

	private static final int LINES = 4;

	private String Player1Color;
	private String Player2Color;
	private Integer rowBox;
	private Integer colBox;

	public ParseSettingsFile() {
	}

	public void parseSettingsFile() {
		try {
			File file = new File("settings.txt");
			if (!file.exists()) {
				file.createNewFile();
				writeDefaultValues();
				writeSettings(rowBox, colBox, Player1Color, Player2Color);
				return;
			}

			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			for (int i = 0; i < LINES; i++) {
				line = bufferedReader.readLine();
				if (i == 0)
					this.rowBox = Integer.parseInt(line);

				if (i == 1)
					this.colBox = Integer.parseInt(line);

				if (i == 2) 
					this.Player1Color = line;
				

				if (i == 3) 
					this.Player2Color = line;
				
			}

			fileReader.close();
		} catch (Exception e) {
			System.out.println("Can't parse settings from file, using the default settings");

			// using the default values
			writeDefaultValues();
		}
	}
	
	public void writeSettings(Integer row, Integer col, String p1, String p2) {

		try {
			File file = new File("settings.txt");

			if (!file.exists())
				file.createNewFile();

			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(row.toString());
			bufferedWriter.newLine();
			bufferedWriter.write(col.toString());
			bufferedWriter.newLine();

			bufferedWriter.write(p1);
			bufferedWriter.newLine();

			bufferedWriter.write(p2);

			bufferedWriter.close();
		} catch (Exception e) {
			System.out.println("Can't write settings to file!");
		}
	}

	private void writeDefaultValues() {
		this.rowBox = 8;
		this.colBox = 8;
		this.Player1Color = Color.BLACK.toString();
		this.Player2Color = Color.WHITE.toString();
	}

	
	public String getPlayer1Color() {
		return Player1Color;
	}
	
	public String getPlayer2Color() {
		return Player2Color;
	}
	
	public Integer getRowBox() {
		return rowBox;
	}

	public Integer getColBox() {
		return colBox;
	}
}
