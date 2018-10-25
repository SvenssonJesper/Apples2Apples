package cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> deck;
	public Deck() {
		this.deck = new ArrayList<Card>();
	}
	
	 void add(Card card) {
		this.deck.add(card);
	}
	
	public void shuffle() {
		//shuffles an ArrayList with 
		Collections.shuffle(this.deck);
	}
	
	public Card popCard() {
		return deck.remove(0);
		
	}
}
