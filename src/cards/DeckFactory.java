package cards;

/**
* Help object for creating decks.
* @author Jesper Svensson
*
*/

import java.util.ArrayList;

public class DeckFactory {
	private CardFactory cardFac;
	
	public DeckFactory() {
		this.cardFac = new CardFactory();
	}
	/**
	 * Creates a deck of red cards
	 * @param redCards an ArrayList with card text on each line.
	 * @return Deck<RedCard>
	 */
	public Deck<RedCard> createRedDeck(ArrayList<String> redCards) {
		Deck<RedCard> deck = new Deck<RedCard>();
		for(int i = 0; i < redCards.size(); i++){
			deck.add(cardFac.createRedCard(redCards.get(i)));  
		}
		return deck;
	}
	
	/**
	 * Creates a deck of green cards
	 * @param greenCards an ArrayList with card text on each line.
	 * @return Deck<RedCard>
	 */
	public Deck<GreenCard> createGreenDeck(ArrayList<String> greenCards){
		Deck<GreenCard> deck = new Deck<GreenCard>();
		for(int i = 0; i < greenCards.size(); i++){
			deck.add(cardFac.createGreenCard(greenCards.get(i)));  
		}
		return deck;
	}
}
