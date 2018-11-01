package tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import cards.*;
import client.Client;
import controller.Game;
import input.InputFileHandeler;
import model.Model;
import player.*;
import server.Server;
import view.View;


public class UnitTest {
	
	@Test
    public void createGreenDeck()
    {
		String filename = "greenApples.txt";
//		create deck with DeckFactory
		InputFileHandeler inputHandeler = new InputFileHandeler();
		DeckFactory testFac = new DeckFactory();
		Deck<GreenCard> deck = testFac.createGreenDeck(inputHandeler.scan(filename));
		ArrayList<String> temp = new ArrayList<String>();
//		load all Strings from filename into list ArrayList
		try {
			File file = new File(filename);				
	        Scanner sc = new Scanner(file);
	        
	        while (sc.hasNextLine()) {
	            temp.add(sc.nextLine());
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    }
		
		int lenght = (temp.size()>deck.size())?deck.size():temp.size();
		for(int i = 0; i < lenght; i++) {
			assertEquals(temp.get(i), deck.popCard().toString());
		}
    }
	
	@Test
	public void incorrectGreenFileName() {
		String filename = "noFile.txt";
//		create deck with DeckFactory
		InputFileHandeler inputHandeler = new InputFileHandeler();
		DeckFactory testFac = new DeckFactory();
		ByteArrayInputStream in = new ByteArrayInputStream("greenApples.txt".getBytes());
		System.setIn(in);
		System.setIn(System.in);
		Deck<GreenCard> deck = testFac.createGreenDeck(inputHandeler.scan(filename));
	}
	
	

	
	@Test
    public void createRedDeck()
    {
		String filename = "redApples.txt";
//		create deck with DeckFactory
		InputFileHandeler inputHandeler = new InputFileHandeler();
		DeckFactory testFac = new DeckFactory();
		Deck<RedCard> deck = testFac.createRedDeck(inputHandeler.scan(filename));
		ArrayList<String> temp = new ArrayList<String>();
//		load all Strings from filename into list ArrayList
		try {
			File file = new File(filename);				
	        Scanner sc = new Scanner(file);
	        
	        while (sc.hasNextLine()) {
	            temp.add(sc.nextLine());
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    }
		
		int lenght = (temp.size()>deck.size())?deck.size():temp.size();
		for(int i = 0; i < lenght; i++) {
			assertEquals(temp.get(i), deck.popCard().toString());
		}
    }
	@Test
	public void testPopCard() {
		String filename = "greenApples.txt";
		InputFileHandeler inputHandeler = new InputFileHandeler();
		DeckFactory testFac = new DeckFactory();
		Deck<GreenCard> deck = testFac.createGreenDeck(inputHandeler.scan(filename));
//		get first card in the green apples file
		assertEquals("[Absurd] - (ridiculous, senseless, foolish) ", deck.popCard().toString());
		assertEquals("[Abundant] - (plentiful, ample, numerous) ", deck.popCard().toString());
		assertEquals("[Addictive] - (obsessive, consuming, captivating) ", deck.popCard().toString());
		
	}
	
	@Test
	public void testBotHand(){
		String filename = "greenApples.txt";
		InputFileHandeler inputHandeler = new InputFileHandeler();
		DeckFactory testFac = new DeckFactory();
		Deck<GreenCard> deck = testFac.createGreenDeck(inputHandeler.scan(filename));
		Bot bot = new Bot(1);
		bot.addCardToHand(deck.popCard());
		bot.addCardToHand(deck.popCard());
		bot.addCardToHand(deck.popCard());
		assertEquals("[Addictive] - (obsessive, consuming, captivating) ", bot.playCard(2).toString());
	}
	
	@Test
	public void dealCards() {
		Model model = setupModel("greenApples.txt", "redApples.txt");
		//adds 10 bots
		for(int i = 0; i <10; i++) {
			model.addPlayer(new Bot(i));
		}
		//deals cards to players
		model.dealRedCards();
		//goes through all players and compare models max hand size and players hand size
		for(Player player:model.getPlayers()) {
			assertEquals(model.getMaxHandSize(),player.getHandSize());
		}
	}
	
	@Test
	public void refillHandOfPlayers() {
		Model model = setupModel("greenApples.txt", "redApples.txt");
		//adds 10 bots
		for(int i = 0; i <10; i++) {
			model.addPlayer(new Bot(i));
		}
		//deals cards to players
		model.dealRedCards();
		//Makes every player play two cards
		for(Player player:model.getPlayers()) {
			player.playCard(0);
			player.playCard(0);
		}
		//checks if players have 2 less cards than max hand size
		for(Player player:model.getPlayers()) {
			assertEquals(model.getMaxHandSize()-2,player.getHandSize());
		}
		//deals new cards to players
		model.dealRedCards();
		//goes through all players and compare models max hand size and players hand size
		for(Player player:model.getPlayers()) {
			assertEquals(model.getMaxHandSize(),player.getHandSize());
		}
	}
	
	@Test
	public void runGameWithBots() {
		View view = new View();
		Model model = setupModel("greenApples.txt", "redApples.txt");
		Server server = null;
		try {
			server = new Server(0, model, 4545);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Game game = new Game(model, view, server);
		game.init();
		game.run();
	}
	
	@Test
	public void isWinnerWith4Players() {
		Model model = setupModel("greenApples.txt", "redApples.txt");
		//add 4 bots
		Bot bot = new Bot(0);
		model.addPlayer(bot);
		for(int i = 1; i < 4; i++) {
			model.addPlayer(new Bot(i));
		}

		//add points to bot, should have 8 points after loop
		for(int i = 0; i <8; i++) {
			//checks if bot is winner. Checks 0-7 which should return false
			assertEquals(false,model.isWinner(bot));
			model.addPoint(bot, model.popGreenCard());
		}
		//checks if bot is winner after the loop when bot has 8 points
		assertEquals(true,model.isWinner(bot));
	}
	
	@Test
	public void isWinnerWith6Players() {
		Model model = setupModel("greenApples.txt", "redApples.txt");
		//add 6 bots
		Bot bot = new Bot(0);
		model.addPlayer(bot);
		for(int i = 1; i < 6; i++) {
			model.addPlayer(new Bot(i));
		}

		//add points to bot, should have 6 points after loop
		for(int i = 0; i <6; i++) {
			//checks if bot is winner. Checks 0-5 which should return false
			assertEquals(false,model.isWinner(bot));
			model.addPoint(bot, model.popGreenCard());
		}
		//checks if bot is winner after the loop when bot has 6 points
		assertEquals(true,model.isWinner(bot));
	}
	

	@Test
	public void setUpServerAndConnectClients() throws IOException {
		int port = 4545;
		String localhost = "127.0.0.1";
		for(int numberOfClients = 1; numberOfClients<8; numberOfClients ++) {
			View view = new View();
			Model model = setupModel("greenApples.txt", "redApples.txt");
			Server server = new Server(numberOfClients, model, port);
			//connecting clients.		
			for(int i = 0; i < numberOfClients; i++) {			
				new Client(localhost,port).connect();			
			}
			server.connectToClients();
			
			Game game = new Game(model, view, server);
			game.init();
			server.close();
		}
	}
	

	private Model setupModel(String greenDeckFile, String redDeckFile) {
		InputFileHandeler inputHandeler = new InputFileHandeler();
		DeckFactory testFac = new DeckFactory();
		Deck<GreenCard> greenDeck = testFac.createGreenDeck(inputHandeler.scan(greenDeckFile));
		greenDeck.shuffle();
		Deck<RedCard> redDeck = testFac.createRedDeck(inputHandeler.scan(redDeckFile));
		redDeck.shuffle();
		return new Model(redDeck, greenDeck);
	}
}



//@Test
//public void runGameWithOneClient() throws IOException {
//	int port = 4545;
//	String localhost = "127.0.0.1";
//	View view = new View();
//	Model model = setupModel("greenApples.txt", "redApples.txt");
//	Server server = new Server(1, model, port);
//	//connecting client.		
//	Client client = new Client(localhost,port);
//	client.connect();
//	server.connectToClients();
//	Game game = new Game(model, view, server);
//	game.init();
//	//Thread here to simulate game.
////	game.run();
////	client.run();
//	}
//}

//Test with Thread not working atm.

//@Test
//public void incorrectRedFileName() {
//	String filename = "noFile.txt";
////	create deck with DeckFactory
//	DeckFactory testFac = new DeckFactory();
//	
//	final Thread createDeck = new Thread(new Runnable() {
//
//		public void run(){
//			String filename = "noFile.txt";
////			create deck with DeckFactory
//			DeckFactory testFac = new DeckFactory();
//			 Deck<RedCard> deck = testFac.createRedDeck(filename);
//		}
//	 });
//	final Thread generateInput = new Thread(new Runnable() {
//			public void run(){			
//				ByteArrayInputStream in = new ByteArrayInputStream("greenApples.txt".getBytes());
//				System.setIn(in);
//				System.setIn(System.in);
//		  }
//		});
//	
//	for(int i = 0; i < 10; i++) {
//		createDeck.start();
//		generateInput.start();
////		try {
////			createDeck.sleep(100);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		try {
////			generateInput.sleep(100);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}
//}
