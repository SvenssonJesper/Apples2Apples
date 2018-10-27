package cards;

public abstract class Card {
	protected String text = "";
	public Card(String text) {
		this.text = text;
	}
	
	public String toString() {
		return this.text;
	}
}
