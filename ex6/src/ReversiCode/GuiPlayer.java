package ReversiCode;

import GeneralDef.Owner;

public class GuiPlayer implements Player {

	private String color; // TODO maybe to delete...#@
	private Owner owner;
	
	
	public GuiPlayer(String color, GeneralDef.Owner owner) {
		super();
		this.color = color;
		this.owner = owner;
	}

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

	public Owner getOwner() {
		return owner;
	}

}
