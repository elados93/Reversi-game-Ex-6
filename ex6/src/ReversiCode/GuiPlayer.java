package ReversiCode;

import GeneralDef.Owner;
import Reversi.ClickListener;

public class GuiPlayer implements Player {

	private String color; // TODO maybe to delete...#@
	private Owner owner;
	private ClickListener clickListener;
	
	
	public GuiPlayer(String color, GeneralDef.Owner owner, ClickListener clickListener) {
		super();
		this.color = color;
		this.owner = owner;
		this.clickListener = clickListener;
	}

	@Override
	public String getSymbol() {
		return color;
	}

	@Override
	public Point getMove(GameState gameState) {
		while (true) {
			if (clickListener.isPressed())
				break;
		}
		return clickListener.getLastClick();
	}

	public Owner getOwner() {
		return owner;
	}

}
