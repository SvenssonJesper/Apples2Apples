package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * The Client side of the game.
* @author Jesper Svensson
*
*/
public class Client {
	Socket socket;
	DataOutputStream outputStream;
	BufferedReader in, br;
	String ip;
	int port;
	/**
	 * Constructor of client
	 * @param ip the ip-address of the server that you want to connect to.
	 * @param port the port number the server send data on. 
	 */
	public Client(String ip, int port){
		this.ip = ip;
		this.port = port;
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	/**
	 * Connects the client to the provided ip-address and port
	 * @throws java.net.UnknownHostException if there is no server running on this port 
	 * @throws IOException if the ip-address or the port is invalid.
	 */
	public void connect() throws java.net.UnknownHostException, IOException {
		this.socket = new Socket(this.ip, this.port);
		this.outputStream = new DataOutputStream(socket.getOutputStream());
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	/**
	 * Closes the clients socket
	 */
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Runs the game on client side.
	 * @throws IOException if the client is not connected to the server.
	 */
	public void run() throws IOException {
		boolean run = true;
		while (run){
			String input = this.in.readLine();
			switch(input) {
				case "input": {
					this.br = new BufferedReader(new InputStreamReader(System.in));
					this.outputStream.writeBytes(readInput() + "\n");
					this.outputStream.flush();
					System.out.println("Waiting for other players...");
					break;
				}case "gameFinnished": {
					run=false;
					System.out.println("Game finnished, closing client");
					break;
				}
				default:{ 
					System.out.println(input);
				}
			}
		}
	}
	
	private String readInput() {
		String input;
		try{
			input = this.br.readLine();
			if (!input.matches("-?\\d+")) {
				System.out.println(input + " is not a number. Please input a number:");
				input = readInput();
			}
		}catch(IOException er){
			System.out.println("Bad input");
			input = readInput();
		}
		return input;
	}

}




