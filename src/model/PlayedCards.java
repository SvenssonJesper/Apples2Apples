package model;

import java.util.ArrayList;

import cards.RedCard;
import player.Player;

public class PlayedCards {
	private ArrayList<RedCard> playedCards;
	private ArrayList<Player> player;
	
	public PlayedCards() {
		this.playedCards = new ArrayList<RedCard>();
		this.player = new ArrayList<Player>();
	}
}
