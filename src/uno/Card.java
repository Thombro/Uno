package uno;


import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * holds the type & color of an UNO card
 * @author Isabella Patnode, Thomas Hutchins, Daniel Supplee, Grace Badger, Jedidiah Madubuko
 */
public class Card extends Parent{
	private Color color;
	private String type;
	private Rectangle bg;
	
	/**
	 * sets the color and type
	 * @param color 'y'(yellow), 'g'(green), 'b'(blue), or 'r'(red), or 'w'(wild)
	 * @param type 0 through 9, dr2(draw 2), skip, rev(reverse), wild, dr4(draw 4 wild)
	 */
	public Card(Color color, String type) {
		this.color = color;
		this.type = type;
		
		//creates the display of each card for the gui
		bg = new Rectangle(80,100);
		bg.setArcHeight(20);
		bg.setFill(color);
		Text text = new Text(type);
		text.setFont(Font.font("Serif", 20));
		text.setWrappingWidth(70);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFill(Color.BLACK);
		getChildren().add(new StackPane(bg,text));
	}
	
	/**
	 * checks if this Card is a kind of wild, the same color as other, or the same type as other
	 * @param other : the other Card
	 * @return whether this Card can be played on top of other
	 */
	public boolean canPlayOn(Card other) {
		if(type=="wild" || type=="dr4") {
			return true;
		}
		else if(this.type.equals(other.type) || this.color.equals(other.color)) {
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
	public void setWildColor(Color color) {
		if(this.type == "wild" || type=="dr4") {
			this.color = color;
			bg.setFill(this.color);
		}
	}
	
	/**
	 * @return what type the card is
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * modifies the toString method for each color and type
	 */
	@Override
	public String toString() {
		if(color.equals(Color.rgb(8, 122, 177))) {
			return "blue "+ type;
		}
		else if(color.equals(Color.rgb(255, 53, 0))) {
			return "red "+ type;
		}
		else if(color.equals(Color.rgb(0, 194, 84))) {
			return "green "+ type;
		}
		else if(color.equals(Color.rgb(255, 137, 0))) {
			return "yellow "+ type;
		}
		else {
			return type;
		}
	}
}
