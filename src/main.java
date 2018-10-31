import java.io.IOException;

import cards.*;
import controller.Game;
import model.Model;
import network.Server;
import view.View;

public class main {

	public static void main(String[] args) {
		String greenApplesFile = "greenApples.txt";
		String redApplesFile = "redApples.txt";
		int numberOfClients = 1;
		int port = 4545;
		switch(args.length) {
			case 2:
				numberOfClients = Integer.parseInt(args[0]);
				port = Integer.parseInt(args[1]);
				break;
			case 4:			
				greenApplesFile = args[2];
				redApplesFile = args[3];
				numberOfClients = Integer.parseInt(args[0]);
				port = Integer.parseInt(args[1]);
				break;
			default:
		}
		View view = new View();
		Model model = setupModel(greenApplesFile, redApplesFile);
		Server server = startServer(numberOfClients, model, port);
		Game game = new Game(model, view, server);
		game.init();
		game.run();
	}
	
	private static Model setupModel(String greenDeckFile, String redDeckFile) {
		InputFileHandeler inputHandeler = new InputFileHandeler();
		DeckFactory testFac = new DeckFactory();
		Deck<GreenCard> greenDeck = testFac.createGreenDeck(inputHandeler.scan(greenDeckFile));
		greenDeck.shuffle();
		Deck<RedCard> redDeck = testFac.createRedDeck(inputHandeler.scan(redDeckFile));
		redDeck.shuffle();
		return new Model(redDeck, greenDeck);
	}
	
	private static Server startServer(int numberOfClients, Model model ,int port) {
		try {
			return new Server(numberOfClients, model, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
