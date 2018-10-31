package cards;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputFileHandeler {

	public InputFileHandeler() {

	}		 
		/**
 		 * Scanns a textfile and return the content in an ArrayList.
 		 * Each line becomes an element.
		 *
		 * @param  textfile  a file that contains Strings that represents cards 
		 * @return      an ArrayList with the content of the file. 
		 */
		public ArrayList<String> scan(String textfile){
			ArrayList<String> temp = new ArrayList<String>();
			
			try {
				File file = new File(textfile);				
		        Scanner sc = new Scanner(file);
		        
		        while (sc.hasNextLine()) {
		            temp.add(sc.nextLine());
		        }
		        sc.close();
		    } 
		    catch (FileNotFoundException e) {
		    	System.out.println("cant find the file: " + textfile + ".\nEnter an correct file:");
				String newfile = readInput();
				temp = scan(newfile);
		    }
			return temp;
		}
		
		private String readInput() {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			try{
				input = br.readLine();
				
			}catch(IOException er){
				System.out.println("Bad input");
				input = readInput();
			}
			return input;
		}
}

