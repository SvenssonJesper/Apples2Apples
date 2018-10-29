package model;

import java.util.ArrayList;
import java.util.Collections;

import cards.*;
import player.*;

public class Model {
	private Deck redDeck, greenDeck;
	private ArrayList<Player> players;
	private Player judge;
	private int maxHandSize = 7;
	public Model(Deck redDeck, Deck greenDeck) {
		this.redDeck = redDeck;
		this.greenDeck = greenDeck;
		this.players = new ArrayList<Player>();
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public RedCard popRedCard() {
		return (RedCard) redDeck.popCard();
	}
	
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	public void dealRedCards() {
		for(Player player:this.players) {
			while(player.getHandSize() < this.maxHandSize) {
				player.addCardToHand(popRedCard());
			}
		}
	}
	
	public GreenCard popGreenCard() {
		return (GreenCard) greenDeck.popCard();
	}
	
}
