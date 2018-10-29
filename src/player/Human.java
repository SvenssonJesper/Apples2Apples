package player;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;

public class Human extends Player{
	protected Socket connection;
	protected BufferedReader in;
	protected DataOutputStream outputStream;
	public Human(int id, Socket connection, BufferedReader inFromClient, DataOutputStream outputStream) {
		super(id);
		this.connection = connection;
		this.in = inFromClient;
		this.outputStream = outputStream;
	}
	
	public DataOutputStream getOutputStream() {
		return this.outputStream;
	}
	
	public BufferedReader getInputStream() {
		return this.in;
	}
}
