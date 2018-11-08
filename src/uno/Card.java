package uno;

public class Card {
	private char color; // can be blue:'b', green:'g', yellow:'y', red:'r', or none:' '(ex. wild)
	private String type; //can be 0-9, draw 2(Dr2?), skip(Skp?), reverse(Rev?), wild(Wld?), draw 4 wild(D4W?)
	/**
	 * @param other : the other Card
	 * @return whether this Card can be played on top of other
	 */
	public boolean canPlayOn(Card other) {//function which checks if it is a kind of wild, the same color, or the same type
		return false;
	}
	/**
	 * changes this Card's color if it is a wild card
	 * @param color : the color to change the card to
	 */
	public void setWild(char color) {// which sets the card's color if it doesn't already have one
		
	}
}
