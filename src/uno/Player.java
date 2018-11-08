package uno;

import java.util.ArrayList;

public class Player {
	private ArrayList<Card> hand;//holds the player's cards, starting with 7
	private String name;
	public Player(String name) {
		//should initialize hand, set this.name to name
	}
	/**
	 * asks the player if they want to make a move or draw a card
	 * if they want to make a move, this removes & returns the move if it canPlayOn(top)
	 * if they want to draw a card, this should return null
	 * @param top : the current card
	 * @return the card the player chose or null
	 */
	public Card getPlayerMove(Card top) {
		return null;
	}
	/**
	 * adds the card to the player's hand
	 * @param c the card
	 */
	public void add(Card c) {
		
	}
	public int handSize() {
		return hand.size();
	}
}
