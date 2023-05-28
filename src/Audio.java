import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {
    public static final File BRICK_SOUND = new File("Audio/Brick.wav");
    public static final File WALL_SOUND = new File("Audio/Wall.wav");
    public static final File PLATFORM_SOUND = new File("Audio/Platform.wav");
    public static final File CHOICE_SOUND = new File("Audio/Choice.wav");
    public static final File CLICK_SOUND = new File("Audio/Click.wav");
    public static void playSound(File soundFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playSoundThread(File soundFile) {
        new Thread(() -> playSound(soundFile)).start();
    }
}