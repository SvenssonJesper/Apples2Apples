import cards.*;

public class main {

	public static void main(String[] args) {		
		CardFactory testFac = new CardFactory("redApples.txt", "greenApples.txt");
		testFac.createSuffledDecks();
		System.out.println(testFac.getRedCardsDeck().get(25).getText());
	}

}
