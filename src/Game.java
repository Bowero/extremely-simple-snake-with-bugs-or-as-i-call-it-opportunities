package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 * this class draws things, like my sister does
 */
public class Game extends JPanel implements KeyListener {

    /* avoid misteks */
    private static final long serialVersionUID = 3502635132646172422L;

    /* fields */
    public final static int DEFAULT_SCALE = 20;

    private int scale;
    private Snake snake;

    /* constructor */
    public Game(Snake snake) {
        this(snake, Game.DEFAULT_SCALE);
    }

    public Game(Snake snake, int scale) {
        this.scale = scale;
        this.snake = snake;

        addKeyListener(this);
        setPreferredSize(new Dimension(20 * scale, 20 * scale));
    }

    @Override
    public void paintComponent(Graphics g) {

        /* draws a white square over the screen to clear it */
        g.clearRect(0, 0, 20 * scale, 20 * scale);

        /* store last block */
        Body last = snake.getHead();

        /* loop through the snake */
        while (last.getNext() != null) {
            g.setColor(last.getColor());
            g.fillRect(last.getLoc().x * scale, last.getLoc().y * scale, scale, scale);
            last = last.getNext();
        }

        /* draw the tail */
        g.setColor(last.getColor());
        g.fillRect(last.getLoc().x * scale, last.getLoc().y * scale, scale, scale);


        /* draw food */
        g.setColor(snake.food.getColor());
        g.fillRect(snake.food.getLoc().x * scale, snake.food.getLoc().y * scale, scale, scale);

        /* draw the score */
        g.setColor(Color.BLACK);
        Font font = new Font("Helvetica", 0, 20);
        g.setFont(font);
        g.drawString("score: " + snake.getLength(), 20, 20);
    }

    /* keylisteners */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
        case KeyEvent.VK_UP:
            snake.setDirection(Directions.NORTH);
            break;

        case KeyEvent.VK_RIGHT:
            snake.setDirection(Directions.EAST);
            break;

        case KeyEvent.VK_DOWN:
            snake.setDirection(Directions.SOUTH);
            break;

        case KeyEvent.VK_LEFT:
            snake.setDirection(Directions.WEST);
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}