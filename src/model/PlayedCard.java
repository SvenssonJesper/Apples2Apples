package model;


import cards.Card;
import player.Player;

public class PlayedCard {
	private Card playedCards;
	private Player player;
	
	public PlayedCard(Card card, Player player) {
		this.playedCards = card;
		this.player = player;
	}
	
	public Card getCard() {
		return this.playedCards;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
}
