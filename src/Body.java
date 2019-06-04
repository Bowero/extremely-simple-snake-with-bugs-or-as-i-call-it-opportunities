package src;

import java.awt.Color;
import java.awt.Point;

/**
 * The body is a part of the snake. The head is also a body part
 */
public class Body extends Block {

    /* Fields */
    private Point loc;
    private Color color;

    /* The body is part of a linked list */
    private Body prev;
    private Body next;

    /* Constructor */
    public Body(Point loc, Color color) {
        super(loc, color);

        this.loc = loc;
        this.color = color;
    }

    /* Getters and setters */
    public void setNext(Body next) {
        this.next = next;
    }

    public void setPrev(Body prev) {
        this.prev = prev;
    }

    public Body getNext() {
        return next;
    }

    public Body getPrev() {
        return prev;
    }

    /* Overrides of abstract class */
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

    /* Check if object collides */
    public boolean isCollision(Block o) {

        /* Following is true when object and head collide */
        return (getLoc().x == o.getLoc().x && getLoc().y == o.getLoc().y);

    }

    public int count() {
        if (getNext() != null) return getNext().count() + 1;
        return 1;
    }

}