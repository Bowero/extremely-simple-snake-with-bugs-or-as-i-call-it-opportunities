package src;
import java.awt.Color;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

/**
 * this could also be named app or program
 */
public class Main {

    static Timer timer = new Timer();
    static TimerTask task;

    public static boolean nowalls;
    public static boolean pride;
    public static boolean picasso;

    public static void main(String[] args) {

        nowalls = false;
        pride = false;
        picasso = false;
        int scale = 20;

        for(int i = 0; i < args.length; i++) {
            if (args.length > 0) {
                if (args[i].equals("--no-walls")) {
                    nowalls = true;
                }
                if (args[i].equals("--pride")) {
                    pride = true;
                }
                if (args[i].equals("--scale")) {
                    scale = Integer.parseInt(args[i + 1]);
                }
                if(args[i].equals("--picasso")){
                    picasso = true;
                    pride = true;
                }
                if(args[i].equals("--help")){

                    System.out.println("\n" +
"The following parameters are available:\n" +
"\n" +
"--no-walls             this disables all walls\n" +
"--pride                this turns the snake into a rainbow snake\n" + 
"--scale NUM            this allows you to scale the game\n" +
"                       default: 20\n" +
"\n" +
                    "");

                    System.exit(0);
                }
            }
        }
        /* unit test */
        Head head = new Head(new Point(6, 4), Color.BLACK);

        Snake snake = new Snake(head, nowalls, pride);
        Body body = new Body(new Point(5, 4), Color.BLUE);

        snake.append(body);

        /* screen */
        JFrame frame = new JFrame();
        Game game = new Game(snake, scale, picasso);
        frame.add(game);
        frame.addKeyListener(game);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        task = new TimerTask() {

            @Override
            public void run() {
                snake.move();
                game.repaint();
            }
        };
        timer.schedule(task, 100, 100);

    }
}