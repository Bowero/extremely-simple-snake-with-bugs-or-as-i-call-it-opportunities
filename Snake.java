import java.awt.Color;
import java.awt.Point;
import java.util.Random;

/**
 * complete snek with head and booty
 */
public class Snake {

    /* fields */
    private Head head;
    private Directions dir = Directions.EAST;

    public Food food = new Food(new Point(1, 3), Color.GREEN);

    private boolean nowalls;

    Random random = new Random();

    /* constructor */
    public Snake(Head head, boolean nowalls) {
        this.head = head;
        this.nowalls = nowalls;
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

        Body checkifyouaredeadotherwisewewillgetyoudead = head.getNext();
        while (checkifyouaredeadotherwisewewillgetyoudead.getNext() != null) {
            if (head.isCollision(checkifyouaredeadotherwisewewillgetyoudead))
                System.exit(0);
            checkifyouaredeadotherwisewewillgetyoudead = checkifyouaredeadotherwisewewillgetyoudead.getNext();
        }

        if (nowalls) {
            if (headX < 0) {
                head.setLoc(new Point(headX+20, headY));
            } else if (headX > 19) {
                head.setLoc(new Point(headX-20, headY));
            } else if (headY < 0) {
                head.setLoc(new Point(headX, headY+20));
            } else if (headY > 19) {
                head.setLoc(new Point(headX, headY-20));
            }
        } else {
            if (head.getLoc().x < 0 || head.getLoc().x > 19 || head.getLoc().y < 0 || head.getLoc().y > 19) {
                System.exit(0);
            }
        }

        if (head.isCollision(food)) {
            append(new Body(lp, Color.BLUE));
            food.setLoc(new Point(random.nextInt(19 - 0 + 1), random.nextInt(19 - 0 + 1)));


            /* Makes sure the food does not spawn on top of the snake*/
            while(last.getNext() != null){
                if(last.getLoc().x == food.getLoc().x && last.getLoc().y == food.getLoc().y){
                    food.setLoc(new Point(random.nextInt(19 - 0 + 1), random.nextInt(19 - 0 + 1)));
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
}