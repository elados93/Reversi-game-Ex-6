package ReversiCode;
import java.util.Vector;

import GeneralDef.*;;

public interface Printer {

	void printBoard();

	void printNextPlayerMove(Player p, Vector<Point> v);

	void printLastMove(Player player, Point point);

	void printEndOfGame(Player p, Status status1);

	void printError(Possible_OutCome outcome);

	void printMenu();

	void printInformingGameStarted(Owner currentOwner);

	void printWaitingForOtherPlayer(Owner currentOwner);

	void printMessage(String s);
}
