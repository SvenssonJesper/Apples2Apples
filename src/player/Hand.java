package player;

import java.util.ArrayList;

import cards.Card;

/**
 * Help object that hold red cards for the players.
 * @author Jesper Svensson
 *
 */

public class Hand {
	private ArrayList<Card> hand;
	/**
	 * Constructor of Hand. Takes no arguments.
	 */
	public Hand () {
		this.hand = new ArrayList<Card>();
	}
	/**
	 * 
	 * @param card the card that should be added to the hand.
	 */
	public void add(Card card) {
		this.hand.add(card);
	}
	/**
	 * 
	 * @return the size of the hand.
	 */
	public int getSize() {
		return hand.size();
	}
	/**
	 * Removes the card with index index from hand and returns the card.
	 * @param index of the card you want to play.
	 * @return the card that was played.
	 */
	public Card playCard(int index) {
		return this.hand.remove(index);
	}
	/**
	 * @return a string with index and text to each card in hand. The cards are separated with a new line. 
	 */
	public String toString(){
		String temp = "";
		int counter = 0;
		for (Card card : this.hand) {
			temp = temp + "[" + counter + "]" + card.toString() + "\n"; 
			counter++;
		}
		return temp;
	}
}
