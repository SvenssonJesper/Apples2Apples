import java.io.IOException;
import java.util.ArrayList;

import com.sun.xml.internal.ws.util.StringUtils;

import cards.*;
import controller.Game;
import model.Model;
import network.Server;
import player.*;

public class main {

	public static void main(String[] args) {
		Game game;
//		Scuff: fixa args s� man bara kan skicka in r�tt saker.
		switch(args.length) {
			case 2:
//				if(StringUtils.isNumeric(args[0]) && (Integer.parseInt(args[0]) > 0)) {
					game = new Game(Integer.parseInt(args[0]), Integer.parseInt(args[1]), "greenApples.txt", "redApples.txt");
			
//				}else {
//					System.out.println("number of clients must be a number thats larger than 0");
//				}
				
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
