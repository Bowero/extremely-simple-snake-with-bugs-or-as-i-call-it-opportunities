package src;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;

public class PlaySound {

    private final int BUFFER_SIZE = 128000;
    private File soundFile;

    /**
     * @param filename the name of the file that is going to be played
     */

    public static void play(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
}
