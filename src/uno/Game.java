package uno;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * runs an UNO game when setup() is called
 * holds the players and decks used in the game
 * @author Tommy, Daniel, Jed, Izzy, and Grace
 *
 */
public class Game {
	//public static final int MIN_PLAYERS = 2;
	//public static final int MAX_PLAYERS = 10;
	public static final int HAND_SIZE = 7;
	private Deck deck;//the discard pile and draw pile
	private ArrayList<Player> players;//holds the players
	private Card topCard;//holds the card on top of the discard pile
	private int currentPlayer;//the current player's index
	private int direction = 1;//1 if normal, -1 if reverse
	private int numPlayers;
	
	
	public Game(int selectedNumPlayers) {
		numPlayers = selectedNumPlayers;
		players = new ArrayList<>();
		deck = new Deck();
	}
	/**
	 * in the game loop:
	 * <ul>
	 * 		this should get a player's move then discard the card and set the new top card.<br>
	 * 		if it isn't a number, this should also do the appropriate action.<br>
	 * 		then this should check if the player has won, if they have, it should quit.
	 * </ul>
	 */
	private void gameLoop() {
		while(true) {
			Player p = players.get(currentPlayer);
			Card move = p.getPlayerMove(topCard);
			if(move == null) {
				p.add(deck.drawCard());
			}else {
				if(move.getType() == "rev") {
					if(players.size() == 2) {
						nextPlayer();
					}else {
						direction = direction*-1;
					}
				}
				if(move.getType() == "skip") {
					nextPlayer();
				}
				if(move.getType() == "dr2") {
					nextPlayer();
					Player p2 = players.get(currentPlayer);
					p2.add(deck.drawCard());
					p2.add(deck.drawCard());
				}
				else if(move.getType() == "wild") {
					move.setWildColor(p.getWildColor());
				}
				else if(move.getType() == "dr4") {
					nextPlayer();
					Player p2 = players.get(currentPlayer);
					for(int i = 0; i<4; i++) {
						p2.add(deck.drawCard());
					}
					move.setWildColor(p.getWildColor());
				}
				deck.discard(move);
				topCard=move;
			}
			if(p.handSize() == 0) {
				System.out.println("Congratulations "+p.getName()+", you have won the game!");
				break;
			}
			if(!p.getSaidUno()&&p.handSize()==1) {
				p.add(deck.drawCard());
				p.add(deck.drawCard());
			}
			p.setSaidUno(false);
			nextPlayer();
		}
	}
	/**
	 * prints the rules
	 * gets the number of players
	 * gets the names and initializes the players
	 * gets the 1st player and sets currentPlayer
	 */
	public void menu() {
		for(int i = 0; i < numPlayers; i++) {
			Player p = new Player("Player" + (i + 1));
			players.add(p);
		}
		
		/*Scanner in = new Scanner(System.in);
		System.out.println("how many people are going to play?");
		int numPlayers = in.nextInt();
		in.nextLine();
		while (numPlayers<MIN_PLAYERS || numPlayers>MAX_PLAYERS) {//this will only run if they input a number that is outside of the correct number range of players
			System.out.println("please enter a number between "+MIN_PLAYERS+" and "+MAX_PLAYERS);
			numPlayers=in.nextInt();
			in.nextLine();
		}
		for(int i = 0; i < numPlayers; i++) {
			System.out.print("what is the name of player "+(i+1)+": ");
			String name = in.nextLine();
			Player p = new Player(name);
			players.add(p);
		}
		System.out.print("\nwhich # player is going first: ");
		currentPlayer = in.nextInt()-1;
		in.close(); */
	}
	/**
	 * calls menu()<br>
	 * initializes deck and gives the players 7 cards each,<br>
	 * calls deck.peekDiscard() and sets topCard with it<br>
	 * calls gameLoop()
	 */
	public void setup() {
		//deck = new Deck();
		topCard = deck.peekDiscard();
		//players = new ArrayList<>();
		menu();
		for(Player p : players) {
			for(int i=0; i < HAND_SIZE; i++) {
				p.add(deck.drawCard());
			}
		}
		gameLoop();
	}
	private void nextPlayer() {
		currentPlayer = currentPlayer + direction;
		if(currentPlayer == -1) {
			currentPlayer = players.size() - 1;
		}else {
			currentPlayer = currentPlayer % players.size();
		}
	}
}
