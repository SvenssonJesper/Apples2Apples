package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import model.Model;
import player.Human;
import player.Player;

/**
 * The server that connects to the clients.
* @author Jesper Svensson
*
*/

public class Server {
	private int numberOfClients;
	private Model model;
	private ServerSocket server;
	
	/**
	 * Constructor of server. 
	 * @param numberOfClients number of clients that will connect.
	 * @param model is the model where the clients should be added to.
	 * @param port which is the port number the serversocket will communicate on.
	 * @throws IOException if you try to accses input from a player that has not connected.
	 */
	public Server(int numberOfClients, Model model, int port) throws IOException {
		this.numberOfClients = numberOfClients;
		this.model = model;
		server = new ServerSocket(port);
	}
	
	public void connectToClients() throws IOException {
		for(int onlineClient=0; onlineClient<numberOfClients; onlineClient++) {
			System.out.println("waiting for " + (numberOfClients - onlineClient) + " client(s) to connect");
			Socket client = this.server.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
			model.addPlayer(new Human(onlineClient, client, inFromClient, outputStream));
			System.out.println("Connected to " + "Player ID: " + (onlineClient));
		}
	}
	/**
	 * Sends the provided text to players outputstream.
	 * @param player the client that should receive the text.
	 * @param text the text that will be sent.
	 */
	public void sendTextToClient(Player player, String text){	
		try {
			((Human) player).getOutputStream().writeBytes(text+"\n");
			((Human) player).getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * receive input from provided player.
	 * @param player that sends data.
	 * @return the data that player sent as String.
	 * @throws IOException if the player don't have connected.
	 */
	public String receiveInput(Player player) throws IOException {
		return ((Human) player).getInputStream().readLine();
	}
	/**
	 * Closes the serversocket.
	 */
	public void close() {
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
