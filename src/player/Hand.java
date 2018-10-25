package player;

import java.util.ArrayList;

import cards.Card;

public class Hand {
	private ArrayList<Card> hand;
	Hand () {
		this.hand = new ArrayList<Card>();
	}
	
	void add(Card card) {
		this.hand.add(card);
	}
	
	int getSize() {
		return hand.size();
	}
	
	Card playCard(int index) {
		return this.hand.remove(index);
	}
	
	ArrayList<String> getAllCardsText(){
		ArrayList<String> temp = new ArrayList<String>();
		for (Card card : this.hand) {
			temp.add(card.getText()); 
		}
		return temp;
	}
}
