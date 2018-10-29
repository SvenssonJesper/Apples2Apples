package view;

public class View {
	public View() {

	}
	
	public String newRound(boolean judge) {
		String header = "*****************************************************";
		if(judge) {
			 header = header + "\n**                 NEW ROUND - JUDGE               **";
		}else {
			header = header + "\n**                    NEW ROUND                    **";
		}
		header = header + "\n*****************************************************";
		return header;
	}
}
