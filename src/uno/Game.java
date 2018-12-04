package uno;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.paint.Color;

/**
 * Performs the main background code for when a game of UNO is played
 * Accessed by the gui methods in the main class
 * @author Isabella Patnode, Thomas Hutchins, Daniel Supplee, Grace Badger, Jedidiah Madubuko
 *
 */
public class Game {
	public static final int MIN_PLAYERS = 2;
	public static final int MAX_PLAYERS = 4;
	public static final int HAND_SIZE = 7;
	//the discard pile and draw pile
	private Deck deck;
	//holds the players in the game
	private ArrayList<Player> players;
	//holds the card on top of the discard pile
	private Card topCard;
	//the current player's index
	private int currentPlayer;
	//1 if normal, -1 if reverse
	private int direction = 1;
	//number of players in the game
	private int numPlayers;
	//true if playing on the console, false otherwise
	private boolean console;
	//true if card played is a wild card, false otherwise
	private boolean wildCard;
	//the card that is being played
	private Card move;
	//true if game is complete, false otherwise
	private boolean gameOver;
	//true if card played can be played, false otherwise
	private boolean validCard;
	
	/**
	 * Constructor for console version of UNO
	 */
	public Game() {
		numPlayers = 0;
		players = new ArrayList<>();
		deck = new Deck();
		console = true;
		gameOver = false;
	}
	
	/**
	 * Constructor used for gui version of UNO
	 * @param selectedNumPlayers the number of players in the game
	 */
	public Game(int selectedNumPlayers) {
		numPlayers = selectedNumPlayers;
		players = new ArrayList<>();
		deck = new Deck();
		console = false;
		gameOver = false;
	}
	
	/**
	 * game loop for console version of UNO
	 */
	public void consolePlayTurn() {
		
		//Sets up the game to be played
		setup(); 
		
		while(true) {
			Scanner scnr = new Scanner(System.in);
			
			Player p = players.get(currentPlayer);
	
			Card move = p.getPlayerMove(topCard, scnr);
			
			//adds a card to the player's hand if their hand is empty
			if(move == null) {
				p.add(deck.drawCard());
			}
			
			//performs moves based on special cards (rev, skip, draw2, draw4, wild)
			else {
				if(move.getType() == "rev") {
					//skips the next player if it is a two player game
					if(players.size() == 2) {
						nextPlayer();
					}
					//reverses the order of the players when game has more than two players
					else {
						direction = direction * -1;
					}
				}
				//skips the next player if a skip card is played
				if(move.getType() == "skip") {
					nextPlayer();
				}
				//automatically draws two card for the next player when a draw2 card is played
				if(move.getType() == "dr2") {
					nextPlayer();
					Player p2 = players.get(currentPlayer);
					p2.add(deck.drawCard());
					p2.add(deck.drawCard());
				}
				//automatically draws 4 card for the next player when a draw4 card is played
				else if(move.getType() == "dr4") {
					nextPlayer();
					Player p2 = players.get(currentPlayer);
					for(int i = 0; i<4; i++) {
						p2.add(deck.drawCard());
					}
				}
				
				//discards the card that is played from the current player's hand to the discard pile
				deck.discard(move);
				//makes the card on top of the discard pile the card most recently played
				topCard = move;
			}
			
			//ends the game when one player runs out of cards
			if(p.handSize() == 0) {
				System.out.println("Congratulations " + p.getName() + ", you have won the game!");
				break;
			}
			
			//automatically draw 2 cards for the current player if they don't say uno when they have
			//one card left
			if(!p.getSaidUno() && p.handSize() == 1) {
				p.add(deck.drawCard());
				p.add(deck.drawCard());
			}
			//gives the player a card if they hit uno and have more than one card
			if(p.getSaidUno() && p.handSize() > 1){
				p.add(deck.drawCard());
			}
			
			p.setSaidUno(false);
			nextPlayer();
		}
	}
	
