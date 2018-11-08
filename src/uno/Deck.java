package uno;

import java.util.Stack;

public class Deck {
	private Stack<Card> drawPile;
	private Stack<Card> discardPile;//it's a stack so we can get the top card
	/**
	 * should hold in each color: 
	 * <ul>	one 0 card, two of 1-9; two Draw Two cards;<br>
	 * 		two Skip cards; and two Reverse cards.</ul><br>
	 * Also it should hold four Wild cards and four Wild Draw Four cards
	 */
	Deck(){
		
	}
	/**
	 * takes a Card from the deck, if it's type is a number then it will return it,
	 * otherwise if it's type is a type of wild, than it will get a new card
	 */
	private Card flipInitialCard() {
		return null;//TODO: fill in this method
	}
	/**
	 * gets and removes the top card from the draw pile
	 * it will set the draw pile pointer to the discard pile pointer,
	 * and will set the discard pile to pont to a new Stack
	 * @return the card
	 */
	public Card drawCard() {
		return null;
	}
	/**
	 * @return top card of discard pile
	 */
	public Card peekDiscard() {
		return null;
	}
}
