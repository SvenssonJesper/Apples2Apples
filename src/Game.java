import java.io.IOException;

import cards.*;
import controller.Controller;
import model.Model;
import network.Server;
import view.View;
import player.Player;

public class Game {
	Server server;
	Model model;
	Controller controller;
	View view;
	int numberOfClients, port;
	String greenDeck, redDeck;
	public Game(int numberOfClients,int port, String greenDeck, String redDeck) {
		this.numberOfClients = numberOfClients;
		this.port = port;
		this.greenDeck = greenDeck;
		this.redDeck = redDeck;
		this.view = new View();
	}
	
	public void init() {
		setupModel();
		this.controller = new Controller(view, model);
		System.out.println("game initialized");
		System.out.println("starting server");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		model.dealRedCards();
		boolean run = true;
		while(run) {
			for(Player player: model.getPlayers()) {
				server.sendTextToClient(player, view.newRound(false));
				server.sendTextToClient(player, model.popGreenCard().toString());
				server.sendTextToClient(player,player.getAllCardsTextInHand());
				try {
					System.out.println(server.requireInput(player));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

