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
		 * @param  textfile  a file that ... 
		 * @return      an ArrayList with the content of the file. 
		 */
		public ArrayList<String> scan(String textfile) {
			ArrayList<String> scanned = new ArrayList();
			
			try {
				File file = new File(textfile);				
		        Scanner sc = new Scanner(file);
		        
		        while (sc.hasNextLine()) {
		            scanned.add(sc.nextLine());
		        }
		        sc.close();
		    } 
		    catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
			return scanned;
		}
}

