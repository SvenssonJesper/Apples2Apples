package cards;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
		public ArrayList<String> scan(String textfile) throws FileNotFoundException{
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
		        throw new FileNotFoundException();
		    }
			return temp;
		}
}

