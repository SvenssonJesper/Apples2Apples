package cards;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
		try {
			ArrayList<String> temp = this.inputFileHandeler.scan(redApplesFile);
		
			for(int i = 0; i < temp.size(); i++){
				deck.add(cardFac.createRedCard(temp.get(i)));  
			}
		}catch(FileNotFoundException e) {
			System.out.println("cant find the file: " + redApplesFile + ".\nEnter an correct file:");
			String input = readInput();
			deck = createRedDeck(input);
		}
		return deck;
	}
	
	/**
	 * Creates a deck for green cards
	 * @return a deck
	 * @throws IOException 
	 */
	public Deck<GreenCard> createGreenDeck(String greenApplesFile){
		Deck<GreenCard> deck = new Deck<GreenCard>();
		//load an ArrayList with Strings from the assigned file and loads them to the Card Arrays as Cards.
		try {
			ArrayList<String> temp = this.inputFileHandeler.scan(greenApplesFile);
			for(int i = 0; i < temp.size(); i++){
				deck.add(cardFac.createGreenCard(temp.get(i)));  
			}
		}catch(FileNotFoundException e) {
			System.out.println("cant find the file: " + greenApplesFile + ".\nEnter an correct file:");
			String input = readInput();
			deck = createGreenDeck(input);
		}
		return deck;
	}
	
	private String readInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		try{
			input = br.readLine();
			
		}catch(IOException er){
			System.out.println("Bad input");
			input = readInput();
		}
		return input;
	}
	
}
