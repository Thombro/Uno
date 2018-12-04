package uno;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.paint.Color;
/**
 * holds a player's name and hand of cards
 * 
 * @author Isabella Patnode, Thomas Hutchins, Daniel Supplee, Grace Badger, Jedidiah Madubuko
 *
 */
public class Player {
	//holds the player's cards, starting with 7
	private ArrayList<Card> hand;
	//the name of the player
	private String name;
	//the default setting is false and at the end of every turn 
	//it become false, if the player presses the uno button it becomes true
	private boolean saidUno;
	
	public Player(String name) {
		this.name = name;
		hand = new ArrayList<>();
		saidUno=false;
	}
	
	/**
	 * Gets the selected card from the current player's hand
	 * @param cardNum the index of the card selected
	 * @return the selected card in the current player's hand 
	 */
	public Card getCard(int cardNum) {
		return hand.get(cardNum);
	}
	
	/**
	 * asks the player if they want to make a move or draw a card<br>
	 * if they want to make a move, this removes & returns the Card if it can be played on <b>top</b><br>
	 * if they want to draw a card, returns null
	 * @param top the current card
	 * @return the card the player chose or null
	 */
	public Card getPlayerMove(Card top, Scanner sc) {
		System.out.println("\nIt is your turn, "+ name + "\n");
		//tells the current player what the top card is
		System.out.println("the top card is a "+ top.toString() +"\n");
		//prints the player's hand
		printHand();
		char answer = ' ';
		while(answer != 'm' && answer != 'd') {
			System.out.println();
			System.out.println("Do you want to make a move (m) or draw a card (d)");
			answer = sc.next().charAt(0);
			sc.nextLine();
		}
		//runs if the player wants to make a move
		if(answer == 'm') {
			//tells the player which cards in their hand can be played
			System.out.println("The cards in your hand that can be played on a "+ top +" are:");
			//a list of the cards in the player's hand that can be played
			ArrayList<Card> temp = new ArrayList<>();
			for(Card c : hand) {
				if(c.canPlayOn(top)) {
					temp.add(c);
				}
			}
			for(int i = 0; i < temp.size(); i++) {
				System.out.println((i + 1) + ". " + temp.get(i).toString());
			}
			if(temp.size() == 0) {
				System.out.println("no cards to play, drawing a card");
				try{
					Thread.sleep(1000);
				}catch(Exception ex){}
				return null;
			}
			String move;
			while(true) {
				System.out.println();
				System.out.println("which do you want to play (1-" + temp.size() + "), or cancel (c) to draw a card");
				move = sc.nextLine();
				if(move.charAt(0) == 'c') {
					return null;
				}
				//removes all non-number characters
				int numOfCard = Integer.parseInt(move.replaceAll("[^0-9]", ""));
				numOfCard--;
				//runs if the card is within the list of cards that can be played
				if(numOfCard >= 0 && numOfCard < temp.size()) {
					Card c = temp.get(numOfCard);
					//sets the color that the player wants if the card is a wild
					if(c.getType() == "wild" || c.getType() == "dr4") {
						answer = ' ';
						while(answer != 'y' && answer != 'r' && answer != 'g' && answer != 'b') {
							System.out.println();
							System.out.println("Do you want the wild to be Blue (b), Red (r), Green (g), or Yellow (y)");
							answer = sc.next().charAt(0);
							sc.nextLine();
						}
						//sets the color to blue
						if(answer == 'b') {
							c.setWildColor(Color.rgb(8, 122, 177));
						}
						//sets the color to green
						if(answer == 'g') {
							c.setWildColor(Color.rgb(0, 194, 84));
						}
						//sets the color to red
						if(answer == 'r') {
							c.setWildColor(Color.rgb(255, 53, 0));
						}
						//sets the color to yellow
						if (answer == 'y') {
							c.setWildColor(Color.rgb(255, 137, 0));
						}
					}
					//removes the card from the player's hand
					hand.remove(c);
					if(!askUno(sc)) {
						return c;
					}
					else {
						saidUno = true;
						return c;
					}
				}
			}
		}
		else {
			return null;
		}
	}
	
	/**
	 * adds the card to the player's hand
	 * @param c : the card
	 */
	public void add(Card c) {
		if(c != null) {
			hand.add(c);
		}
	}
	/**
	 * removes the card at the specified index from the hand
	 * @param index the index of the card to be removed
	 */
	public void remove(int index) {
		hand.remove(index);
	}
	
	/**
	 * @return the number of cards in the player's hand
	 */
	public int handSize() {
		return hand.size();
	}
	
	/**
	 * @return the name of the current player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return true if the player says uno, false otherwise
	 */
	public boolean getSaidUno() {
		return saidUno;
	}
	
	/**
	 * @param unoSaid true if player said uno, false otherwise
	 */
	public void setSaidUno(boolean unoSaid) {
		saidUno = unoSaid;
	}
	
	/**
	 * prints the current player's hand of cards
	 */
	private void printHand() {
		System.out.println("Your hand:");
		System.out.print("    ");
		
		for(Card c : hand) {
			System.out.print(c.toString() + "    ");
		}
		
		System.out.println();
	}
	
	/**
	 * asks the player if they want to say uno
	 * if they say uno when they have more than one card,
	 * then their move will be a draw
	 * @return whether the move will be a draw
	 */
	private boolean askUno(Scanner sc) {
		if(hand.size()==0) {
			return false;
		}
		else {
			System.out.println("do you want to say uno (u), or end your turn (any character)");
			System.out.println("saying uno when you don't have only one card will cause you to draw a card.");
			char answer = sc.next().charAt(0);
			
			if (answer == 'u') {
				if(hand.size() <= 1) {
					saidUno = true;
					return false;
				}
				else {
					return true;
				}
			}
			
			return false;
		}
	}
	/**
	 * @return the current player's hand of cards
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}
}