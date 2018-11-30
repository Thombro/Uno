package uno;


import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

/**
 * holds the type & color of an UNO card
 * @author Tommy, Daniel, Jed, Izzy, and Grace
 */
public class Card extends Parent{
	private Color color;
	private String type;
	/**
	 * sets the color and type
	 * @param color 'y'(yellow), 'g'(green), 'b'(blue), or 'r'(red), or 'w'(wild)
	 * @param type 0 through 9, dr2(draw 2), skip, rev(reverse), wild, dr4(draw 4 wild)
	 */
	public Card(Color color, String type) {
		this.color = color;
		this.type = type;
		
	    //Group root = new Group();
	    //Scene theScene = new Scene( root );
	    //theStage.setScene( theScene );
	         
	   /* Canvas canvas = new Canvas( 400, 200 );
	    root.getChildren().add( canvas );
	         
	    GraphicsContext gc = canvas.getGraphicsContext2D();
	         
	    gc.setFill( Color.RED );
	    gc.setStroke( Color.BLACK );
	    gc.setLineWidth(2);
	    Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
	    gc.setFont( theFont );
	    gc.fillText( "Hello, World!", 60, 50 );
	    gc.strokeText( "Hello, World!", 60, 50 
		Rectangle r = new Rectangle();*/
		//r.setX(50);
		//r.setY(50);
		
		/*r.setWidth(100);
		r.setHeight(200);
		r.setArcWidth(20);
		r.setArcHeight(20);
		r.setFill(color);
	    Text text1 = new Text(type);
	    
	    r.getChildren().add(t);*/
	    //text1.setX(100 - text1.getLayoutBounds().getWidth() - 10);
        //text1.setY(text1.getLayoutBounds().getHeight());
		Rectangle bg = new Rectangle(80,100);
		bg.setArcHeight(20);
		bg.setFill(color);
		Text text = new Text(type);
		text.setWrappingWidth(70);
		getChildren().add(new StackPane(bg,text));
	}
	/**
	 * checks if this Card is a kind of wild, the same color as other, or the same type as other
	 * @param other : the other Card
	 * @return whether this Card can be played on top of other
	 */
	public boolean canPlayOn(Card other) {
		if(type=="wild") {
			return true;
		}
		else if(this.type.equals(other.type)||this.color==other.color) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * changes this Card's color if it is a wild card
	 * @param color : the color to change the card to
	 */
	public void setWildColor(Color color) {
		if(this.type == "wild") {
			this.color = color;
		}
	}
	public String getType() {
		return type;
	}
/*	@Override
	public String toString() {
		if(color == 'b') {
			return "blue "+type;
		}
		else if(color == 'r') {
			return "red "+type;
		}
		else if(color == 'g') {
			return "green "+type;
		}
		else if(color == 'y') {
			return "yellow "+type;
		}
		else {
			return "no color "+type;
		}
	}*/
}