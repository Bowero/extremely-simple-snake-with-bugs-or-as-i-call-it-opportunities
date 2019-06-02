package src;

import java.awt.Color;
import java.awt.Point;

/**
 * The head is part of the body
 */
public class Head extends Body {

    /* Fields */
    private Point loc;
    private Color color;

    /* The head does not have a previous */
    private Body next;

    /* Constructor */
    public Head(Point loc, Color color) {
        super(loc, color);

        this.loc = loc;
        this.color = color;
    }

    /* Getters and setters */
    public void setNext(Body next) {
        this.next = next;
    }

    public Body getNext() {
        return next;
    }

    /* Overrides of abstract class Block */
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