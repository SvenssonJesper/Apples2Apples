package player;

import java.util.ArrayList;

import cards.Card;
import cards.GreenCard;

/**
 * Abstract class for creating players.
 * @author Jesper Svensson
 *
 */

public abstract class Player {
	protected Hand hand;
	protected ArrayList<GreenCard> points;
	protected int id;
	protected boolean isHuman;
	public Player(int id) {
		this.id = id;
		points = new ArrayList<GreenCard>();
		this.hand = new Hand();
	}
	/**
	 * adds the provided card to the players hand.
	 * @param card the card that should be added to hand.
	 */
	public void addCardToHand(Card card) {
		this.hand.add(card);
	}
	/**
	 * Removes the card with index index from the players hand and returns the card.
	 * @param index of the card you want to play from the players hand.
	 * @return the card that was played.
	 */
	
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
	/**
	 * 
	 * @return a string with index and text to each card in hand. The cards are separated with a new line. 
	 */
	public String getAllCardsTextInHand(){
		return this.hand.toString();
	}
	
	public boolean isHuman() {
		return isHuman;
	}
}
