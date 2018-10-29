package player;

import java.util.ArrayList;

import cards.Card;

public class Hand {
	private ArrayList<Card> hand;
	public Hand () {
		this.hand = new ArrayList<Card>();
	}
	
	public void add(Card card) {
		this.hand.add(card);
	}
	
	public int getSize() {
		return hand.size();
	}
	
	public Card playCard(int index) {
		return this.hand.remove(index);
	}
	
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
