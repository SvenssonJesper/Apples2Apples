package controller;
import java.io.IOException;
import java.util.HashMap;

import cards.*;
import model.Model;
import network.Server;
import view.View;
import player.Player;

public class Game {
	Server server;
	Model model;
	View view;
	int numberOfClients, port;
	String greenDeck, redDeck;
	HashMap<Player, String> playedCards;
	
	public Game(int numberOfClients,int port, String greenDeck, String redDeck) {
		this.numberOfClients = numberOfClients;
		this.port = port;
		this.greenDeck = greenDeck;
		this.redDeck = redDeck;
		this.view = new View();
		this.playedCards = new HashMap<Player, String>();
	}
	
	public void init() {
		setupModel();
		System.out.println("game initialized");
		startServer();
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
		model.dealRedCards();
		boolean run = true;
		model.setJudge(model.getPlayers().get(0));
		while(run) {
			sendNewRound();
			model.popGreenCard();
			sendNewGreenCard();
			sendHand();
			waitForInput();
			waitForJudgeInput();
		}
	}
	
	private void waitForInput(){
		for(Player player: model.getPlayers()) {
			if(player != model.getJudge()) {
				try {
					System.out.println(server.requireInput(player));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void waitForJudgeInput(){
		try {
			System.out.println(server.requireInput(model.getJudge()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendNewGreenCard() {
		for(Player player: model.getPlayers()) {
			server.sendTextToClient(player, "Green apple: " + model.getCurrentGreenCard().toString());
		}
	}
	
	private void sendNewRound() {
		for(Player player: model.getPlayers()) {
			if(player == model.getJudge())
				server.sendTextToClient(player, view.newRound(true));
			else {
				server.sendTextToClient(player, view.newRound(false));
			}
		}
	}
	
	private void sendHand() {
		for(Player player: model.getPlayers()) {
			if(player != model.getJudge())
				server.sendTextToClient(player,player.getAllCardsTextInHand());
		}
	}
}

