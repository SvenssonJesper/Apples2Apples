package view;

import cards.Card;
import cards.GreenCard;
import player.Player;

/**
 * The view returns processed Strings to send to clients.
 * @author Jesper Svensson
 *
 */

public class View {
	/**
	 * Constructor of View. The view handles all Strings that should be sent to the clients.
	 */
	public View() {

	}
	/**
	 * 
	 * @param judge notes if the message should be for the judge or not.
	 * @return the new round visuals message as a String.
	 */
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
	/**
	 * 
	 * @param roundWinner the player that won the round.
	 * @param card the card the player won with.
	 * @return the message that should be sent to clients as a String.
	 */
	public String roundWinnerMessage(Player roundWinner, Card card) {
		return getBotOrPlayerText(roundWinner) + roundWinner.getPlayerId() + " won with: " + card.toString();
	}
	/**
	 * 
	 * @param winner the player that won the game.
	 * @return a winner message as a String.
	 */
	public String winnerMessage(Player winner) {
		return getBotOrPlayerText(winner) + winner.getPlayerId() + " won the game!! \nCongratulations! \nHere is " +
				getBotOrPlayerText(winner) + winner.getPlayerId() + "s prize: \n" + trophy();
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
	/**
	 * 
	 * @param player that should receive the hand.
	 * @return the hand of the player as String.
	 */
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
	/**
	 *
	 * @param player that you want to send the points to
	 * @return a message with the points in as a String.
	 */
	public String sendPoints(Player player) {
		return "You have " + player.getPoints() + " points.";
	}

	
}
