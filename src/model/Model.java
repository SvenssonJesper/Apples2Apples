package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import cards.*;
import player.*;

/**
* The model holds the state of the game and functions to update the state.
* @author Jesper Svensson
*
*/

public class Model {
	private Deck<RedCard> redDeck;
	private Deck<GreenCard> greenDeck;
	private ArrayList<Player> players;
	private int judgeIndex;
	private int maxHandSize = 7;
	private GreenCard currentGreenCard;
	private ArrayList<PlayedCard> playedCards;
	private Random rnd; 
	/**
	 * Constructor of Model
	 * @param redDeck a Deck of RedCards.
	 * @param greenDeck a Deck of GreenCards. 
	 */
	public Model(Deck<RedCard> redDeck, Deck<GreenCard> greenDeck) {
		this.redDeck = redDeck;
		this.greenDeck = greenDeck;
		this.players = new ArrayList<Player>();
		this.playedCards = new ArrayList<PlayedCard>();
		this.rnd = ThreadLocalRandom.current();
	}
	
	public int getMaxHandSize() {
		return maxHandSize;
	}
	/**
	 * Adds players to the state.
	 * @param player the player that should be added.
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public RedCard popRedCard() {
		return (RedCard) redDeck.popCard();
	}
	
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	/**
	 * Deal red cards to all players until the have cards equals to the maximum number of cards a hand should have.
	 */
	public void dealRedCards() {
		for(Player player:this.players) {
			while(player.getHandSize() < this.maxHandSize) {
				player.addCardToHand(popRedCard());
			}
		}
	}
	
	public Player getJudge() {
		return players.get(judgeIndex);
	}
	/**
	 * Sets Judge to a random player
	 */
	public void setRandomJudge() {
		this.judgeIndex = rnd.nextInt(players.size());	
	}
	/**
	 * Sets Judge to next player in line.
	 */
	public void setNextJudge() {
		if(judgeIndex == (players.size() -1)) {
			judgeIndex = 0;
		}else {
			judgeIndex++;
		}
	}	
	
	public GreenCard popGreenCard() {
		currentGreenCard = (GreenCard) greenDeck.popCard();
		return currentGreenCard;
	}
	
	public GreenCard getCurrentGreenCard() {
		return currentGreenCard;
	}
	
	public void addPoint(Player player, GreenCard card) {
		player.addPoint(card);
	}
	
	public void playCard(Card card, Player player) {
		this.playedCards.add(new PlayedCard(card,player));
	}
	
	public void shufflePlayedCards() {
		Collections.shuffle(this.playedCards);
	}
	
	public Card getPlayedCard(int index) {
		return this.playedCards.get(index).getCard();
	}
	/**
	 * 
	 * @param player
	 * @return the card that player played
	 */
	public Card getCardThatPlayerPlayed(Player player) {
		int index;
		for(index = 0; index < playedCards.size(); index++) {
			if (playedCards.get(index).getPlayer() == player) {
				break;
			}
		}
		return this.playedCards.get(index).getCard();
	}
	
	public int getNumberOfPlayedCards() {
		return playedCards.size();
	}
	
 	public Player getPlayerThatPlayedCard(int index) {
		return this.playedCards.get(index).getPlayer();
	}
	
	public void clearAllPlayedCards() {
		this.playedCards.clear();
	}
	/**
	 * Concatenates all the played cards toString into one
	 * @return a string with index and text to each card in playedCards. The cards are separated with a new line. 
	 */
	public String playedCardsToString() {
		String temp = "";
		int counter = 0;
		for(PlayedCard playedCard:playedCards) {
			temp = temp + "\n\t[" + counter + "]" + playedCard.getCard().toString(); 
			counter++;
		}
		return temp;
	}
	/**
	 * Checks if player has enough points to win.
	 * @param player
	 * @return true if the player won, otherwise false.
	 */
	public boolean isWinner(Player player) {
		if (player.getPoints() >= pointsToWin()) {
			return true;
		}
		return false;
	}
	
	private int pointsToWin() {
		switch(this.players.size()) {
			case 4: 
				return 8;
			case 5:
				return 7;
			case 6:
				return 6;
			case 7: 
				return 5;
			default:
				return 4;
		}
	}
}
