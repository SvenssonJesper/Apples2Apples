import java.io.IOException;

import client.Client;

public class ClientMain {
	public static void main(String[] args){
		Client client = new Client(args[0], Integer.parseInt(args[1]));
		try {
			client.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("connected successfully!\nThe game will start when all clients has connected.");
		try {
			client.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
		client.close();
	}

}
