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
			System.out.println("waiting for client");
			Socket client = this.server.accept();
			System.out.println("client accepted");
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(client.getOutputStream());
			Hand hand = new Hand();
			for(int i=0; i<7; i++) { //Deal 7 cards to the online Player
				hand.add(model.popRedCard());			
			}
			outToClient.writeBytes(hand.toString());
			model.addPlayer(new Human(onlineClient, hand, client, inFromClient, outToClient));
			System.out.println("Connected to " + "Player ID: " + (onlineClient));
		}

	}
}
