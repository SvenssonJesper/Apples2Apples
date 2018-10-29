package network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

public class Client {
	Socket socket;
	DataOutputStream outputStream;
	BufferedReader in, br;
	String ip;
	int port;
	
	public Client(String ip, int port){
		this.ip = ip;
		this.port = port;
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String[] args){
		Client client = new Client(args[0], Integer.parseInt(args[1]));
		try {
			client.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("client connected successfully");
		try {
			client.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() throws java.net.UnknownHostException, IOException {
		this.socket = new Socket(this.ip, this.port);
		this.outputStream = new DataOutputStream(socket.getOutputStream());
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void run() throws IOException {
		boolean run = true;
		while (run){
//			if(in.ready()) {
				String input = this.in.readLine();
				switch(input) {
					case "input": {
						this.outputStream.writeBytes(readInput() + "\n");
						this.outputStream.flush();
						break;
					}
					case "exit":{ 
						run = false;
						break;
					}
					default:{ 
						System.out.println(input);
					}
//				}
			}
		}
	}
	
	private String readInput() {
		String input;
		try{
			input = this.br.readLine();
			System.out.println(input);
		}catch(IOException er){
			System.out.println("Bad input");
			input = readInput();
		}
		return input;
	}
}




