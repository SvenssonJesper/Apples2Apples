package cards;

/**
* Deck of Cards. Deck is generic typed.
* @author Jesper Svensson
*
*/

import java.util.ArrayList;
import java.util.Collections;

public class Deck<CardType> {
	private ArrayList<CardType> deck;
	/**
	 * Constructor of Deck. Note that deck is generic typed.
	 */
	public Deck() {
		this.deck = new ArrayList<CardType>();
	}
	/**
	 * Adds a card to the deck.
	 * @param card the card that should be added to the deck.
	 */
	void add(CardType card) {
		this.deck.add(card);
	}
	/**
	 * Shuffles the deck with help of Collections.shuffle
	 */
	public void shuffle() {
		//shuffles an ArrayList with 
		Collections.shuffle(this.deck);
	}
	/**
	 * Pops a card from the deck.
	 * @return the first card in the deck.
	 */
	public CardType popCard(){
		return deck.remove(0);
	}
	/**
	 * 
	 * @return the size of the deck.
	 */
	public int size() {
		return deck.size();
	}
}
