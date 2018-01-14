package ReversiCode;

import GeneralDef.Owner;

public class GuiPlayer implements Player {

	private String color; // TODO maybe to delete...#@
	private Owner owner;
	
	/**
	 * constroctor
	 * @param color is the color of the gui player
	 * @param owner is the owner symbol
	 */
	public GuiPlayer(String color, GeneralDef.Owner owner) {
		super();
		this.color = color;
		this.owner = owner;
	}

	/**
	 * gets the symbol of the player.
	 */
	@Override
	public String getSymbol() {
		return color;
	}

	@Override
	public Point getMove(GameState gameState) {
/*		while (true) {
			if (clickListener.isPressed())
				break;
		}
		return clickListener.getLastClick();*/
		return null;
		// TODO what with player man?!
	}

	/**
	 * return the owner of the cell.
	 * @return the owner.
	 */
	public Owner getOwner() {
		return owner;
	}

}