	/**
	 * Plays the current player's turn for the card selected for gui version of UNO
	 * @param index the index of the card selected in the current player's hand
	 */
	public void guiPlayTurn(int index) {
		validCard = false;
		
		wildCard = false;
		
		Player p = players.get(currentPlayer);
		
		//plays card if card selected is in the hand, otherwise repeats their turn
		if(index < p.getHand().size()) {
			
			//plays card if card selected can be played
			if(p.getCard(index).canPlayOn(topCard)) {
				
				validCard = true;
			
				//returns the card at the selected index of the current player's hand
				move = p.getCard(index);
				
				//reverses order of players when a reverse card is played
				if(move.getType() == "rev") {
					if(players.size() == 2) {
						nextPlayer();
					}
					
					else {
						direction = direction * -1;
					}
				}
				
				//skips the next player when a skip card is played
				if(move.getType() == "skip") {
					nextPlayer();
				}
				
				//automatically draws 2 cards for the next player when a draw2 card is played
				if(move.getType() == "dr2") {
					nextPlayer();
					Player p2 = players.get(currentPlayer);
					p2.add(deck.drawCard());
					p2.add(deck.drawCard());
				}
				
				else if(move.getType() == "wild") {
					wildCard = true;
				}
				
				//automatically draws 4 cards for the next player when a draw4 card is played
				else if(move.getType() == "dr4") {
					wildCard = true;
				}
				//removes the card played from the current player's hand
				p.remove(index);
				
				if (!wildCard) {
					//discards the card played by the current player to the discard pile
					deck.discard(move);
					//makes the top card of the discard pile the most recent card played
					topCard = move; 
				}
				
				if(p.handSize() == 0) {
					gameOver = true;
				}
				
				//automatically adds two cards to the current player's hand if they don't say uno when they
				//have one card left
				if(!p.getSaidUno() && p.handSize() == 1) {
					p.add(deck.drawCard());
					p.add(deck.drawCard());
				}
				p.setSaidUno(false);
			}
		}
	}
	
	/**
	 * creates an ArrayList of players with the number of players selected
	 */
	public void menu() {
		//only runs if playing on the console
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
		
		//adds number of players to an ArrayList
		for(int i = 0; i < numPlayers; i++) {
			Player p = new Player("Player " + (i + 1));
			players.add(p);
		}
	
	}
	
	/**
	 * prepares the game for gameplay
	 */
	public void setup() {
		//initializes topCard as the first (and only) card in the discard pile 
		topCard = deck.peekDiscard();
		menu();
		//adds seven cards to each player's hand
		for(Player p : players) {
			for(int i = 0; i < HAND_SIZE; i++) {
				p.add(deck.drawCard());
			}
		}
	}
	
	/**
	 * @return true if the game is finished, false otherwise
	 */
	public boolean hasWon() {
		return gameOver;
	}
	
	/**
	 * calls the next player in the order of players
	 */
	public void nextPlayer() {
		currentPlayer = currentPlayer + direction;
		
		if(currentPlayer == -1) {
			currentPlayer = players.size() - 1;
		}
		
		else {
			currentPlayer = currentPlayer % players.size();
		}
	}
	
	/**
	 * @return the player whose turn it is
	 */
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}
	
	/**
	 * Adds a card from the draw pile to the current player's hand when they draw a card
	 */
	public void drawCard() {
		players.get(currentPlayer).add(deck.drawCard());
	}
	
	/**
	 * @return the card at the top of the discard pile
	 */
	public Card getTopCard() {
		return deck.peekDiscard();
	}
	
	/**
	 * @return the hand of the current player
	 */
	public ArrayList<Card> getCurrentHand(){
		return players.get(currentPlayer).getHand();
	}
	
	/**
	 * @return true if a wild card is selected, false otherwise
	 */
	public boolean isWild() {
		return wildCard;
	}
	
	/**
	 * Sets the wild card to the color selected by the player
	 * @param colorIndex the color the player wants to set the wild card to
	 */
	public void playWild(int colorIndex) {
		
		//sets the wild color to red
		if(colorIndex == 0) {
			move.setWildColor(Color.rgb(255, 53, 0));
		}
		
		//sets the wild color to green
		if(colorIndex == 1) {
			move.setWildColor(Color.rgb(0, 194, 84));
		}
		
		//sets the wild color to blue
		if(colorIndex == 2) {
			move.setWildColor(Color.rgb(8, 122, 177));
		}
		
		//sets the wild color to yellow
		if(colorIndex == 3) {
			move.setWildColor(Color.rgb(255, 137, 0));
		}
		
		//discards the wild card that was played to the top of the discard pile
		deck.discard(move);
		//makes top card on discard pile the wild card that was played
		topCard = move; 
		//calls the next player and adds cards to their deck
		if(move.getType() == "dr4") {
			nextPlayer();
			Player p2 = players.get(currentPlayer);
			for(int i = 0; i < 4; i++) {
				p2.add(deck.drawCard());
			}
		}
		
	}
	
	/**
	 * called when player has called UNO
	 */
	public void callPlayerUno() {
		players.get(currentPlayer).setSaidUno(true);
	}
	
	/**
	 * @return true if the card selected can be played, false otherwise
	 */
	public boolean isValidCard() {
		return validCard;
	}
}