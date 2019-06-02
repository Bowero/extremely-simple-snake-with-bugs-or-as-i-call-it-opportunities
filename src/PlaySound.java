package src;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.io.*;

public class PlaySound {


    /**
     * @param filename the name of the file that is going to be played
     */

    public static void play(String filename) {
        try {
            Clip clip = AudioSystem.getClip();

            clip.addLineListener(event -> {
                if(LineEvent.Type.STOP.equals(event.getType())) {
                    clip.close();
                }
            });

            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
}
