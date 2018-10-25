import cards.*;

public class main {

	public static void main(String[] args) {		
		DeckFactory testFac = new DeckFactory();
		Deck deck = testFac.createRedDeck("redApples.txt");
		deck.shuffle();
		System.out.println(deck.popCard().getText());
	}

}
