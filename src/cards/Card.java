package cards;

/**
* Abstract class for creating cards.
* @author Jesper Svensson
*
*/

public abstract class Card {
	protected String text = "";
	/**
	 * Constructor of Card.
	 * @param text is the card text.
	 */
	public Card(String text) {
		this.text = text;
	}
	/**
	 * @return the card text as a String.
	 */
	public String toString() {
		return this.text;
	}
}
