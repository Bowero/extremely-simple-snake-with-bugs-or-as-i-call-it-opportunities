package src;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * complete snek with head and booty
 */
public class Snake {

    /* fields */
    private Head head;
    private Directions dir = Directions.EAST;

    public Food food = new Food(new Point(1, 3), Color.GREEN);

    private boolean nowalls;
    private boolean pride;
    private int mapsize;
    private boolean retro;
    private int red = 0;
    private int green = 0;
    private int blue = 255;

    private String eatSound = "../audiofiles/ploink.wav";
    private String gameOver = "../audiofiles/gameover.wav";

    PlaySound playSound = new PlaySound();
    Random random = new Random();

    /* constructor */

    public Snake(Head head, boolean nowalls, boolean pride, int mapsize, boolean retro) {
        this.head = head;
        this.nowalls = nowalls;
        this.pride = pride;
        this.mapsize = mapsize;
        this.retro = retro;
    }

    /* functions */
    public void append(Body next) {
        Body last = findLastBlock();
        last.setNext(next);
        next.setPrev(last);
    }

    public void move() {
        this.move(dir);
    }

    public void move(Directions dir) {

        /* loop through the snake */
        Body last = findLastBlock();
        Point lp = last.getLoc();

        while (last.getPrev() != null) {

            /* move the snake */
            last.setLoc(last.getPrev().getLoc());
            last = last.getPrev();
        }

        int headX = head.getLoc().x;
        int headY = head.getLoc().y;

        switch (dir) {
        case NORTH:
            headY -= 1;
            head.setLoc(new Point(headX, headY));
            this.dir = Directions.NORTH;
            break;

        case EAST:
            headX += 1;
            head.setLoc(new Point(headX, headY));
            this.dir = Directions.EAST;
            break;

        case SOUTH:
            headY += 1;
            head.setLoc(new Point(headX, headY));
            this.dir = Directions.SOUTH;
            break;

        case WEST:
            headX -= 1;
            head.setLoc(new Point(headX, headY));
            this.dir = Directions.WEST;
            break;
        }
      
        Body collisionBody = head;
        while (collisionBody.getNext() != null) {
            collisionBody = collisionBody.getNext();
            if (head.isCollision(collisionBody)) {
                playSound.playSound(gameOver);
                sleep(3000);
                System.exit(0);
            }

        if (nowalls) {
            if (headX < 0) {
                head.setLoc(new Point(headX + mapsize, headY));
            } else if (headX > mapsize - 1) {
                head.setLoc(new Point(headX - mapsize, headY));
            } else if (headY < 0) {
                head.setLoc(new Point(headX, headY + mapsize));
            } else if (headY > mapsize - 1) {
                head.setLoc(new Point(headX, headY - mapsize));
            }
        } else {
            if (head.getLoc().x < 0 || head.getLoc().x > mapsize - 1 || head.getLoc().y < 0
                    || head.getLoc().y > mapsize - 1) {
                playSound.play(gameOver);
                sleep(3000);
                System.exit(0);
            }
        }

        /* checks if the snake collides with the food */
        if (head.isCollision(food)) {

            new PlaySound().play(eatSound);

            if (pride) {
                append(new Body(lp, food.getColor()));
                red = random.nextInt(255 - 0 + 1);
                green = random.nextInt(255 - 0 + 1);
                blue = random.nextInt(255 - 0 + 1);
                food.setColor(new Color(red, green, blue, 255));
            } else {
                if (!retro) {
                    append(new Body(lp, Color.BLUE));
                } else {
                    append(new Body(lp, new Color(10, 80, 40)));
                }
            }
            food.setLoc(new Point(random.nextInt(mapsize), random.nextInt(mapsize)));

            /* Makes sure the food does not spawn on top of the snake */
            while (last.getNext() != null) {
                if (last.getLoc().x == food.getLoc().x && last.getLoc().y == food.getLoc().y) {
                    food.setLoc(new Point(random.nextInt(mapsize), random.nextInt(mapsize)));
                }

                last = last.getNext();
            }
        }
    }

    public void setDirection(Directions dir) {
        switch (dir) {
        case NORTH:
            if (this.dir != Directions.SOUTH)
                this.dir = dir;
            break;
        case EAST:
            if (this.dir != Directions.WEST)
                this.dir = dir;
            break;
        case SOUTH:
            if (this.dir != Directions.NORTH)
                this.dir = dir;
            break;
        case WEST:
            if (this.dir != Directions.EAST)
                this.dir = dir;
            break;
        }
    }

    /* find the last block of the snake */
    public Body findLastBlock() {

        /* loop through the snake */
        Body last = head;
        while (last.getNext() != null) {
            last = last.getNext();
        }

        return last;
    }

    public Head getHead() {
        return head;
    }

    public int getLength() {

        /* keep track of the length in a temporary integer */
        int length = 1;

        /* loop through the snake */
        Body last = head;
        while (last.getNext() != null) {
            last = last.getNext();
            length++;
        }

        /* return the length of the snake */
        return length - 2;
    }

    private void sleep(int timer) {
        try {
            TimeUnit.MILLISECONDS.sleep(timer);
        } catch (Exception e) {
            System.out.println("Sleep failed. Exception: " + e);
        }
    }
}