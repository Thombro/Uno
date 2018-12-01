package uno;

import javafx.scene.paint.Color;
import java.util.*;

public class Player {
	private ArrayList<Card> hand;
	private String name;
	private boolean saidUno;
	
	public Player(String name) {
		this.name = name;
		hand = new ArrayList<>();
		saidUno = false;
	}
	
	public Card getCard(int cardNum) {
		return hand.get(cardNum);
	}
	
	public Card getPlayerMove(Card top, Scanner scnr) {
		System.out.print("Would you like to move or draw a card (m/d)");
		char input = scnr.next().charAt(0);
		if (input == 'm') {
			for(int i = 0; i < hand.size(); ++i) {
				System.out.println((i + 1) + " " + hand.get(i));
				}
			
			System.out.print("The card at the top is " + top);
			System.out.println("Player " + name + "Which card will you like to play" + "(1 -" + hand.size() + " )");
			int numOfCard = scnr.nextInt();
			
				while (!hand.get(numOfCard - 1).canPlayOn(top)) {
					System.out.println("Player " + name + "Which card will you like to play" + "(1 -" + hand.size() + " )");
					numOfCard = scnr.nextInt();
					
						if (hand.get(numOfCard-1).getType() == "wild" || hand.get(numOfCard-1).getType() == "dr4") {
							System.out.println("What color would you like: ");
							char b = scnr.next().charAt(0);
							
							if(b == 'b') {
								hand.get(numOfCard-1).setWildColor(Color.BLUE);
							}
							if(b == 'g') {
								hand.get(numOfCard-1).setWildColor(Color.GREEN);
							}
							if(b == 'r') {
								hand.get(numOfCard-1).setWildColor(Color.RED);
							}
							if (b == 'y') {
								hand.get(numOfCard-1).setWildColor(Color.YELLOW);
							}
						}
						
					}
				Card c = hand.get(numOfCard - 1);
				if (c.canPlayOn(top)) {
					hand.remove(numOfCard - 1);
				}
				
				return c;
		}
		else {
			return null;
		}
	}
	
	public void add(Card c) {
		if (c != null) {
			hand.add(c);
		}
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
	
	public ArrayList<Card> getHand() {
		return hand;
	}
}