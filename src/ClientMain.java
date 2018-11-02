import java.io.IOException;
import java.util.regex.Pattern;

import client.Client;

/**
* This file starts the client.
* @author Jesper Svensson
*
*/

public class ClientMain {
	
	/**
	 * Main function for running client.
	 * @param args contains ip and port number to server
	 */
	public static void main(String[] args){
//		Default options that will be set if no arguments are provided 
		String ip = "127.0.0.1";
		int port = 4545;
		if (args.length == 2 && validateIp(args[0]) && validatePort(args[1])) {
			ip = args[0];
			port = Integer.parseInt(args[1]);
		}else {
			System.out.println("Starting client with default parameters");
		}
		Client client = new Client(ip, port);
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

	private static boolean validatePort(String port) {
		if (port.matches("-?\\d+") && Integer.parseInt(port) > 0) {
			return true;
		}
		System.out.println("The provided portnumber was incorrect. \nPlease provide a portnumber that is an integers larger than 0");
		return false;
	}
	
//	checks if the input was an ip address
	private static boolean validateIp(final String ip) {
	    if(PATTERN.matcher(ip).matches()) {
	    	return true;
	    }
	    System.out.println("The provided ip address was incorrect.");
	    return false;
	}
	
	private static final Pattern PATTERN = Pattern.compile(
	        "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

}
