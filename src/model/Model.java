package model;

import java.util.ArrayList;
import java.util.Collections;

import cards.*;
import player.*;

public class Model {
	private Deck redDeck, greenDeck;
	private ArrayList<Player> players;
	private Player judge;
	private int maxHandSize = 7;
	public Model(ArrayList<Player> players, Deck redDeck, Deck greenDeck) {
		this.redDeck = redDeck;
		this.greenDeck = greenDeck;
		this.players = players;
		Collections.shuffle(this.players);
		this.judge = players.get(0);
	}
}
