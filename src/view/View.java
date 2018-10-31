package view;

import cards.Card;
import player.Player;

public class View {
	public View() {

	}
	
	public String newRound(boolean judge) {
		String header = "*****************************************************";
		if(judge) {
			 header = header + "\n**                 NEW ROUND - JUDGE               **";
		}else {
			header = header + "\n**                    NEW ROUND                    **";
		}
		header = header + "\n*****************************************************";
		return header;
	}
	
	public String roundWinnerMessage(Player player, Card card) {
		return getBotOrPlayerText(player) + player.getPlayerId() + " won with: " + card.toString();
	}
	
	public String winnerMessage(Player player) {
		return getBotOrPlayerText(player) + player.getPlayerId() + " won the game!! \nCongratulations! Here is your prize: \n" + trophy();
	}
	
	private String getBotOrPlayerText(Player player) {
		if(player.isHuman()) {
			return "Player ID";
		}else {
			return "Bot ID";
		}
	}
	
	private String trophy() {
		return			"             ___________\r\n" + 
						"            '._==_==_=_.'\r\n" + 
						"            .-\\:      /-.\r\n" + 
						"           | (|:.     |) |\r\n" + 
						"            '-|:.     |-'\r\n" + 
						"              \\::.    /\r\n" + 
						"               '::. .'\r\n" + 
						"                 ) (\r\n" + 
						"               _.' '._\r\n" + 
						"              `\"\"\"\"\"\"\"`";
	}
}
