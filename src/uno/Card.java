package uno;

public class Card {
	private char color; // can be blue:'b', green:'g', yellow:'y', red:'r', or none:' '(ex. wild)
	private String type; //can be 0-9, draw 2(Dr2?), skip(Skp?), reverse(Rev?), wild(Wld?), draw 4 wild(D4W?)
	//probably should add a public boolean canPlayOn(Card other) function which checks if it is a kind of wild, the same color, or the same type
	//need a public void setWild(char color) which sets the card's color if it doesn't already have one
}
