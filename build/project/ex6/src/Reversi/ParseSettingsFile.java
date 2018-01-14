package Reversi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javafx.scene.paint.Color;

public class ParseSettingsFile {

	private static final int LINES = 5;

	private String Player1Color;
	private String Player2Color;
	private Integer rowBox;
	private Integer colBox;
	private String firstPlayer;

	public ParseSettingsFile() {
	}

	/**
	 * creates a new file if not exists and than translate the details from the
	 * file the the parameters. it translate the row a column values, the colors
	 * of each players and the starting player.
	 */
	public void parseSettingsFile() {
		try {
			File file = new File("settings.txt");
			// checks if there is allready a file
			if (!file.exists()) {
				file.createNewFile();
				// in case there was no file write one with default values.
				writeDefaultValues();
				writeSettings(rowBox, colBox, Player1Color, Player2Color, firstPlayer);
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

				if (i == 4)
					this.firstPlayer = line;

			}

			fileReader.close();
		} catch (Exception e) {
			System.out.println("Can't parse settings from file, using the default settings");
			// using the default values
			writeDefaultValues();
		}
	}

	/**
	 * writing the parameters of the setting to a file.
	 * 
	 * @param row
	 *            is the numbers of row in the board
	 * @param col
	 *            is the number of column in the board
	 * @param p1
	 *            is the color of the #1 player
	 * @param p2
	 *            is the number of the second player
	 * @param firstPlayer
	 *            is the player that will start
	 */
	public void writeSettings(Integer row, Integer col, String p1, String p2, String firstPlayer) {

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
			bufferedWriter.newLine();

			bufferedWriter.write(firstPlayer);

			bufferedWriter.close();
		} catch (Exception e) {
			System.out.println("Can't write settings to file!");
		}
	}

	/**
	 * in case the player didnt change the values we create a default settings
	 * so the game will start with it
	 */
	private void writeDefaultValues() {
		this.rowBox = 8;
		this.colBox = 8;
		this.Player1Color = Color.BLACK.toString();
		this.Player2Color = Color.WHITE.toString();
		this.firstPlayer = "Player 1";
	}

	/**
	 * get the first player
	 * @return the first player
	 */
	public String getFirstPlayer() {
		return firstPlayer;
	}

	/**
	 * get p1 color
	 * @return the color of p1
	 */
	public String getPlayer1Color() {
		return Player1Color;
	}

	/**
	 * get p2 color
	 * @return the color of p2
	 */
	public String getPlayer2Color() {
		return Player2Color;
	}

	/**
	 * number of rows
	 * @return the number of rows
	 */
	public Integer getRowBox() {
		return rowBox;
	}

	/**
	 * number of columns
	 * @return the number of columns
	 */
	public Integer getColBox() {
		return colBox;
	}
}
