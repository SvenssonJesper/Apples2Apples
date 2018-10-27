import java.io.IOException;
import java.util.ArrayList;

import cards.*;
import model.Model;
import network.Server;
import player.*;

public class main {

	public static void main(String[] args) {		
		DeckFactory testFac = new DeckFactory();
		Deck<GreenCard> greenDeck = testFac.createGreenDeck("greenApples.txt");
		greenDeck.shuffle();
		Deck<RedCard> redDeck = testFac.createRedDeck("redApples.txt");
		redDeck.shuffle();
		Model model = new Model(redDeck, greenDeck);
		System.out.println("so far so good");
		try {
			Server server = new Server(1, model, 4545);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("done");
//		players.add(((Player) bot0));
//		Model model = new Model();

	}
	
}
