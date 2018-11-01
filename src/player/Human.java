package player;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * The human class aka clients. Extends Player.
 * @author Jesper Svensson
 *
 */

public class Human extends Player{
	protected Socket connection;
	protected BufferedReader in;
	protected DataOutputStream outputStream;
	/**
	 * Constructor to Human class. Extends Player.
	 * @param id the id of the user.
	 * @param connection the socket the user will communicate through.
	 * @param inFromClient the BufferedReader of the DataInputStream of the users socket.
	 * @param outputStream the DataOutputStream of the users socket.
	 */
	public Human(int id, Socket connection, BufferedReader inFromClient, DataOutputStream outputStream) {
		super(id);
		this.connection = connection;
		this.in = inFromClient;
		this.outputStream = outputStream;
		super.isHuman = true;
	}
	
	public DataOutputStream getOutputStream() {
		return this.outputStream;
	}
	
	public BufferedReader getInputStream() {
		return this.in;
	}
}
