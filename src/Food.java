package src;

import java.awt.Color;
import java.awt.Point;

/**
 * There is always 1 food element in the game
 */
public class Food extends Block {

    /* Fields */
    private Point loc;
    private Color color;

    /* Constructor */
    public Food(Point loc, Color color) {
        super(loc, color);

        this.loc = loc;
        this.color = color;
    }

    /* Getters and setters */
    public void setLoc(Point loc) {
        this.loc = loc;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getLoc() {
        return loc;
    }

    public Color getColor() {
        return color;
    }

}