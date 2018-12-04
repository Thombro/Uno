package uno;

import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Plays one game of UNO on either the console or the gui
 * Contains most of the gui methods
 * @author Isabella Patnode, Thomas Hutchins, Daniel Supplee, Grace Badger, Jedidiah Madubuko, 
 *
 */
public class Main extends Application {
	
	private Stage window;
	private Scene mainMenu;
	private Scene howToPlay;
	private Scene gamePlay;
	private int numPlayers;
	private RadioButton play4;
	private RadioButton play3;
	private RadioButton play2;
	private HBox layoutDeck;
	private HBox layoutHand;
	private StackPane root;
	private Game newGame;
	private HBox wildButtons;
	private Card currentVisible;
	private HBox unoContainer;
	private Text currentPlayerName;
	private VBox exitContainer;
	private Scene endScene;
	
	public static void main(String[] args) {
		boolean gui = false;
		boolean incorrectEnter = true;
		Scanner scnr = new Scanner(System.in);
		//allows players to choose whether they want to play on the gui or console
		while(incorrectEnter) {
			System.out.println("Please enter c to play on the console or g to play on the graphic user interface.");
			char choice = scnr.next().charAt(0);
		
			if(choice == 'G' || choice == 'g') {
				gui = true;
				incorrectEnter = false;
			}
			else if(choice == 'c' || choice == 'C') {
				incorrectEnter = false;
			}
		}
		
		//Launches gui if players want to play on the gui
		if(gui) {
			launch(args);
		}
		//Runs the game on the console if players want to play on the console
		else {
			Game g = new Game();
			g.consolePlayTurn();
		}
	}
	
