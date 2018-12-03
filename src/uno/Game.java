package uno;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.paint.Color;

/**
 * runs an UNO game when setup() is called
 * holds the players and decks used in the game
 * @author Tommy, Daniel, Jed, Izzy, and Grace
 *
 */
public class Game {
	public static final int MIN_PLAYERS = 2;
	public static final int MAX_PLAYERS = 4;
	public static final int HAND_SIZE = 7;
	private Deck deck;//the discard pile and draw pile
	private ArrayList<Player> players;//holds the players
	private Card topCard;//holds the card on top of the discard pile
	private int currentPlayer;//the current player's index
	private int direction = 1;//1 if normal, -1 if reverse
	private int numPlayers;
	private boolean console;
	private boolean wildCard;
	private Card move;
	private boolean gameOver;
	private boolean validCard;
	
	
	public Game() {
		numPlayers = 0;
		players = new ArrayList<>();
		deck = new Deck();
		console = true;
		gameOver = false;
	}
	
	public Game(int selectedNumPlayers) {
		numPlayers = selectedNumPlayers;
		players = new ArrayList<>();
		deck = new Deck();
		console = false;
		gameOver = false;
	}
	
	/**
	 * in the game loop:
	 * <ul>
	 * 		this should get a player's move then discard the card and set the new top card.<br>
	 * 		if it isn't a number, this should also do the appropriate action.<br>
	 * 		then this should check if the player has won, if they have, it should quit.
	 * </ul>
	 */
	public void playTurn(int index) {
		validCard = false;
		
		wildCard = false;
		
		Player p = players.get(currentPlayer);
		
		if(index < p.getHand().size()) {
		
			if(p.getCard(index).canPlayOn(topCard)) {
				
				validCard = true;
			
				move = p.getCard(index);
			
				if(move.getType() == "rev") {
					if(players.size() == 2) {
						nextPlayer();
					}
					
					else {
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
					wildCard = true;
				}
				
				else if(move.getType() == "dr4") {
					wildCard = true;
					nextPlayer();
					Player p2 = players.get(currentPlayer);
					for(int i = 0; i<4; i++) {
						p2.add(deck.drawCard());
					}
				}
				p.remove(index);
				if (!wildCard) {
					deck.discard(move);
					topCard = move; 
				}
				
				if(p.handSize() == 0) {
					System.out.println("Congratulations " + p.getName() + ", you have won the game!");
					gameOver = true;
				}
				
				if(!p.getSaidUno() && p.handSize() == 1) {
					p.add(deck.drawCard());
					p.add(deck.drawCard());
				}
				
				p.setSaidUno(false);
			}
		}
	}
	
	/**
	 * prints the rules
	 * gets the number of players
	 * gets the names and initializes the players
	 * gets the 1st player and sets currentPlayer
	 */
	public void menu() {
		if(console) {
			Scanner in = new Scanner(System.in);
			System.out.println("How many people are going to play?");
			numPlayers = in.nextInt();
			
			//this will only run if they input a number that is outside of the correct number range of players
			while(numPlayers < MIN_PLAYERS || numPlayers > MAX_PLAYERS) {
				System.out.println("Please enter a valid number between " + MIN_PLAYERS + " and " + MAX_PLAYERS);
				numPlayers = in.nextInt();
			}
		}
		
		for(int i = 0; i < numPlayers; i++) {
			Player p = new Player("Player " + (i + 1));
			players.add(p);
		}
	
	}
	
	/**
	 * calls menu()<br>
	 * initializes deck and gives the players 7 cards each,<br>
	 * calls deck.peekDiscard() and sets topCard with it<br>
	 * calls gameLoop()
	 */
	public void setup() {
		topCard = deck.peekDiscard();
		menu();
		for(Player p : players) {
			for(int i = 0; i < HAND_SIZE; i++) {
				p.add(deck.drawCard());
			}
		}
	}
	
	public boolean hasWon() {
		return gameOver;
	}
	
	
	public void nextPlayer() {
		currentPlayer = currentPlayer + direction;
		
		if(currentPlayer == -1) {
			currentPlayer = players.size() - 1;
		}
		
		else {
			currentPlayer = currentPlayer % players.size();
		}
	}
	
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}
	
	public void drawCard() {
		players.get(currentPlayer).add(deck.drawCard());
	}
	
	public Card getTopCard() {
		return deck.peekDiscard();
	}
	
	public ArrayList<Card> getCurrentHand(){
		return players.get(currentPlayer).getHand();
	}
	
	public boolean isWild() {
		return wildCard;
	}
	
	public void playWild(int colorIndex) {

		if(colorIndex == 0) {
			move.setWildColor(Color.rgb(255, 53, 0));
		}
		
		if(colorIndex == 1) {
			move.setWildColor(Color.rgb(255, 137, 0));
		}
		
		if(colorIndex == 2) {
			move.setWildColor(Color.rgb(8, 122, 177));
		}
		
		if(colorIndex == 3) {
			move.setWildColor(Color.rgb(0, 194, 84));
		}
		
		deck.discard(move);
		topCard = move; 
		nextPlayer();
		
	}
	
	public boolean isValidCard() {
		return validCard;
	}
}
