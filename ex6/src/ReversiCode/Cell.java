package ReversiCode;

import GeneralDef.Owner;

public class Cell {
	private Owner symbol;
	private String color;
	private boolean isActive;
	private boolean isPossibleOption;

	public Cell() {
		this.symbol = Owner.NONE;
		this.isActive = false;
		this.color = null;
		this.isPossibleOption = false;
	}
	
	public Cell(Cell cell) {
		this.symbol = cell.symbol;
		this.isActive = cell.isActive;
		this.color = cell.color;
	}

	public boolean isCellActive() {
		return this.isActive;
	}

	public Owner getSymbol() {
		return symbol;
	}
	
	public String getColor() {
		return color;
	}

	public void setSymbol(Owner symbol, String color) {
		if (symbol != Owner.NONE) {
			this.isActive = true;
		}
		this.symbol = symbol;
		this.color = color;
	}

	public boolean isPossibleOption() {
		return isPossibleOption;
	}

	public void setPossibleOption(boolean isPossibleOption) {
		this.isPossibleOption = isPossibleOption;
	}
	
}
