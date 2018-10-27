package player;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;

public class Human extends Player{
	protected Socket connection;
	protected BufferedReader inFromClient;
	protected DataOutputStream outToClient;
	public Human(int id, Hand hand, Socket connection, BufferedReader inFromClient, DataOutputStream outToClient) {
		super(id, hand);
		this.connection = connection;
		this.inFromClient = inFromClient;
		this.outToClient = outToClient;
	}
}
