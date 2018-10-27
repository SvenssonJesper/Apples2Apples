package tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import cards.*;
import player.*;


public class DeckTest {
	
	@Test
    public void createGreenDeck()
    {
		String filename = "greenApples.txt";
//		create deck with DeckFactory
		DeckFactory testFac = new DeckFactory();
		Deck<GreenCard> deck = testFac.createGreenDeck(filename);
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
		DeckFactory testFac = new DeckFactory();
		ByteArrayInputStream in = new ByteArrayInputStream("greenApples.txt".getBytes());
		System.setIn(in);
		System.setIn(System.in);
		Deck<GreenCard> deck = testFac.createGreenDeck(filename);
	}
	
	

	
	@Test
    public void createRedDeck()
    {
		String filename = "redApples.txt";
//		create deck with DeckFactory
		DeckFactory testFac = new DeckFactory();
		Deck<RedCard> deck = testFac.createRedDeck(filename);
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
		DeckFactory testFac = new DeckFactory();
		Deck<GreenCard> deck = testFac.createGreenDeck("greenApples.txt");
//		get first card in the green apples file
		assertEquals("[Absurd] - (ridiculous, senseless, foolish) ", deck.popCard().toString());
		assertEquals("[Abundant] - (plentiful, ample, numerous) ", deck.popCard().toString());
		assertEquals("[Addictive] - (obsessive, consuming, captivating) ", deck.popCard().toString());
		
	}
	
	@Test
	public void testBotHand(){
		DeckFactory testFac = new DeckFactory();
		Deck<GreenCard> deck = testFac.createGreenDeck("greenApples.txt");
		Hand hand = new Hand();
		Bot bot = new Bot(1, hand);
		bot.addCardToHand(deck.popCard());
		bot.addCardToHand(deck.popCard());
		bot.addCardToHand(deck.popCard());
		assertEquals("[Addictive] - (obsessive, consuming, captivating) ", bot.playCard(2).toString());
	}
}


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
