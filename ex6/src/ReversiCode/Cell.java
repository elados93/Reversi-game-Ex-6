package ReversiCode;

import GeneralDef.Owner;

public class Cell {
	private Owner symbol;
	private boolean isActive;

	public Cell() {
		this.symbol = Owner.NONE;
		this.isActive = false;
	}

	public boolean isCellActive() {
		return this.isActive;
	}

	public Owner getSymbol() {
		return symbol;
	}

	public void setSymbol(Owner symbol) {
		if (symbol != Owner.NONE) {
			this.isActive = true;
		}
		this.symbol = symbol;
	}

	public Cell(Cell cell) {
		this.symbol = cell.symbol;
		this.isActive = cell.isActive;
	}

}
