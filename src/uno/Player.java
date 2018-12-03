package uno;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.paint.Color;
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
	
	public Card getCard(int cardNum) {
		return hand.get(cardNum);
	}
	
	/**
	 * asks the player if they want to make a move or draw a card<br>
	 * if they want to make a move, this removes & returns the Card if it can be played on <b>top</b><br>
	 * if they want to draw a card, this should return null
	 * @param top : the current card
	 * @return the card the player chose or null
	 */
	public Card getPlayerMove(Card top, Scanner sc) {
		System.out.println("\nIt is your turn, "+name+"\n");
		System.out.println("the top card is a "+top+"\n");
		printHand();
		char answer = ' ';
		while(answer != 'm' && answer != 'd') {
			System.out.println();
			System.out.println("Do you want to make a move (m) or draw a card (d)");
			answer = sc.next().charAt(0);
			sc.nextLine();
		}
		if(answer == 'm') {
			System.out.println("The cards in your hand that can be played on a "+top+" are:");
			ArrayList<Card> temp = new ArrayList<>();
			for(Card c : hand) {
				if(c.canPlayOn(top)) {
					temp.add(c);
				}
			}
			for(int i=0; i<temp.size(); i++) {
				System.out.println((i+1)+". "+temp.get(i));
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
				int numOfCard = Integer.parseInt(move.replaceAll("[^0-9]", ""));//removes all non-number characters
				numOfCard--;
				if(numOfCard >= 0 && numOfCard < temp.size()) {
					Card c = temp.get(numOfCard);
					if(c.getType() == "wild" || c.getType() == "dr4") {
						answer = ' ';
						while(answer != 'y' && answer != 'r' && answer != 'g' && answer != 'b') {
							System.out.println();
							System.out.println("Do you want the wild to be Blue (b), Red (r), Green (g), or Yellow (y)");
							answer = sc.next().charAt(0);
							sc.nextLine();
						}
						if(answer == 'b') {
							c.setWildColor(Color.BLUE);
						}
						if(answer == 'g') {
							c.setWildColor(Color.GREEN);
						}
						if(answer == 'r') {
							c.setWildColor(Color.RED);
						}
						if (answer == 'y') {
							c.setWildColor(Color.YELLOW);
						}
					}
					hand.remove(c);
					if(!askUno(sc)) {
						return c;
					}
					else {
						hand.add(c);
						return null;
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
	
	public void remove(int index) {
		hand.remove(index);
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
	
	private void printHand() {
		System.out.println("Your hand:");
		System.out.print("    ");
		
		for(Card c : hand) {
			System.out.print(c+"    ");
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
	
	public ArrayList<Card> getHand() {
		return hand;
	}
}