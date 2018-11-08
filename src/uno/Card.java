package uno;

public class Card {
	private char color;
	private String type;
	/**
	 * sets the color and type
	 * @param color 'y'(yellow), 'g'(green), 'b'(blue), or 'r'(red), or 'w'(wild)
	 * @param type 0 through 9, dr2(draw 2), skip, rev(reverse), wild, dr4(draw 4 wild)
	 */
	public Card(char color, String type) {
		
	}
	/**
	 * checks if it is a kind of wild, the same color, or the same type
	 * @param other : the other Card
	 * @return whether this Card can be played on top of other
	 */
	public boolean canPlayOn(Card other) {
		return false;
	}
	/**
	 * changes this Card's color if it is a wild card
	 * @param color : the color to change the card to
	 */
	public void setWildColor(char color) {// which sets the card's color if it doesn't already have one
		
	}
	public String getType() {
		return type;
	}
}
