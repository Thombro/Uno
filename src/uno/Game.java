package uno;

import java.util.ArrayList;
import java.util.Stack;

public class Game {
	private Stack<Card> deck;//should hold in each color: one 0 card, two of 1-9; two Draw Two cards; two Skip cards; and two Reverse cards. Also it should hold four Wild cards and four Wild Draw Four cards
	private ArrayList<Player> players;//holds the players
	private Card topCard;//holds the top card
	/**
	 * should run setUp() and then play the game
	 * in the game loop:
	 * 		this should get a player's move, then see if it is a number.
	 * 		if it is, this should change topCard to the move.
	 * 		if it isn't, this should do the appropriate action.
	 * 		then this should check if a player has won, if someone has, it should break out and congatulate them.
	 */
	public void gameLoop() {
		
	}
	private void setUp() {
		//sets up the players and gets 7 cards for each, then calls getFirstCard() and sets topCard with it
	}
	/**
	 * takes a Card from the deck, if it's type is a number then it will return it,
	 * otherwise if it's type is a draw 4 wild, than it will get a new card
	 * otherwise if it's a wild, than the 1st player chooses the color
	 * otherwise if it's a draw two, than the 1st player draws two and loses their next turn
	 * otherwise if it's a skip, than the 1st player loses their turn
	 * otherwise if it's a reverse, than the direction is reversed and the 1st player loses their turn
	 */
	private Card getFirstCard() {//part of setup
		return null;//TODO: fill in this method
	}
}
