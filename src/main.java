import java.io.IOException;
import java.util.ArrayList;

import cards.*;
import model.Model;
import network.Server;
import player.*;

public class main {

	public static void main(String[] args) {
		Game game;
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
