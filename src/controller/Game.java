package controller;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import cards.*;
import model.Model;
import network.Server;
import view.View;
import player.Bot;
import player.Player;

public class Game {
	Server server;
	Model model;
	View view;
	int numberOfClients, port;
	String greenDeck, redDeck;
	private Random rnd; 
	
	public Game(int numberOfClients,int port, String greenDeck, String redDeck) {
		this.numberOfClients = numberOfClients;
		this.port = port;
		this.greenDeck = greenDeck;
		this.redDeck = redDeck;
		this.view = new View();
		this.rnd = ThreadLocalRandom.current();
	}
	
	public void init() {
		setupModel();
		System.out.println("game initialized");
		startServer();
		addBots();
	}
	
	
	private void setupModel() {
		DeckFactory testFac = new DeckFactory();
		Deck<GreenCard> greenDeck = testFac.createGreenDeck(this.greenDeck);
		greenDeck.shuffle();
		Deck<RedCard> redDeck = testFac.createRedDeck(this.redDeck);
		redDeck.shuffle();
		this.model = new Model(redDeck, greenDeck);
	}
	
	private void startServer() {
		try {
			this.server = new Server(this.numberOfClients, model, this.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		Player roundWinner = null;
		boolean run = true;
		model.setRandomJudge();
		while(run) {
//			Game flow
			model.dealRedCards();
			model.clearAllPlayedCards();
			sendNewRound();
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
			run = !model.isWinner(roundWinner);	
			model.setNextJudge();
		}
		sendWinnerMessage(roundWinner);
		sendCloseClientMessage();
	}
	
	private void addBots() {
		while(model.getPlayers().size() < 4) {
			model.addPlayer(new Bot(model.getPlayers().size()));
		}
	}
	
	private void sendCloseClientMessage() {
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
		sendMessageToClients("The following apples were played:\n" + model.getCurrentGreenCard().toString() + model.playedCardsToString());
	}
	
	private Player receiveRoundWinnerFromJudge(){
		int index;
		if(model.getJudge().isHuman()) {
			try {
				index = Integer.parseInt(server.requireInput(model.getJudge()));
				return model.getPlayerThatPlayedCard(index);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			index = rnd.nextInt(model.getNumberOfPlayedCards());
			return model.getPlayerThatPlayedCard(index);
		}
		return null;
	}
	
	private void sendNewGreenCard() {
		sendMessageToClients("Green apple: " + model.getCurrentGreenCard().toString());
	}
	
	private void sendNewRound() {
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
				server.sendTextToClient(player,"Choose a red apple to play:\n" + player.getAllCardsTextInHand());
		}
	}
	
	private void receivePlayedCardsFromClients() {
		for(Player player: model.getPlayers()) {
			if(player != model.getJudge() && player.isHuman()) {
				try {
					 model.playCard(player.playCard(Integer.parseInt(server.requireInput(player))), player);
				} catch (IOException e) {
					e.printStackTrace();
				}
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

