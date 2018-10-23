package cards;

import java.util.ArrayList;
import java.util.Collections;

public class CardFactory {
	private String redApplesFile, greenApplesFile;
	private InputFileHandeler inputFileHandeler;
	private ArrayList redCardsDeck, greenCardsDeck;
	public CardFactory(String redApplesFile, String greenApplesFile, InputFileHandeler inputFileHandeler) {
		this.redApplesFile = redApplesFile;
		this.greenApplesFile = greenApplesFile;
		this.inputFileHandeler = inputFileHandeler;
	}
	/**
	 * Creates a deck for red and green cards and shuffle them
	 */
	public void createDecks() {
		this.redCardsDeck = this.inputFileHandeler.scan(this.redApplesFile);
		this.greenCardsDeck = this.inputFileHandeler.scan(this.greenApplesFile);
		this.redCardsDeck = shuffle(this.redCardsDeck);
		this.greenCardsDeck = shuffle(this.greenCardsDeck);
	}
	
	private ArrayList shuffle(ArrayList deck) {
		//shuffles an ArrayList with 
		Collections.shuffle(deck);
		return deck;
	}
	/**
	 * @return      an ArrayList with the redCards. 
	 */
	public ArrayList getRedCardsDeck() {
		return redCardsDeck;
	}
	/**
	 * @return      an ArrayList with the greenCards. 
	 */
	public ArrayList getGreenCardsDeck() {
		return greenCardsDeck;
	}
	
	
}
