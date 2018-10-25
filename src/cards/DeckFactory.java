package cards;

import java.util.ArrayList;

public class DeckFactory {
	private InputFileHandeler inputFileHandeler;
	private CardFactory cardFac;
	
	public DeckFactory() {
		this.inputFileHandeler = new InputFileHandeler();
		this.cardFac = new CardFactory();
	}
	/**
	 * Creates a deck for red cards
	 * @return a deck
	 */
	public Deck<RedCard> createRedDeck(String redApplesFile) {
		Deck<RedCard> deck = new Deck<RedCard>();
		//load an ArrayList with Strings from the assigned file and loads them to the Card Arrays as Cards.
		ArrayList<String> temp = this.inputFileHandeler.scan(redApplesFile);
		for(int i = 0; i < temp.size(); i++){
			deck.add(cardFac.createRedCard(temp.get(i)));  
		}
		return deck;
	}
	
	/**
	 * Creates a deck for green cards
	 * @return a deck
	 */
	public Deck<GreenCard> createGreenDeck(String greenApplesFile) {
		Deck<GreenCard> deck = new Deck<GreenCard>();
		//load an ArrayList with Strings from the assigned file and loads them to the Card Arrays as Cards.
		ArrayList<String> temp = this.inputFileHandeler.scan(greenApplesFile);
		for(int i = 0; i < temp.size(); i++){
			deck.add(cardFac.createGreenCard(temp.get(i)));  
		}
		return deck;
	}
	
}
