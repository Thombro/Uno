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
		System.out.print("Would you like to move or draw a card (m/d) player " + name);
		char input = scnr.next().charAt(0);
		if (input == 'm') {
			for(int i = 0; i < hand.size(); ++i) {
				System.out.println((i + 1) + " " + hand.get(i));
				}
			
			System.out.println("The card at the top is " + top);
			System.out.println("Player " + name + " Which card will you like to play" + "(1 - " + hand.size() + ")");
			int numOfCard = scnr.nextInt();
			Card c = hand.get(numOfCard - 1);
			
				while (!c.canPlayOn(top)) {
					System.out.println("Player " + name + "Which card will you like to play" + "(1 - " + hand.size() + ")");
					numOfCard = scnr.nextInt();
					c = hand.get(numOfCard - 1);
					
						if (c.getType() == "wild" || c.getType() == "dr4") {
							char answer = ' ';
							while (answer != 'y' && answer != 'r' && answer != 'b' && answer != 'g') {
								System.out.println();
								System.out.println("What color do want for your wild card");
								answer = scnr.next().charAt(0);
								scnr.nextLine();
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
						
					}
				
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