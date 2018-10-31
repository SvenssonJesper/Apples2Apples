package view;

import cards.Card;
import cards.GreenCard;
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
	
	public String roundWinnerMessage(Player roundWinner, Card card) {
		return getBotOrPlayerText(roundWinner) + roundWinner.getPlayerId() + " won with: " + card.toString();
	}
	
	public String winnerMessage(Player player) {
		return getBotOrPlayerText(player) + player.getPlayerId() + " won the game!! \nCongratulations! Here is your prize: \n" + trophy();
	}
	
	public String nameMessage(Player player) {
		return "You are Player ID" + player.getPlayerId();
	}
	
	public String greenCardMessage(GreenCard greenCard) {
		return "Green apple: " + greenCard.toString();
	}
	
	public String playedCardsMessage(GreenCard currentGreenCard, String playedCardsToString) {
		return "The following apples were played:\n" + currentGreenCard.toString() + playedCardsToString;
		
	}
	
	public String sendHand(Player player) {
		return "Choose a red apple to play:\n" + player.getAllCardsTextInHand();
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

	public String sendPoints(Player player) {
		return "You have " + player.getPoints() + " points.";
	}

	
}
