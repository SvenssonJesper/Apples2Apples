import cards.*;
import player.*;

public class main {

	public static void main(String[] args) {		
		DeckFactory testFac = new DeckFactory();
		Deck<GreenCard> deck = testFac.createGreenDeck("greenApples.txt");
		deck.shuffle();
		Bot bot = new Bot(1);
		bot.addCardToHand(deck.popCard());
		bot.addCardToHand(deck.popCard());
		bot.addCardToHand(deck.popCard());
		System.out.println(bot.playCard(2).getText());
//		System.out.println(bot.getPoints());
//		System.out.println(deck.popCard().getClass().getName());
	}

}
