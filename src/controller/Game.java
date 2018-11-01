package controller;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import model.Model;
import view.View;
import player.Bot;
import player.Player;
import server.Server;

/**
 * Game acts as a controller. Game uses model ,view and server.
* @author Jesper Svensson
*
*/

public class Game {
	Server server;
	Model model;
	View view;
	private Random rnd; 
	private boolean run;
	
	public Game(Model model, View view, Server server) {
		this.model = model;
		this.view = view;
		this.server = server;
		this.rnd = ThreadLocalRandom.current();
	}
	/**
	 * Initialize the game
	 */
	public void init() {
		addBots();
	}
	/**
	 * Sets the run variable
	 * @param run the boolean that run should have.
	 */
	public void setRun(boolean run) {
		this.run = run;
	}
	
	/**
	 * The game loop which follows the game flow.
	 * This function follows the game flow of Apples 2 Apples.
	 */
	public void run() {
		Player roundWinner = null;
		setRun(true);
		model.setRandomJudge();
		sendNameToClients();
		while(run) {
//			Game flow
			model.dealRedCards();
			model.clearAllPlayedCards();
			sendNewRoundMessage();
			model.popGreenCard();
			sendNewGreenCard();
			sendHand();
			receivePlayedCardsFromClients();
			receivePlayedCardsFromBots();
			model.shufflePlayedCards();
			sendPlayedCards();
			roundWinner = receiveRoundWinnerFromJudge();
			addPoint(roundWinner);
			sendRoundWinnerMessage(roundWinner);
			sendPonitsInformation();
			setRun(!model.isWinner(roundWinner));	
			model.setNextJudge();
		}
		sendWinnerMessage(roundWinner);
		sendCloseClientsCommand();
		server.close();
	}
	
	private void sendNameToClients() {
		for(Player player: model.getPlayers()) {
			if(player.isHuman()) {
				server.sendTextToClient(player, view.nameMessage(player));
			}
		}
		
	}
	
	private void sendPonitsInformation() {
		for(Player player: model.getPlayers()) {
			if(player.isHuman()) {
				server.sendTextToClient(player, view.sendPoints(player));
			}
		}
	}

	private void sendInputCommand(Player player) {
		sendMessageToClients("input");
	}
	
	private void addBots() {
		while(model.getPlayers().size() < 4) {
			model.addPlayer(new Bot(model.getPlayers().size()));
		}
	}
	
	private void sendCloseClientsCommand() {
		sendMessageToClients("gameFinnished");
	}
	
	private void sendMessageToClients(String text) {
		for(Player player: model.getPlayers()) {
			if(player.isHuman()) {
				server.sendTextToClient(player, text);
			}
		}
	}
	
	private void sendWinnerMessage(Player winner) {
		sendMessageToClients(view.winnerMessage(winner));
	}
	
	private void sendRoundWinnerMessage(Player roundWinner) {
		sendMessageToClients(view.roundWinnerMessage(roundWinner, model.getCardThatPlayerPlayed(roundWinner)));
	}
	
	private void addPoint(Player player) {
		model.addPoint(player, model.getCurrentGreenCard());
	}
	
	private void sendPlayedCards() {
		sendMessageToClients(view.playedCardsMessage(model.getCurrentGreenCard(), model.playedCardsToString()));
	}
	
	private Player receiveRoundWinnerFromJudge(){
		int index;
		if(model.getJudge().isHuman()) {
				sendInputCommand(model.getJudge());
				index = receivePlayedCardIndexFromClient(model.getJudge());
				return model.getPlayerThatPlayedCard(index);
		}else {
			index = rnd.nextInt(model.getNumberOfPlayedCards());
			return model.getPlayerThatPlayedCard(index);
		}
	}
	
	private void sendNewGreenCard() {
		sendMessageToClients(view.greenCardMessage(model.getCurrentGreenCard()));
	}
	
	private void sendNewRoundMessage() {
		for(Player player: model.getPlayers()) {
			if (player.isHuman()) {
				if(player == model.getJudge())
					server.sendTextToClient(player, view.newRound(true));
				else {
					server.sendTextToClient(player, view.newRound(false));
				}
			}
		}
	}
	
	private void sendHand() {
		for(Player player: model.getPlayers()) {
			if(player != model.getJudge() && player.isHuman())
				server.sendTextToClient(player,view.sendHand(player));
		}
	}
	
	private int receivePlayedCardIndexFromClient(Player player) {
		int cardNumber = 0;
		try {
			cardNumber = Integer.parseInt(server.receiveInput(player)); 
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Checks if the number that client sent was valid.
//		Number must be larger than 0
//		If the player is not the judge the number must be smaller than max hand size.
//		If the player is the judge the number must be smaller than number of played cards.
		boolean judgeCondition =  ((player == model.getJudge()) && cardNumber >= model.getNumberOfPlayedCards());
		boolean notJudgeCondition = ((player != model.getJudge()) && cardNumber >= model.getMaxHandSize());
		
		if(0 > cardNumber || notJudgeCondition || judgeCondition) {
			server.sendTextToClient(player, "Invalid input. Please choose a valid card number");
			sendInputCommand(player);
			cardNumber = receivePlayedCardIndexFromClient(player);
		}
		return cardNumber;
	}
	
	private void receivePlayedCardsFromClients() {
//		Tells clients that the should send inputs
		for(Player player: model.getPlayers()) {
			if(player != model.getJudge() && player.isHuman()) {
				sendInputCommand(player);
			}
		}
//		reads the client inputs
		for(Player player: model.getPlayers()) {
			if(player != model.getJudge() && player.isHuman()) {
				model.playCard(player.playCard(receivePlayedCardIndexFromClient(player)), player);
			}
		}
	}
	
	private void receivePlayedCardsFromBots() {
		for(Player player: model.getPlayers()) {
			if(player != model.getJudge() && !player.isHuman()) {
				model.playCard(player.playCard(rnd.nextInt(player.getHandSize())), player);
			}
		}
	}
}

