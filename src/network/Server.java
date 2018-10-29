package network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import model.Model;
import player.Hand;
import player.Human;
import player.Player;

public class Server {
	private int numberOfClients;
	private Model model;
	private ServerSocket server;
	
	public Server(int numberOfClients, Model model, int port) throws IOException {
		this.numberOfClients = numberOfClients;
		this.model = model;
		server = new ServerSocket(port);
		setUpClients();
	}
	
	private void setUpClients() throws IOException {
		for(int onlineClient=0; onlineClient<numberOfClients; onlineClient++) {
			Socket client = this.server.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
			model.addPlayer(new Human(onlineClient, client, inFromClient, outputStream));
			System.out.println("Connected to " + "Player ID: " + (onlineClient));
		}
	}
	
	public void sendTextToClient(Player player, String text){	
		try {
			((Human) player).getOutputStream().writeBytes(text+"\n");
			((Human) player).getOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public String requireInput(Player player) throws IOException {
		sendTextToClient(player, "input");
		return ((Human) player).getInputStream().readLine();
	}
}
//
//Hand hand = new Hand();
//for(int i=0; i<7; i++) { //Deal 7 cards to the online Player
//	hand.add(model.popRedCard());			
//}
//System.out.println(hand.toString());
