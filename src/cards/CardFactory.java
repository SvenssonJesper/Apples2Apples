package cards;


public class CardFactory {

	public CardFactory() {
		
	}
	
	public RedCard createRedCard(String text) {
		return new RedCard(text);
	}
	
	public GreenCard createGreenCard(String text) {
		return new GreenCard(text);
	}
	
	
}
