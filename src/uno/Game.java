package uno;

import java.util.ArrayList;
import java.util.Stack;
/**
 * runs an UNO game when setup() is called
 * holds the players and decks used in the game
 * @author Tommy, Daniel, Jed, Izzy, and Grace
 *
 */
public class Game {
	private Deck deck;//the discard pile and draw pile
	private ArrayList<Player> players;//holds the players
	private Card topCard;//holds the card on top of the discard pile
	private int currentPlayer;//the current player's index
	/**
	 * in the game loop:
	 * <ul>
	 * 		this should get a player's move then discard the card and set the new top card.<br>
	 * 		if it isn't a number, this should also do the appropriate action.<br>
	 * 		then this should check if the player has won, if they have, it should quit.
	 * </ul>
	 */
	private void gameLoop() {
		
	}
	/**
	 * prints the rules
	 * gets the number of players
	 * gets the names and initializes the players
	 * gets the 1st player and sets currentPlayer
	 */
	private void menu() {
		
	}
	/**
	 * calls menu()<br>
	 * gives the players 7 cards each,<br>
	 * initializes deck and calls deck.getFirstCard() and sets topCard with it<br>
	 * calls gameLoop()
	 */
	public void setup() {
		
	}
}
