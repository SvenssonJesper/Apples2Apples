package player;

import java.util.ArrayList;

import cards.Card;
import cards.GreenCard;

public abstract class Player {
	protected Hand hand;
	protected ArrayList<GreenCard> points;
	protected int id;
	public Player(int id) {
		this.id = id;
		points = new ArrayList<GreenCard>();
		this.hand = new Hand();
	}
	
	public void addCardToHand(Card card) {
		this.hand.add(card);
	}
	
	public Card playCard(int index) {
		return this.hand.playCard(index);
	}
	
	public int getHandSize() {
		return this.hand.getSize();
	}
	
	public void addPoint(GreenCard card) {
		this.points.add(card);
	}
	
	public int getPoints() {
		return this.points.size();
	}
	
	public int getPlayerId() {
		return this.id;
	}
	
	public String getAllCardsTextInHand(){
		return this.hand.toString();
	}
	
	public boolean isHuman() {
		return true;
	}
}
