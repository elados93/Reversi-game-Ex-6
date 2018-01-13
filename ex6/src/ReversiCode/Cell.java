package ReversiCode;

import GeneralDef.Owner;

public class Cell {
	private Owner symbol;
	private String color;
	private boolean isActive;
	private boolean isPossibleOption;

	/**
	 * constroctor
	 */
	public Cell() {
		this.symbol = Owner.NONE;
		this.isActive = false;
		this.color = null;
		this.isPossibleOption = false;
	}

	/**
	 * copy constroctor
	 */
	public Cell(Cell cell) {
		this.symbol = cell.symbol;
		this.isActive = cell.isActive;
		this.color = cell.color;
	}

	/**
	 * return if the cell is active
	 * 
	 * @return
	 */
	public boolean isCellActive() {
		return this.isActive;
	}

	/**
	 * get the symbol of the player
	 * 
	 * @return the symbol of the player
	 */
	public Owner getSymbol() {
		return symbol;
	}

	/**
	 * get the color of the current player
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * set the symbol of the player
	 * @param symbol is the current owner
	 * @param color is the color of the owner
	 */
	public void setSymbol(Owner symbol, String color) {
		if (symbol != Owner.NONE) {
			this.isActive = true;
		}
		this.symbol = symbol;
		this.color = color;
	}

	/**
	 * checks if it an option for the current player to play
	 * @return true or false
	 */
	public boolean isPossibleOption() {
		return isPossibleOption;
	}

	/**
	 * if it not a possible option any more than set this cell as false.
	 * @param isPossibleOption is the answer if it is a possible option.
	 */
	public void setPossibleOption(boolean isPossibleOption) {
		this.isPossibleOption = isPossibleOption;
	}

}