	/**
	 * Primary method for creating the gui
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		//creates the main menu for the gui
		Text gameName = new Text("UNO");
		gameName.setFont(Font.font("Serif", 100));
		gameName.setFill(Color.YELLOW);
		gameName.setStrokeWidth(1.5);
		gameName.setStroke(Color.BLACK);
		
		//sets up the game when the start button is pressed
		Button startBtn = new Button("Start Game");
		startBtn.setOnAction(e -> setupGame());
		
		//takes players to the instructions when pressed
		Button ruleBtn = new Button("How to Play");
		ruleBtn.setOnAction(e -> window.setScene(howToPlay));
		
		Text playText = new Text("Number of Players");
		playText.setFont(Font.font("Serif", 15));
		
		final ToggleGroup groupOfPlayers = new ToggleGroup();
		
		//the buttons the players use to select the number of players that are playing
		play2 = new RadioButton("Two Player");
		play2.setToggleGroup(groupOfPlayers);
		//automatically sets the game to two players unless otherwise specified
		play2.setSelected(true);
		
		play3 = new RadioButton("Three Player");
		play3.setToggleGroup(groupOfPlayers);
		
		play4 = new RadioButton("Four Player");
		play4.setToggleGroup(groupOfPlayers);
		
		
		//sets the background design for the main menu
		StackPane background = new StackPane();
		background.setStyle("-fx-background-color: DARKRED;");
		
		//centers the text and buttons created for the main menu
		HBox gameTitle = new HBox(20);
		gameTitle.getChildren().add(gameName);
		gameTitle.setAlignment(Pos.CENTER);
		
		HBox playerNumber = new HBox(40);
		playerNumber.getChildren().addAll(play2, play3, play4);
		playerNumber.setAlignment(Pos.CENTER);
		
		HBox buttons = new HBox(40);
		buttons.getChildren().addAll(startBtn, ruleBtn);
		buttons.setAlignment(Pos.CENTER);
		
		VBox menuLayout = new VBox(20);
		menuLayout.getChildren().addAll(gameName, playText, playerNumber, buttons);
		menuLayout.setAlignment(Pos.CENTER);
		
		background.getChildren().add(menuLayout);
		
		
		mainMenu = new Scene(background, 800, 650);
		 
		//sets up the how to play screen for the gui
		
		//returns to the main menu when pressed
		Button backBtn = new Button("Back");
		backBtn.setOnAction(e -> window.setScene(mainMenu));
		
		Text rulesTitle = new Text("How To Play");
		rulesTitle.setFont(Font.font("Serif", 25));
		rulesTitle.setTextAlignment(TextAlignment.CENTER);
		
		HBox htpTitle = new HBox(20);
		htpTitle.getChildren().add(rulesTitle);
		htpTitle.setAlignment(Pos.BASELINE_CENTER);
		
		Text rules =  new Text("UNO is a game where you try to empty your hand before your opponents.\n"
				+ "Each player is dealt seven cards, the remaining cards forming the draw pile.\n"
				+ "Afterwards, the top card of the draw pile is turned over to form the discard pile.\n" 
				+ "The play consists of each player selecting a card from their hand by matching the " 
				+ "color, number, or word of the top card of the discard pile. A wild card will always work.\n"
				+ "If a player cannot play any of their cards, they must draw one card from the draw pile.\n"
				+ "If that card fits the sequence they may play it, otherwise their turn is over.\n"
				+ "The maximum number of cards you can have in your deck is 10. If you have more than ten card, you "
				+ "must play a card.\n"
				+ "When you have one card in your hand, you must call UNO. Otherwise you will be automatically drawn "
				+ "two cards.\n"
				+ "UNO has cards in four colors: red, blue, green, and yellow.\n"
				+ "It also has fifteen numbers and types: 0-9, wild, wild draw 4, skip, reverse, and draw 2.");
		rules.setFont(Font.font("Serif", 20));
		rules.setWrappingWidth(500);
		rules.setTextAlignment(TextAlignment.JUSTIFY);
		
		HBox rulesLayout = new HBox(20);
		rulesLayout.getChildren().add(rules);
		rulesLayout.setAlignment(Pos.CENTER);
		
		//creates the background for the "how to play" screen
		StackPane htpBackground = new StackPane();
		htpBackground.setStyle("-fx-background-color: LIGHTGREY;");
		
		//creates the layout for the instructions and buttons on the "how to play" screen
		HBox back = new HBox(40);
		back.getChildren().add(backBtn);
		
		VBox ruleLayout = new VBox(50);
		ruleLayout.getChildren().addAll(htpTitle, rulesLayout, back);
		
		htpBackground.getChildren().add(ruleLayout);
		
		howToPlay = new Scene(htpBackground, 800, 650);
		
		//sets up the screen where the game is actually played
		
		//sets the background for the screen where the game is played
		root = new StackPane();
		root.setStyle("-fx-background-color: BLACK;");
		
		//returns to the main menu when pressed
		Button exit = new Button("Exit Game");
		exit.setOnMouseClicked(e -> clearGame());
		exit.setAlignment(Pos.TOP_LEFT);
		
		//current players draws a card when pressed
		Button draw = new Button("Draw");
		draw.setOnAction(e -> drawCard());
		draw.setAlignment(Pos.CENTER);
		
		//layout for draw button
		layoutDeck = new HBox(20);
		layoutDeck.setAlignment(Pos.CENTER);
		layoutDeck.getChildren().add(draw);
		
		//displays the cards in the current player's hand
		layoutHand = new HBox(20);
		layoutHand.setAlignment(Pos.BOTTOM_CENTER);
		
		//designs the display of the cards in the discard deck and hand
		VBox cardLayout = new VBox(30);
		cardLayout.getChildren().addAll(layoutDeck, layoutHand);
		cardLayout.setAlignment(Pos.CENTER);
		
		//creates color option buttons that can be pressed when a wild card is selected
		Button redWild = new Button("Red");
		redWild.setOnMouseClicked(e -> chooseWild(0));
		Button greenWild = new Button("Green");
		greenWild.setOnMouseClicked(e -> chooseWild(1));
		Button blueWild = new Button("Blue");
		blueWild.setOnMouseClicked(e -> chooseWild(2));
		Button yellowWild = new Button("Yellow");
		yellowWild.setOnMouseClicked(e -> chooseWild(3));
		
		wildButtons = new HBox(20);
		wildButtons.getChildren().addAll(redWild, greenWild, blueWild, yellowWild);
		wildButtons.setAlignment(Pos.CENTER);
		//disables the color option buttons for the wild card until a wild card is selected
		wildButtons.setDisable(true);
		
		//creates uno button that is pressed when current player has one card remaining after their turn
		Button unoButton = new Button("UNO");
		unoButton.setOnMouseClicked(e -> sayUno());
		
		unoContainer = new HBox(20);
		unoContainer.getChildren().add(unoButton);
		unoContainer.setAlignment(Pos.CENTER);
		
		//designs the layout of the buttons associated with the game of Uno
		VBox gameButtons = new VBox(20);
		gameButtons.getChildren().addAll(wildButtons, unoContainer);
		gameButtons.setAlignment(Pos.CENTER);
		
		//designs the layout of the entire game screen
		VBox gameLayout = new VBox(20);
		gameLayout.getChildren().addAll(exit, cardLayout, gameButtons);
		
		root.getChildren().addAll(gameLayout);
	
		gamePlay = new Scene(root, 850, 650);
		
		Button endButton = new Button("End Game");
		endButton.setOnMouseClicked(e -> window.setScene(mainMenu));
		
		exitContainer = new VBox(20);
		exitContainer.getChildren().add(endButton);
		exitContainer.setAlignment(Pos.CENTER);
		exitContainer.setStyle("-fx-background-color: DARKGREY;");
		
		endScene = new Scene(exitContainer, 200, 200);
		
		//sets first screen as the main menu
		window.setScene(mainMenu);
		window.sizeToScene();
		window.centerOnScreen();
		window.setTitle("UNO");
		window.show();
	
	}
	
	/**
	 * Prepares the Uno game for play
	 */
	private void setupGame() {
		if(play2.isSelected()) {
			numPlayers = 2;
		}
		else if(play3.isSelected()) {
			numPlayers = 3;
		}
		else if(play4.isSelected()) {
			numPlayers = 4;
		}
		
		window.setScene(gamePlay);
		
		//creates a new Uno game with the number of players selected
		newGame = new Game(numPlayers);
		
		newGame.setup();
		
		//displays the name of the current player
		currentPlayerName = new Text(newGame.getCurrentPlayer().getName());
		currentPlayerName.setFill(Color.YELLOW);
		currentPlayerName.setFont(Font.font("Serif", 20));
		layoutHand.getChildren().add(currentPlayerName);
		
		//displays the card on top of the discard pile
		layoutDeck.getChildren().add(newGame.getTopCard());
		
		//displays each card in the current player's hand
		for(Card c : newGame.getCurrentHand()) {
			layoutHand.getChildren().add(c);
		}
			
		//a player selects the card he desires to play by pressing the number on the keyboard that corresponds
		//to the placement of the card
		//plays the card selected through the keyboard
		gamePlay.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if (key.getCode() == KeyCode.DIGIT1) {
				playCard(0);
			}
			else if(key.getCode() == KeyCode.DIGIT2) {
				playCard(1);
			}
			else if(key.getCode() == KeyCode.DIGIT3) {
				playCard(2);
			}
			else if(key.getCode() == KeyCode.DIGIT4) {
				playCard(3);	
			}
			else if(key.getCode() == KeyCode.DIGIT5) {
				playCard(4);
			}
			else if(key.getCode() == KeyCode.DIGIT6) {
				playCard(5);
			}
			else if(key.getCode() == KeyCode.DIGIT7) {
				playCard(6);
			}
			else if(key.getCode() == KeyCode.DIGIT8) {
				playCard(7);
			}
			else if(key.getCode() == KeyCode.DIGIT9) {
				playCard(8);
			}
			else if(key.getCode() == KeyCode.DIGIT0) {
				playCard(9);
			}
			else if(key.getCode() == KeyCode.Q) {
				playCard(10);
			}
			else if(key.getCode() == KeyCode.W) {
				playCard(11);
			}
			else if(key.getCode() == KeyCode.E) {
				playCard(12);
			}
			else if (key.getCode() == KeyCode.R) {
				playCard(13);
			}
			else if (key.getCode() == KeyCode.T) {
				playCard(14);
			}
			else if (key.getCode() == KeyCode.Y) {
				playCard(15);
			}
			});
	}
	
	/**
	 * Performs the current player's turn after the desired card is selected
	 * @param cardIndex the index of the selected card in the row of cards in the player's hand
	 */
	private void playCard(int cardIndex) {
		
		currentVisible = newGame.getTopCard();
		
		//plays the current player's turn for the selected card
		newGame.guiPlayTurn(cardIndex);
		
		//changes to exit screen and prints out winner when game is over
		if (newGame.hasWon()) {
			Text winner = new Text(newGame.getCurrentPlayer().getName() + " has won!!!");
			exitContainer.getChildren().add(winner);
			window.setScene(endScene);
		}
		
		//completes the current player's turn if the selected card can be played
		if(newGame.isValidCard()) {
			
			//enables the color option buttons if a wild card is selected
			if(newGame.isWild()) {
				wildButtons.setDisable(false);
			}
			else {
				//clears the current player's hand
				layoutHand.getChildren().clear();
				//clears the current top card
				layoutDeck.getChildren().remove(currentVisible);
				
				//changes the current player to the following player
				newGame.nextPlayer();
				
				//displays the name of the current player
				currentPlayerName = new Text(newGame.getCurrentPlayer().getName());
				currentPlayerName.setFill(Color.YELLOW);
				currentPlayerName.setFont(Font.font("Serif", 20));
				layoutHand.getChildren().add(currentPlayerName);
				
				
				//displays the new top card in the discard pile (the card played by the previous player)
				layoutDeck.getChildren().add(newGame.getTopCard());
				
				//displays the hand of the new current player
				for(Card c : newGame.getCurrentHand()) {
					layoutHand.getChildren().add(c);
				}
			}
		}
		//if card can not be played the current player repeats their turn
		else {
			layoutHand.getChildren().clear();
			layoutDeck.getChildren().remove(currentVisible);
			
			layoutDeck.getChildren().add(newGame.getTopCard());
			
			//displays the name of the current player
			currentPlayerName = new Text(newGame.getCurrentPlayer().getName());
			currentPlayerName.setFill(Color.YELLOW);
			currentPlayerName.setFont(Font.font("Serif", 20));
			layoutHand.getChildren().add(currentPlayerName);

			for(Card c : newGame.getCurrentHand()) {
				layoutHand.getChildren().add(c);
			}
			
		}
	}
	
	/**
	 * Displays the cards for when a wild card is played
	 * @param colorIndex the color that the player wants the wild to be
	 */
	private void chooseWild(int colorIndex) {
		//disables the color option buttons after the current player selects the color of the wild
		wildButtons.setDisable(true);
		
		layoutHand.getChildren().clear();
		layoutDeck.getChildren().remove(currentVisible);
		
		newGame.playWild(colorIndex);
		
		layoutDeck.getChildren().add(newGame.getTopCard());
		
		//displays the name of the current player
		currentPlayerName = new Text(newGame.getCurrentPlayer().getName());
		currentPlayerName.setFill(Color.YELLOW);
		currentPlayerName.setFont(Font.font("Serif", 20));
		layoutHand.getChildren().add(currentPlayerName);
		
		for(Card c : newGame.getCurrentHand()) {
			layoutHand.getChildren().add(c);
		}
	}
	
	/**
	 * Completes the turn of the current player when the draw button is selected
	 */
	private void drawCard() {
		newGame.drawCard();
		
		layoutHand.getChildren().clear();
		layoutDeck.getChildren().remove(newGame.getTopCard());
		
		newGame.nextPlayer();
		
		
		layoutDeck.getChildren().add(newGame.getTopCard());
		
		//displays the name of the current player
		currentPlayerName = new Text(newGame.getCurrentPlayer().getName());
		currentPlayerName.setFill(Color.YELLOW);
		currentPlayerName.setFont(Font.font("Serif", 20));
		layoutHand.getChildren().add(currentPlayerName);
		
		for(Card c : newGame.getCurrentHand()) {
			layoutHand.getChildren().add(c);
		}
	}
	
	/**
	 * the boolean method callPlayerUno in the game class returns true
	 */
	private void sayUno() {
		newGame.callPlayerUno();
	}
	
	/**
	 * Clears entire game when the game is exited
	 */
	private void clearGame() {
		layoutHand.getChildren().clear();
		layoutDeck.getChildren().remove(newGame.getTopCard());
		
		window.setScene(mainMenu);
	}
}