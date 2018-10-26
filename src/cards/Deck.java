package cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck<CardType> {
	private ArrayList<CardType> deck;
	public Deck() {
		this.deck = new ArrayList<CardType>();
	}
	
	 void add(CardType card) {
		this.deck.add(card);
	}
	
	public void shuffle() {
		//shuffles an ArrayList with 
		Collections.shuffle(this.deck);
	}
	
	public CardType popCard(){
		return deck.remove(0);
	}
	
	public int size() {
		return deck.size();
	}
}
