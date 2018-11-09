package uno;

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
		flipInitialCard();
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
