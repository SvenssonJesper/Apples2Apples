package model;


import cards.Card;
import player.Player;

/**
 * Help object for storing played cards and which player played them.
 * @author Jesper Svensson
 *
 */

public class PlayedCard {
	private Card playedCards;
	private Player player;
	
	/**
	 * Constructor of PlayedCard.
	 * @param card the card the player played.
	 * @param player the player that played the card.
	 */
	public PlayedCard(Card card, Player player) {
		this.playedCards = card;
		this.player = player;
	}
	/**
	 * 
	 * @return the card
	 */
	public Card getCard() {
		return this.playedCards;
	}
	/**
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		return this.player;
	}
	
}
