package src;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 * This class draws the game
 */
public class Game extends JPanel implements KeyListener {

    /* Avoid mistakes */
    private static final long serialVersionUID = 3502635132646172422L;

    /* Fields */
    private int scale;
    private int mapsize;
    private Snake snake;
    private boolean picasso;
    private boolean retro;

    /* Constructor */
    public Game(Snake snake, int scale, int mapsize, boolean picasso, boolean retro) {
        this.scale = scale;
        this.snake = snake;
        this.picasso = picasso;
        this.mapsize = mapsize;
        this.retro = retro;

        setPreferredSize(new Dimension(mapsize * scale, mapsize * scale));
    }

    public void paintComponent(Graphics g) {

        /* If the player does not want to play as Picasso */
        if (!picasso) {

            /* Draws a white square over the screen to clear it */
            g.clearRect(0, 0, mapsize * scale, mapsize * scale);

            /* If the player plays retro mode */
            if (retro) {

                /* Draw a green square instead of white */
                g.setColor(new Color(0, 170, 0));
                g.fillRect(0, 0, mapsize * scale, mapsize * scale);
            }
        }

        /* If the player does not play retro */
        if (!retro) {

            /* Clear the text */
            g.clearRect(0, 0, 20 * scale, 1 * scale);

        }

        /* Store last block */
        Body last = snake.getHead();

        /* Loop through the snake */
        while (last.getNext() != null) {

            /* Get the color of the selected body part */
            g.setColor(last.getColor());

            /* Draw a square on that place */
            g.fillRect(last.getLoc().x * scale, last.getLoc().y * scale, scale, scale);
            last = last.getNext();
        }

        /* Draw the tail */
        g.setColor(last.getColor());
        g.fillRect(last.getLoc().x * scale, last.getLoc().y * scale, scale, scale);

        /* Draw food */
        g.setColor(snake.food.getColor());
        g.fillRect(snake.food.getLoc().x * scale, snake.food.getLoc().y * scale, scale, scale);

        /* Draw the score */
        g.setColor(Color.BLACK);
        Font font = new Font("Helvetica", 0, 20);
        g.setFont(font);
        g.drawString("score: " + (snake.getLength() - 2), 20, 20);
    }

    /* KeyListeners */
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

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }
}