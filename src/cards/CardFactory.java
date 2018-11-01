package cards;

/**
* Help object for creating cards.
* @author Jesper Svensson
*
*/

public class CardFactory {
	/**
	 * Constructor of CardFactory
	 */
	public CardFactory() {
		
	}
	/**
	 * Creates a red card
	 * @param text is the card text.
	 * @return the new red card.
	 */
	public RedCard createRedCard(String text) {
		return new RedCard(text);
	}
	/**
	 * Creates a green card
	 * @param text is the card text.
	 * @return the new green card.
	 */
	public GreenCard createGreenCard(String text) {
		return new GreenCard(text);
	}
	
	
}
