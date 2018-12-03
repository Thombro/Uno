package uno;


import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
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


public class Main extends Application {
	/**
	 * creates a new Game and calls setup()<br>
	 * after the game this asks if they want to play a new game.<br>
	 * if so, it calls setup again
	 */
	Stage window;
	Scene mainMenu;
	Scene howToPlay;
	Scene gamePlay;
	private int numPlayers;
	RadioButton play4;
	RadioButton play3;
	RadioButton play2;
	HBox layoutDeck;
	HBox layoutHand;
	StackPane root;
	Game newGame;
	HBox wildButtons;
	
	public static void main(String[] args) {
		boolean gui = false;
		boolean incorrectEnter = true;
		Scanner scnr = new Scanner(System.in);
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
		
		
		if(gui) {
			launch(args);
		}
		else {
			Game g = new Game();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		//Main Menu
		
		Text gameName = new Text("UNO");
		gameName.setFont(Font.font("Serif", 100));
		gameName.setFill(Color.YELLOW);
		gameName.setStrokeWidth(1.5);
		gameName.setStroke(Color.BLACK);
		
		Button startBtn = new Button("Start Game");
		startBtn.setOnAction(e -> setupGame());
		
		Button ruleBtn = new Button("How to Play");
		ruleBtn.setOnAction(e -> window.setScene(howToPlay));
		
		Text playText = new Text("Number of Players");
		playText.setFont(Font.font("Serif", 15));
		
		final ToggleGroup groupOfPlayers = new ToggleGroup();
		
		play2 = new RadioButton("Two Player");
		play2.setToggleGroup(groupOfPlayers);
		play2.setSelected(true);
		
		play3 = new RadioButton("Three Player");
		play3.setToggleGroup(groupOfPlayers);
		
		play4 = new RadioButton("Four Player");
		play4.setToggleGroup(groupOfPlayers);
		
		
		newGame = new Game(numPlayers);
		
		StackPane background = new StackPane();
		background.setStyle("-fx-background-color: DARKRED;");
		
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
		
		
		mainMenu = new Scene(background, 800, 800);
		
		//How To Play 
		
		Button backBtn = new Button("Back");
		backBtn.setOnAction(e -> window.setScene(mainMenu));
		
		Text rules =  new Text("UNO is a game where you try to empty your hand before your opponents.\n"
				+ "Each player is dealt seven cards, the remaining cards forming the draw pile.\n"
				+ "Afterwards, the top card of the draw pile is turned over to form the discard pile.\n" 
				+ "The play consists of each player selecting a card from their hand by matching the " 
				+ "color, number, or word of the top card of the discard pile. A wild card will always work.\n"
				+ "If a player cannot play any of their cards, they must draw one card from the draw pile "
				+ "if that card fits the sequence they may play it, otherwise their turn is over.\n"
				+ "UNO has cards in four colors: red, blue, green, and yellow.\n"
				+ "It also has fifteen numbers and types: 0-9, wild, wild draw 4, skip, reverse, and draw 2.");
		rules.setFont(Font.font("Serif", 20));
		rules.setWrappingWidth(500);
		rules.setTextAlignment(TextAlignment.JUSTIFY);
		
		StackPane htpBackground = new StackPane();
		htpBackground.setStyle("-fx-background-color: LIGHTBLUE;");
		
		HBox back = new HBox(40);
		back.getChildren().add(backBtn);
		
		VBox ruleLayout = new VBox(50);
		ruleLayout.getChildren().addAll(rules, back);
		
		htpBackground.getChildren().add(ruleLayout);
		
		howToPlay = new Scene(htpBackground, 800, 800);
		
		
		root = new StackPane();
		root.setStyle("-fx-background-color: BLACK;");
		
		/*
		Button exit = new Button("Exit Game");
		exit.setOnAction(e -> window.setScene(mainMenu));
		exit.setAlignment(Pos.BOTTOM_LEFT);*/
		
		Button draw = new Button("Draw");
		draw.setOnAction(e -> drawCard());
		draw.setAlignment(Pos.CENTER);
		
		layoutDeck = new HBox(20);
		layoutDeck.setAlignment(Pos.CENTER);
		layoutDeck.getChildren().add(draw);
		
		layoutHand = new HBox(20);
		layoutHand.setAlignment(Pos.BOTTOM_CENTER);
		
		VBox gameLayout = new VBox(30);
		gameLayout.getChildren().addAll(layoutDeck, layoutHand);
		
		Button redWild = new Button("Red");
		redWild.setOnAction(e -> chooseWild(0));
		Button greenWild = new Button("Green");
		greenWild.setOnAction(e -> chooseWild(1));
		Button blueWild = new Button("Blue");
		blueWild.setOnAction(e -> chooseWild(2));
		Button yellowWild = new Button("Yellow");
		yellowWild.setOnAction(e -> chooseWild(3));
		
		wildButtons = new HBox(20);
		wildButtons.getChildren().addAll(redWild, greenWild, blueWild, yellowWild);
		wildButtons.setAlignment(Pos.CENTER);
		wildButtons.setDisable(true);
		
		
		root.getChildren().addAll(wildButtons, gameLayout);
	
		gamePlay = new Scene(root, 800, 800);
		
		window.setScene(mainMenu);
		window.setTitle("UNO");
		window.show();
	
	}	

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
		
		newGame = new Game(numPlayers);
		
		newGame.setup();
		
		layoutDeck.getChildren().add(newGame.getTopCard());
		
		for(Card c : newGame.getCurrentHand()) {
			layoutHand.getChildren().add(c);
		}
			
		//For the player to play
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
			});
		
		if(newGame.hasWon()) {
			Text winner = new Text(newGame.getCurrentPlayer() + " has won!!!");
		
			root.getChildren().add(winner);
		}
	}
	
	private void playCard(int cardIndex) {
		
		newGame.playTurn(cardIndex);
		
		if(newGame.isWild()) {
			wildButtons.setDisable(false);
		} 
		
		else {
			
			layoutHand.getChildren().clear();
			layoutDeck.getChildren().remove(newGame.getTopCard());

			newGame.nextPlayer();
			
			layoutDeck.getChildren().add(newGame.getTopCard());
			
			for(Card c : newGame.getCurrentHand()) {
				layoutHand.getChildren().add(c);
			}
		}
		
	}
	
	private void chooseWild(int colorIndex) {
		layoutHand.getChildren().clear();
		layoutDeck.getChildren().remove(newGame.getTopCard());
		
		newGame.playWild(colorIndex);
		
		layoutDeck.getChildren().add(newGame.getTopCard());
		
		for(Card c : newGame.getCurrentHand()) {
			layoutHand.getChildren().add(c);
		}
	}
	
	private void drawCard() {
		newGame.drawCard();
		
		layoutHand.getChildren().clear();
		layoutDeck.getChildren().remove(newGame.getTopCard());
		
		newGame.nextPlayer();
		
		layoutDeck.getChildren().add(newGame.getTopCard());
		
		for(Card c : newGame.getCurrentHand()) {
			layoutHand.getChildren().add(c);
		}
	}
}
