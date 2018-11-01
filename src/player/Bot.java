package player;

/**
 *  Bot that can play the game. Extends Player.
 * @author Jesper Svensson
 *
 */
public class Bot extends Player{
	public Bot(int id) {
		super(id);
		super.isHuman = false;
	}
	
}
