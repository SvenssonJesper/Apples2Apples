import java.io.IOException;

import cards.*;
import controller.Game;
import input.InputFileHandeler;
import model.Model;
import server.Server;
import view.View;

public class ServerMain {

	public static void main(String[] args) {
//		Default options that will be set if no arguments are provided.
		String greenApplesFile = "greenApples.txt";
		String redApplesFile = "redApples.txt";
		int numberOfClients = 1;
		int port = 4545;
//		The switch will set options depending on how many arguments were provided
		switch(args.length) {
			case 2:
//				Checks that the input is numbers larger than 0 if they are use them, otherwise use the default options
				if (args[0].matches("-?\\d+") && args[1].matches("-?\\d+")
					&& Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0) {
					numberOfClients = Integer.parseInt(args[0]);
					port = Integer.parseInt(args[1]);
				}else {
					System.out.println("The number of clients and the portnumber must be integers larger than 0");
					System.out.println("Starting server with default parameters");
				}
				break;
			case 4:
//				Checks that the input is numbers larger than 0 if they are use them and the provided text files, otherwise use the default options
				if (args[0].matches("-?\\d+") && args[1].matches("-?\\d+")
						&& Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0) {
					numberOfClients = Integer.parseInt(args[0]);
					port = Integer.parseInt(args[1]);
					greenApplesFile = args[2];
					redApplesFile = args[3];
				}else {
					System.out.println("The number of clients and the portnumber must be integers larger than 0");
					System.out.println("Starting server with default parameters");
				}
				break;
			default:
				System.out.println("Starting server with default parameters");
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
