package uno;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
			Game newGame = new Game();
			newGame.setup();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage window = primaryStage;
		
		//Main Menu
		
		Text gameName = new Text("UNO");
		gameName.setFont(Font.font("Serif", 100));
		gameName.setFill(Color.YELLOW);
		gameName.setStrokeWidth(1.5);
		gameName.setStroke(Color.BLACK);
		
		Button startBtn = new Button("Start Game");
		startBtn.setOnAction(e -> window.setScene(gamePlay));
		
		Button ruleBtn = new Button("How to Play");
		ruleBtn.setOnAction(e -> window.setScene(howToPlay));
		
		Text playText = new Text("Number of Players");
		playText.setFont(Font.font("Serif", 15));
		
		final ToggleGroup groupOfPlayers = new ToggleGroup();
		
		RadioButton play2 = new RadioButton("Two Player");
		play2.setToggleGroup(groupOfPlayers);
		
		RadioButton play3 = new RadioButton("Three Player");
		play3.setToggleGroup(groupOfPlayers);
		
		RadioButton play4 = new RadioButton("Four Player");
		play4.setToggleGroup(groupOfPlayers);
		
		if(play2.isSelected()) {
			numPlayers = 2;
		}
		else if(play3.isSelected()) {
			numPlayers = 3;
		}
		else if(play4.isSelected()) {
			numPlayers = 4;
		}
		
		Game g = new Game(numPlayers);
		
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
		
		
		mainMenu = new Scene(background, 600, 600);
		
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
		
		howToPlay = new Scene(htpBackground, 600, 600);
		
		//Game
		
		Group root = new Group();
		
		Button exit = new Button("Exit Game");
		exit.setOnAction(e -> window.setScene(mainMenu));
		
		root.getChildren().add(exit);
		
		HBox layoutDeck = new HBox(20);
		root.getChildren().add(layoutDeck);
		
		HBox layoutHand = new HBox(20);
		root.getChildren().add(layoutHand);
		
		//g.setup();
		
		gamePlay = new Scene(root, 600, 600);
		
		window.setScene(mainMenu);
		window.setTitle("UNO");
		window.show();
	
	}	
}
