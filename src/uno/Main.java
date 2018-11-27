package uno;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


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
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage window = primaryStage;
		
		//Main Menu
		
		Label gameName = new Label("UNO");
		
		Button startBtn = new Button("Start Game");
		startBtn.setOnAction(e -> window.setScene(gamePlay));
		
		Button ruleBtn = new Button("How to Play");
		ruleBtn.setOnAction(e -> window.setScene(howToPlay));
		
		Text playText = new Text("Number of Players");
		
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
		
		
		HBox menuLayout = new HBox(20);
		menuLayout.getChildren().addAll(gameName, playText, play2, play3, play4, startBtn, ruleBtn);
		
		mainMenu = new Scene(menuLayout, 600, 600);
		
		//How To Play 
		
		Button backBtn = new Button("Back");
		backBtn.setOnAction(e -> window.setScene(mainMenu));
		
		Text rules =  new Text("UNO is a game where you try to empty your hand before your opponents.\n"
				+ "Each player is dealt seven cards, the remaining cards forming the draw pile.\n"
				+ "Afterwards, the top card of the draw pile is turned over to form the discard pile.\n" 
				+ "The play consists of each player selecting a card from their hand by matching the\n" 
				+ "color, number, or word of the top card of the discard pile. A wild card will always work.\n"
				+ "If a player cannot play any of their cards, they must draw one card from the draw pile"
				+ "if that card fits the sequence they may play it, otherwise their turn is over.\n"
				+ "UNO has cards in four colors: red, blue, green, and yellow"
				+ "It also has fifteen numbers and types: 0-9, wild, wild draw 4, skip, reverse, and draw 2.");

		VBox ruleLayout = new VBox(20);
		ruleLayout.getChildren().addAll(rules, backBtn);
		
		howToPlay = new Scene(ruleLayout, 600, 600);
		
		//Game
		
		HBox gameLayout = new HBox(20);
		
		Button exit = new Button("Exit Game");
		exit.setOnAction(e -> window.setScene(mainMenu));
		
		gameLayout.getChildren().addAll(exit);
		
		gamePlay = new Scene(gameLayout, 600, 600);
		
		window.setScene(mainMenu);
		window.setTitle("UNO");
		window.show();
	
	}	
}
