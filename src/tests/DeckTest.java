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
import player.Bot;

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
			assertEquals(temp.get(i), deck.popCard().getText());
		}
    }
	
	@Test
	public void incorrectFileName() {
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
			assertEquals(temp.get(i), deck.popCard().getText());
		}
    }
	@Test
	public void testPopCard() {
		DeckFactory testFac = new DeckFactory();
		Deck<GreenCard> deck = testFac.createGreenDeck("greenApples.txt");
//		get first card in the green apples file
		assertEquals("[Absurd] - (ridiculous, senseless, foolish) ", deck.popCard().getText());
		assertEquals("[Abundant] - (plentiful, ample, numerous) ", deck.popCard().getText());
		assertEquals("[Addictive] - (obsessive, consuming, captivating) ", deck.popCard().getText());
		
	}
	
	@Test
	public void testBotHand(){
		DeckFactory testFac = new DeckFactory();
		Deck<GreenCard> deck = testFac.createGreenDeck("greenApples.txt");
		Bot bot = new Bot(1);
		bot.addCardToHand(deck.popCard());
		bot.addCardToHand(deck.popCard());
		bot.addCardToHand(deck.popCard());
		assertEquals("[Addictive] - (obsessive, consuming, captivating) ", bot.playCard(2).getText());
	}
}
