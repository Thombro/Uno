package uno;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * holds a player's name and hand of Cards.
 * 
 * @author Tommy, Daniel, Jed, Izzy, and Grace
 *
 */
public class Player {
	private ArrayList<Card> hand;//holds the player's cards, starting with 7
	private String name;
	private boolean saidUno;//default & at the end of every turn false, if the player presses the uno button true
	public Player(String name) {
		this.name = name;
		hand = new ArrayList<>();
		saidUno=false;
	}
	/**
	 * asks the player if they want to make a move or draw a card<br>
	 * if they want to make a move, this removes & returns the Card if it can be played on <b>top</b><br>
	 * if they want to draw a card, this should return null
	 * @param top : the current card
	 * @return the card the player chose or null
	 */
	public Card getPlayerMove(Card top) {
		// I added this to the code
		Scanner scnr = new Scanner(System.in);
		System.out.print("Would you like to move or draw a card (m/d)");
		char input = scnr.next().charAt(0);
		if (input == 'm') {
			System.out.println("Which card will you like to play");
			int numOfCard = scnr.nextInt();
			hand.get(numOfCard);
		}
		else {
			hand.remove(top);
			return null;
		}	
	}
	/**
	 * adds the card to the player's hand
	 * @param c : the card
	 */
	public void add(Card c) {
		hand.add(c);
	}
	public int handSize() {
		return hand.size();
	}
	public String getName() {
		return name;
	}
	public boolean getSaidUno() {
		return saidUno;
	}
	public void setSaidUno(boolean unoSaid) {
		saidUno = unoSaid;
	}
	/**
	 * a method that is called when a wild card is played
	 * @return what color the wild card should be
	 */
	public char getWildColor() {
		// I added this to the code
		Scanner scnr = new Scanner(System.in);
		System.out.println("What color would you like:");
		char b = scnr.next().charAt(0); 
		return b;
	}
}
