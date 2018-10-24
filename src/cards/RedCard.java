package cards;

public class RedCard extends Card{
	private int id;
	public RedCard(String text, int id){
		super.text = text;
		this.id = id;
	}
	
	public String getText(){
		return super.text;
	}
}
