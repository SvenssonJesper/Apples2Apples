import java.io.IOException;
import java.util.ArrayList;

import cards.*;
import controller.Game;
import model.Model;
import network.Server;
import player.*;

public class main {

	public static void main(String[] args) {
		Game game;
//		Scuff: fixa args så man bara kan skicka in rätt saker.
		switch(args.length) {
			case 2:
				game = new Game(Integer.parseInt(args[0]), Integer.parseInt(args[1]), "greenApples.txt", "redApples.txt");
				break;
			case 4:
				game = new Game(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], args[3]);
				break;
			default:
				game = new Game(1, 4545, "greenApples.txt", "redApples.txt");
				break;
			
		}
		game.init();
		game.run();
	}
	
}
