package uno;

/**
 * holds the type & color of an UNO card
 * @author Tommy, Daniel, Jed, Izzy, and Grace
 */
public class Card {
	private char color;
	private String type;
	/**
	 * sets the color and type
	 * @param color 'y'(yellow), 'g'(green), 'b'(blue), or 'r'(red), or 'w'(wild)
	 * @param type 0 through 9, dr2(draw 2), skip, rev(reverse), wild, dr4(draw 4 wild)
	 */
	public Card(char color, String type) {
		this.color = color;
		this.type = type;
	}
	/**
	 * checks if this Card is a kind of wild, the same color as other, or the same type as other
	 * @param other : the other Card
	 * @return whether this Card can be played on top of other
	 */
	public boolean canPlayOn(Card other) {
		if(type=="wild") {
			return true;
		}
		else if(this.type.equals(other.type)||this.color==other.color) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * changes this Card's color if it is a wild card
	 * @param color : the color to change the card to
	 */
	public void setWildColor(char color) {
		if(this.color == 'w') {
			this.color = color;
		}
	}
	public String getType() {
		return type;
	}
}
