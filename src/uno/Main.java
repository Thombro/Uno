package uno;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
	/**
	 * creates a new Game and calls setup()<br>
	 * after the game this asks if they want to play a new game.<br>
	 * if so, it calls setup again
	 */
	Stage window;
	Scene mainMenu;
	Scene gameSetup;
	Scene howToPlay;
	Scene gamePlay;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage window = primaryStage;
		
//		Main Menu
		Label gameName = new Label("UNO");
		Button startBtn = new Button("Start Game");
		startBtn.setOnAction(e -> window.setScene(gameSetup));
		
		Button ruleBtn = new Button("How to Play");
		ruleBtn.setOnAction(e -> window.setScene(howToPlay));
		
		HBox layer1 = new HBox(20);
		layer1.setAlignment(Pos.CENTER);
		layer1.getChildren().add(gameName);
		
		HBox layer2 = new HBox(50);
		layer2.setAlignment(Pos.CENTER);
		layer2.getChildren().addAll(startBtn, ruleBtn);
		
		VBox menuLayout = new VBox(50);
		menuLayout.setAlignment(Pos.CENTER);
		menuLayout.getChildren().addAll(layer1, layer2);
		
		mainMenu = new Scene(menuLayout, 500, 400);
		
//		How to Play
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
		
//		Game Setup Menu
		Group root = new Group();
		ChoiceBox choiceBox = new ChoiceBox();
		root.getChildren().add(choiceBox);
		
        choiceBox.getItems().add("Choice 1");
        choiceBox.getItems().add("Choice 2");
        choiceBox.getItems().add("Choice 3");
		
        String value = (String) choiceBox.getValue();
        System.out.println(value);
        
        gameSetup = new Scene(root, 400, 400);
		
		window.setScene(mainMenu);
		window.setTitle("UNO");
		window.show();
	
	}	
	
	private void createMainMenu() {
		Label gameName = new Label("UNO");
		Button startBtn = new Button("Start Game");
		startBtn.setOnAction(e -> window.setScene(gameSetup));
		
		Button ruleBtn = new Button("How to Play");
		ruleBtn.setOnAction(e -> window.setScene(howToPlay));
		
		HBox layer1 = new HBox(20);
		layer1.setAlignment(Pos.CENTER);
		layer1.getChildren().add(gameName);
		
		HBox layer2 = new HBox(50);
		layer2.setAlignment(Pos.CENTER);
		layer2.getChildren().addAll(startBtn, ruleBtn);
		
		VBox menuLayout = new VBox(50);
		menuLayout.setAlignment(Pos.CENTER);
		menuLayout.getChildren().addAll(layer1, layer2);
		
		mainMenu = new Scene(menuLayout, 500, 400);
	}
	
	
	public void createHelpMenu() {
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
	}
	
	
	public void createGameSetupMenu() {
//		ListView pNumList = new ListView();
//		
//		pNumList.getItems().add("2");
//		pNumList.getItems().add("3");
//		pNumList.getItems().add("4");
//		
//		ObservableList selectedIndices = pNumList.getSelectionModel().getSelectedIndices();
		
		Group root = new Group();
		ChoiceBox choiceBox = new ChoiceBox();
		root.getChildren().add(choiceBox);
		
        choiceBox.getItems().add("Choice 1");
        choiceBox.getItems().add("Choice 2");
        choiceBox.getItems().add("Choice 3");
		
        String value = (String) choiceBox.getValue();
        System.out.println(value);
        
        gameSetup = new Scene(root, 400, 400);
		
	}
		
}
