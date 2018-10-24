package cards;

import java.util.ArrayList;
import java.util.Collections;

public class CardFactory {
	private String redApplesFile, greenApplesFile;
	private InputFileHandeler inputFileHandeler;
	private ArrayList<Card> redCardsDeck, greenCardsDeck;
	public CardFactory(String redApplesFile, String greenApplesFile) {
		this.redApplesFile = redApplesFile;
		this.greenApplesFile = greenApplesFile;
		this.inputFileHandeler = new InputFileHandeler();
		this.redCardsDeck = new ArrayList<Card>();
		this.greenCardsDeck = new ArrayList<Card>();
	}
	/**
	 * Creates a deck for red and green cards and shuffle them
	 */
	public void createSuffledDecks() {
		this.createUnShuffledDecks();
		this.redCardsDeck = shuffle(this.redCardsDeck);
		this.greenCardsDeck = shuffle(this.greenCardsDeck);
	}
	
	private ArrayList<Card> shuffle(ArrayList<Card> deck) {
		//shuffles an ArrayList with 
		Collections.shuffle(deck);
		return deck;
	}
	
	private void createUnShuffledDecks(){
		//load an ArrayList with Strings from the assigned file and loads them to the Card Arrays as Cards.
		ArrayList<String> temp = this.inputFileHandeler.scan(this.redApplesFile);
		for(int i = 0; i < temp.size(); i++){
			this.redCardsDeck.add(new RedCard(temp.get(i),i));  
		}
		temp = this.inputFileHandeler.scan(this.greenApplesFile);
		for(int i = 0; i < temp.size(); i++){
			this.greenCardsDeck.add(new GreenCard(temp.get(i),i));  
		}
	}
	/**
	 * @return      an ArrayList with the redCards. 
	 */
	public ArrayList<Card> getRedCardsDeck() {
		return redCardsDeck;
	}
	/**
	 * @return      an ArrayList with the greenCards. 
	 */
	public ArrayList<Card> getGreenCardsDeck() {
		return greenCardsDeck;
	}
	
	
}
