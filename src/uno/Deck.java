package uno;


import java.util.Collections;
import java.util.Stack;

import javafx.scene.paint.Color;

/**
 * holds the discard pile and draw pile from an UNO game
 * @author Isabella Patnode, Thomas Hutchins, Daniel Supplee, Grace Badger, Jedidiah Madubuko
 */
public class Deck {
	//if they are stacks in real life, they are stacks here
	private Stack<Card> drawPile;
	//they are also stacks so we can get the top card
	private Stack<Card> discardPile;
	
	/**
	 * Initializes the draw and discard piles, the flips the initial card <br>
	 * The draw pile holds: one 0 card, two of 1-9, two Draw Two cards, two Skip cards, and 
	 * two Reverse cards in each color, four Wild cards, and four Wild Draw Four cards
	 */
	Deck(){
		drawPile = new Stack<>();
		discardPile = new Stack<>();
		
		//sets the four colors possible and wild as grey
		Color[] colors = {Color.rgb(255, 53, 0), Color.rgb(255, 137, 0), Color.rgb(8, 122, 177), Color.rgb(0, 194, 84), Color.rgb(120, 120, 120)};
		
		//Creates each card
		//Loops through each color
		for (int c = 0; c < 4; c++) {
			drawPile.add(new Card(colors[c], "0"));
			//Loops through each number
			for (int i = 1; i <= 9; i++) {
				drawPile.add(new Card(colors[c], i + ""));
			}
			for (int i = 0; i < 2; i++) {
				drawPile.add(new Card(colors[c], "skip"));
				drawPile.add(new Card(colors[c], "rev"));
				drawPile.add(new Card(colors[c], "dr2"));
			}
		}
		for (int i = 0; i < 4; i++) {
			drawPile.add(new Card(colors[4], "wild"));
			drawPile.add(new Card(colors[4], "dr4"));
		}
		//shuffles the cards in the draw pile
		Collections.shuffle(drawPile);
		flipInitialCard();
	}
	
	/**
	 * moves a Card from the the draw pile to the discard pile, 
	 * if type any type but a number, it will draw a new card
	 */
	private void flipInitialCard() {
		discardPile.add(drawPile.pop());
		
		while(discardPile.peek().getType() == "wild" || discardPile.peek().getType() == "dr4" 
		|| discardPile.peek().getType() == "dr2" || discardPile.peek().getType() == "skp" 
		|| discardPile.peek().getType() == "rev") {
			
			discardPile.add(drawPile.pop());
		}
	}
	
	/**
	 * removes and returns the top Card from the draw pile <br>
	 * if there are no cards in the draw pile:
	 * 		it sets the draw pile pointer to the discard pile pointer, sets the discard pile to a new Stack, 
	 * 		shuffles the draw pile, and draws and returns the drawn card
	 * @return the card
	 */
	public Card drawCard() {
		if (!drawPile.isEmpty()) {
			return drawPile.pop();
		}
		else {
			drawPile = discardPile;
			discardPile = new Stack<Card>();
			discardPile.add(drawPile.pop());
			Collections.shuffle(drawPile);
			
			if(!drawPile.isEmpty()) {
				return drawPile.pop();
			}
			else{
				System.out.println("draw pile is empty");
				return null;
			}
		}
	}
	
	/**
	 * @return top card of discard pile
	 */
	public Card peekDiscard() {
		return discardPile.peek();
	}
	
	/**
	 * adds a card to the discard pile
	 * @param c the card
	 */
	public void discard(Card c) {
		discardPile.add(c);
	}
}
