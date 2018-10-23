import cards.*;

public class main {

	public static void main(String[] args) {
		InputFileHandeler testHan = new InputFileHandeler();
		CardFactory testFac = new CardFactory("redApples.txt", "greenApples.txt", testHan);
		testFac.createDecks();
		System.out.println(testFac.getRedCardsDeck());
	}

}
