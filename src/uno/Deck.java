package uno;

import java.awt.Color;
import java.util.Collections;
import java.util.Stack;
/**
 * holds the discard pile and draw pile from an UNO game
 * @author Tommy, Daniel, Jed, Izzy, and Grace
 */
public class Deck {
	private Stack<Card> drawPile;//if they are stacks in real life, they are stacks here
	private Stack<Card> discardPile;//they are also stacks so we can get the top card
	/**
	 * this should initialize the draw pile and the discard pile, and then flip the initial card<br>
	 * the draw pile should hold in each color:
	 * <ul>
	 * 		one 0 card, two of 1-9; two Draw Two cards;<br>
	 * 		two Skip cards; and two Reverse cards.
	 * </ul>
	 * the draw pile should also hold four Wild cards and four Wild Draw Four cards
	 */
	Deck(){
		drawPile = new Stack<>();
		discardPile = new Stack<>();
		
		/*Color[] colors = {new Color(255,0,0), new Color(0,128,255), new Color(128,255,0), new Color(255,255,0), new Color(120,120,120)};
		
//		Loops through each color
		for (int c = 0; c < 4; c++) {
			drawPile.add(new Card(colors[c].getRed(), colors[c].getGreen(), colors[c].getBlue(), "0"));
//			Loops through each number
			for (int i = 1; i <= 9; i++) {
				drawPile.add(new Card(colors[c].getRed(), colors[c].getGreen(), colors[c].getBlue(), Integer.toString(i)));
			}
			for (int i = 0; i < 2; i++) {
				drawPile.add(new Card(colors[c].getRed(), colors[c].getGreen(), colors[c].getBlue(), "Skip"));
				drawPile.add(new Card(colors[c].getRed(), colors[c].getGreen(), colors[c].getBlue(), "Reverse"));
			}
		}
		for (int i = 0; i < 4; i++) {
			drawPile.add(new Card(colors[4].getRed(), colors[4].getGreen(), colors[4].getBlue(), "Wild"));
			drawPile.add(new Card(colors[4].getRed(), colors[4].getGreen(), colors[4].getBlue(), "Wild Draw Four"));
		}
		Collections.shuffle(drawPile);
		flipInitialCard(); */
	}
	/**
	 * moves a Card from the the draw pile to the discard pile, 
	 * if it's type is wild then it will move a new card to raplace it
	 */
	private void flipInitialCard() {
		
	}
	/**
	 * removes and returns the top Card from the draw pile<br>
	 * if there are no cards in the draw pile:
	 * <ul>
	 * 		it will set the draw pile pointer to the discard pile pointer,<br>
	 * 		then it will set the discard pile to a new Stack<br>
	 * 		then it will shuffle the draw pile
	 * 		then it will draw & return the Card
	 * </ul>
	 * @return the card
	 */
	public Card drawCard() {
		return null;//what if everyone only draws cards and never plays any?
	}
	/**
	 * @return top card of discard pile
	 */
	public Card peekDiscard() {
		return null;
	}
	/**
	 * adds a card to the discard pile
	 * @param c the card
	 */
	public void discard(Card c) {
		
	}
}
