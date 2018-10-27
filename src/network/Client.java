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
	public static void main(String[] args) throws IOException, UnknownHostException {
		Socket socket = new Socket("127.0.0.1", 4545);
		
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		System.out.println("client startar");
		System.out.println(inFromServer.read());
		//Get the hand of apples from server
//		String[] applesString = (inFromServer.readLine()).split(";");
//		ArrayList<String> hand = new ArrayList<String>(Arrays.asList(applesString));
//		System.out.println("Testa Skriv här boi:");
//		String input = "";
//		try{
//			input = br.readLine();
//			
//		}catch(IOException er){
//			System.out.println("Bad input");
//		}
//		
//		out.writeBytes(input);
//		out.flush();
//		
//		int response = inFromServer.read();
//		
//		System.out.println(response);

	}
}




